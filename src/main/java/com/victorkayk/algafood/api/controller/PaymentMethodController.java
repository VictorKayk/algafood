package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.PaymentMethodCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.PaymentMethodUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.response.PaymentMethodResponseDTO;
import com.victorkayk.algafood.api.mapper.PaymentMethodMapper;
import com.victorkayk.algafood.domain.model.PaymentMethod;
import com.victorkayk.algafood.domain.service.PaymentMethodService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Payment Methods", description = "Payment method endpoints")
@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService PaymentMethodService;

    @Autowired
    private PaymentMethodMapper PaymentMethodMapper;

    @GetMapping
    public ResponseEntity<List<PaymentMethodResponseDTO>> list() {
        List<PaymentMethod> PaymentMethods = PaymentMethodService.findAll();
        return ResponseEntity.ok(
                PaymentMethods.stream().map(PaymentMethodMapper::toResponseDTO).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(PaymentMethodMapper.toResponseDTO(PaymentMethodService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PaymentMethodResponseDTO> save(@RequestBody PaymentMethodCreateRequestDTO dto) {
        PaymentMethod PaymentMethod = PaymentMethodMapper.createRequestDTOToEntity(dto);
        return new ResponseEntity<>(PaymentMethodMapper.toResponseDTO(PaymentMethodService.save(PaymentMethod)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodResponseDTO> update(@PathVariable Long id, @RequestBody PaymentMethodUpdateRequestDTO dto) {
        PaymentMethod PaymentMethod = PaymentMethodMapper.updateRequestDTOToEntity(dto);
        return ResponseEntity.ok(PaymentMethodMapper.toResponseDTO(PaymentMethodService.update(id, PaymentMethod)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        PaymentMethodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
