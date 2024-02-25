package com.victorkayk.algafood.api.controller;

import com.victorkayk.algafood.api.dto.request.OrderCreateRequestDTO;
import com.victorkayk.algafood.api.dto.request.OrderUpdateRequestDTO;
import com.victorkayk.algafood.api.dto.request.OrderUpdateStatusRequestDTO;
import com.victorkayk.algafood.api.dto.response.OrderResponseDTO;
import com.victorkayk.algafood.api.dto.response.OrderSimplifiedResponseDTO;
import com.victorkayk.algafood.api.mapper.OrderMapper;
import com.victorkayk.algafood.domain.model.Order;
import com.victorkayk.algafood.domain.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Orders", description = "Order endpoints")
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderSimplifiedResponseDTO>> list() {
        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok(orderMapper.toSimplifiedResponseDTO(orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(orderMapper.toResponseDTO(orderService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<OrderSimplifiedResponseDTO> save(@RequestBody OrderCreateRequestDTO dto) {
        Order order = orderMapper.createRequestDTOToEntity(dto);
        return new ResponseEntity<>(orderMapper.toSimplifiedResponseDTO(orderService.save(order)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderSimplifiedResponseDTO> update(@PathVariable Long id, @RequestBody OrderUpdateRequestDTO dto) {
        Order order = orderMapper.updateRequestDTOToEntity(dto);
        return ResponseEntity.ok(orderMapper.toSimplifiedResponseDTO(orderService.update(id, order)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderSimplifiedResponseDTO> updateStatus(@PathVariable Long id, @RequestBody OrderUpdateStatusRequestDTO dto) {
        orderService.updateStatus(id, dto.status());
        return ResponseEntity.noContent().build();
    }
}
