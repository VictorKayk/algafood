package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.enums.ErrorEnum;
import com.victorkayk.algafood.domain.enums.StatusOrderEnum;
import com.victorkayk.algafood.domain.exception.ApiException;
import com.victorkayk.algafood.domain.model.Order;
import com.victorkayk.algafood.domain.model.PaymentMethod;
import com.victorkayk.algafood.domain.model.Product;
import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.repository.OrderRepository;
import com.victorkayk.algafood.domain.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public Order save(Order order) {
        userService.findById(order.getClient().getId());

        PaymentMethod paymentMethod = paymentMethodService.findById(order.getPaymentMethod().getId());
        Restaurant restaurant = restaurantService.findById(order.getRestaurant().getId());
        if (restaurant.isPaymentMethodNotAvailable(paymentMethod)) {
            throw new ApiException(ErrorEnum.PAYMENT_METHOD_NOT_AVAILABLE);
        }

        order.setOrderToOrderItems();
        order.getItems().forEach(orderItem -> {
            Product product = productService.findById(orderItem.getProduct().getId());
            if (restaurant.isProductNotAvailable(product)) {
                throw new ApiException(ErrorEnum.PRODUCT_METHOD_NOT_AVAILABLE);
            }
            orderItem.setProduct(product);
        });
        order.setRestaurant(restaurant);
        order.calculateTotal();

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void delete(String uuid) {
        Order order = findByUuid(uuid);

        try {
            orderRepository.delete(order);
            orderRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorEnum.ORDER_IN_USE);
        }
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findByUuid(String uuid) {
        return orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new ApiException(ErrorEnum.ORDER_NOT_FOUND));
    }

    @Override
    @Transactional
    public Order update(String uuid, Order order) {
        Order savedOrder = findByUuid(uuid);
        userService.findById(order.getClient().getId());

        PaymentMethod paymentMethod = paymentMethodService.findById(order.getPaymentMethod().getId());
        Restaurant restaurant = restaurantService.findById(order.getRestaurant().getId());
        if (restaurant.isPaymentMethodNotAvailable(paymentMethod)) {
            throw new ApiException(ErrorEnum.PAYMENT_METHOD_NOT_AVAILABLE);
        }

        orderRepository.deleteOrderItems(uuid);

        BeanUtils.copyProperties(order, savedOrder, "id", "uuid", "total", "createdAt");

        savedOrder.setOrderToOrderItems();
        order.getItems().forEach(orderItem -> {
            Product product = productService.findById(orderItem.getProduct().getId());
            orderItem.setProduct(product);
        });
        savedOrder.setRestaurant(restaurant);
        savedOrder.calculateTotal();

        return orderRepository.save(savedOrder);
    }

    @Override
    @Transactional
    public void updateStatus(String uuid, StatusOrderEnum status) {
        Order order = findByUuid(uuid);

        if (order.isUpdateStatusNotValid(status)) {
            throw new ApiException(ErrorEnum.INVALID_STATUS_UPDATE);
        }

        if (status.equals(StatusOrderEnum.CONFIRMED)) order.confirm();
        else if (status.equals(StatusOrderEnum.DELIVERED)) order.deliver();
        else if (status.equals(StatusOrderEnum.CANCELED)) order.cancel();
    }
}
