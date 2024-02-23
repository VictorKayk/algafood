package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.response.PaymentMethodResponseDTO;
import com.victorkayk.algafood.api.mapper.PaymentMethodMapper;
import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.service.RestaurantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Restaurants", description = "Restaurant payment method endpoints")
@RestController
@RequestMapping("/restaurants/{restaurantId}/payment-methods")
public class RestaurantPaymentMethodController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @GetMapping
    public ResponseEntity<List<PaymentMethodResponseDTO>> listPaymentMethodsFromRestaurant(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        return ResponseEntity.ok(
                restaurant.getPaymentMethods().stream().map(paymentMethodMapper::toResponseDTO).toList()
        );
    }

    @PutMapping("/{paymentMethodId}")
    public ResponseEntity<Void> associatePaymentMethod(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantService.associatePaymentMethod(restaurantId, paymentMethodId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{paymentMethodId}")
    public ResponseEntity<Void> disassociatePaymentMethod(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantService.disassociatePaymentMethod(restaurantId, paymentMethodId);
        return ResponseEntity.noContent().build();
    }
}
