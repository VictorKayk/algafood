package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.OrderCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.OrderFilterRequestDTO;
import com.victorkayk.algafood.api.dto.request.OrderUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.request.OrderUpdateStatusRequestDTO;
import com.victorkayk.algafood.api.dto.response.OrderResponseDTO;
import com.victorkayk.algafood.api.dto.response.OrderSimplifiedResponseDTO;
import com.victorkayk.algafood.api.mapper.OrderMapper;
import com.victorkayk.algafood.api.util.PageableUtils;
import com.victorkayk.algafood.domain.model.Order;
import com.victorkayk.algafood.domain.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Tag(name = "Orders", description = "Order endpoints")
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderSimplifiedResponseDTO> list(Pageable pageable, OrderFilterRequestDTO dto) {
        Page<Order> orders = orderService.findAll(getPageableWithMappedSorts(pageable), orderMapper.toOrderFilterDTO(dto));
        return orders.map(orderMapper::toSimplifiedResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(orderMapper.toResponseDTO(orderService.findByUuid(id)));
    }

    @PostMapping
    public ResponseEntity<OrderSimplifiedResponseDTO> save(@RequestBody OrderCreateRequestDTO dto) {
        Order order = orderMapper.createRequestDTOToEntity(dto);
        return new ResponseEntity<>(orderMapper.toSimplifiedResponseDTO(orderService.save(order)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderSimplifiedResponseDTO> update(@PathVariable String id, @RequestBody OrderUpdateRequestDTO dto) {
        Order order = orderMapper.updateRequestDTOToEntity(dto);
        return ResponseEntity.ok(orderMapper.toSimplifiedResponseDTO(orderService.update(id, order)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderSimplifiedResponseDTO> updateStatus(@PathVariable String id, @RequestBody OrderUpdateStatusRequestDTO dto) {
        orderService.updateStatus(id, dto.status());
        return ResponseEntity.noContent().build();
    }

    private Pageable getPageableWithMappedSorts(Pageable pageable) {
        HashMap<String, String> possibleSorts = new HashMap<>();
        possibleSorts.put("id", "uuid");
        possibleSorts.put("subtotal", "subtotal");
        possibleSorts.put("shippingFee", "shippingFee");
        possibleSorts.put("total", "total");
        possibleSorts.put("status", "status");
        possibleSorts.put("createdAt", "createdAt");
        possibleSorts.put("client.name", "client.name");
        possibleSorts.put("restaurant.id", "restaurant.id");
        possibleSorts.put("restaurant.name", "restaurant.name");

        return PageableUtils.mapSort(pageable, possibleSorts);
    }
}
