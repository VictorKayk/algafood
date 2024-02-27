package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.response.PaymentMethodResponseDTO;
import com.victorkayk.algafood.api.mapper.PaymentMethodMapper;
import com.victorkayk.algafood.api.util.PageableUtils;
import com.victorkayk.algafood.domain.model.PaymentMethod;
import com.victorkayk.algafood.domain.model.Restaurant;
import com.victorkayk.algafood.domain.service.PaymentMethodService;
import com.victorkayk.algafood.domain.service.RestaurantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Tag(name = "Restaurants", description = "Restaurant payment method endpoints")
@RestController
@RequestMapping("/restaurants/{restaurantId}/payment-methods")
public class RestaurantPaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @GetMapping
    public ResponseEntity<Page<PaymentMethodResponseDTO>> listPaymentMethodsFromRestaurant(Pageable pageable, @PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        Page<PaymentMethod> paymentMethods = paymentMethodService.findAllByRestaurantId(getPageableWithMappedSorts(pageable), restaurantId);
        return ResponseEntity.ok(paymentMethods.map(paymentMethodMapper::toResponseDTO));
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

    private Pageable getPageableWithMappedSorts(Pageable pageable) {
        HashMap<String, String> possibleSorts = new HashMap<>();
        possibleSorts.put("id", "id");

        return PageableUtils.mapSort(pageable, possibleSorts);
    }
}
