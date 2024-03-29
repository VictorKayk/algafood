package com.victorkayk.algafood.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "shipping_fee", nullable = false)
    private BigDecimal shippingFee;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @Column(name = "is_open", nullable = false)
    private Boolean isOpen = false;

    @ManyToOne
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @Embedded
    private Address address;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "restaurant_payment_methods",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id")
    )
    private Set<PaymentMethod> paymentMethods;

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products;

    @ManyToMany
    @JoinTable(
            name = "restaurant_users",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    public void activate() {
        setIsActive(true);
    }

    public void deactivate() {
        setIsActive(false);
    }

    public void open() {
        setIsOpen(true);
    }

    public void close() {
        setIsOpen(false);
    }

    public void associatePaymentMethod(PaymentMethod paymentMethod) {
        getPaymentMethods().add(paymentMethod);
    }

    public void disassociatePaymentMethod(PaymentMethod paymentMethod) {
        getPaymentMethods().remove(paymentMethod);
    }

    public void associateUser(User user) {
        getUsers().add(user);
    }

    public void disassociateUser(User user) {
        getUsers().remove(user);
    }

    public Boolean isPaymentMethodAvailable(PaymentMethod paymentMethod) {
        return this.paymentMethods.contains(paymentMethod);
    }

    public Boolean isPaymentMethodNotAvailable(PaymentMethod paymentMethod) {
        return !isPaymentMethodAvailable(paymentMethod);
    }

    public boolean isProductAvailable(Product product) {
        return this.products.contains(product);
    }

    public boolean isProductNotAvailable(Product product) {
        return !isProductAvailable(product);
    }
}
