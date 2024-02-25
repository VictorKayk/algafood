package com.victorkayk.algafood.domain.model;

import com.victorkayk.algafood.domain.enums.StatusOrderEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "shipping_fee")
    private BigDecimal shippingFee;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "confirmed_at")
    private OffsetDateTime confirmedAt;

    @Column(name = "canceled_at")
    private OffsetDateTime canceledAt;

    @Column(name = "delivered_at")
    private OffsetDateTime deliveredAt;

    @Column(name = "status", nullable = false)
    private StatusOrderEnum status = StatusOrderEnum.CREATED;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items;

    @PrePersist
    private void setUuid() {
        this.uuid = UUID.randomUUID().toString();
    }

    public void setOrderToOrderItems() {
        items.forEach(item -> item.setOrder(this));
    }

    public BigDecimal calculateSubtotal() {
        setSubtotal(
                items.stream()
                        .map(OrderItem::getTotalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        return this.subtotal;
    }

    public BigDecimal calculateShippingFee() {
        setShippingFee(restaurant.getShippingFee());
        return this.shippingFee;
    }

    public BigDecimal calculateTotal() {
        setTotal(calculateSubtotal().add(calculateShippingFee()));
        return total;
    }

    public boolean isUpdateStatusValid(StatusOrderEnum status) {
        if (this.getStatus().equals(StatusOrderEnum.CREATED)) {
            return status.equals(StatusOrderEnum.CONFIRMED) || status.equals(StatusOrderEnum.CANCELED);
        } else if (this.getStatus().equals(StatusOrderEnum.CONFIRMED)) {
            return status.equals(StatusOrderEnum.DELIVERED);
        }
        return false;
    }

    public boolean isUpdateStatusNotValid(StatusOrderEnum status) {
        return !isUpdateStatusValid(status);
    }

    public void setStatus(StatusOrderEnum status) {
        if (isUpdateStatusValid(status)) this.status = status;
    }

    public void confirm() {
        setStatus(StatusOrderEnum.CONFIRMED);
        setConfirmedAt(OffsetDateTime.now());
    }

    public void deliver() {
        setStatus(StatusOrderEnum.DELIVERED);
        setDeliveredAt(OffsetDateTime.now());
    }

    public void cancel() {
        setStatus(StatusOrderEnum.CANCELED);
        setCanceledAt(OffsetDateTime.now());
    }
}
