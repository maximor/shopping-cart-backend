package com.personal.shoppingcartrest.order;

import com.personal.shoppingcartrest.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "order_line")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "outstanding_amount", nullable = false)
    private float outstandingAmount = 0;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderLine() {
    }

    public OrderLine(int quantity, float outstandingAmount, Order order, Product product) {
        this.quantity = quantity;
        this.outstandingAmount = outstandingAmount;
        this.order = order;
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(float outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
