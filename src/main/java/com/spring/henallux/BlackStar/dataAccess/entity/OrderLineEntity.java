package com.spring.henallux.BlackStar.dataAccess.entity;

import org.hibernate.Session;

import javax.persistence.*;

@Entity
@Table(name="orderline")
public class OrderLineEntity {

    @Id
    @GeneratedValue
    private int id;

    private int quantity;

    private double payedprice;

    @JoinColumn(name="productid", referencedColumnName="id")
    @ManyToOne
    private ProductEntity product;

    @JoinColumn(name="productorderid", referencedColumnName="id")
    @ManyToOne
    private ProductOrderEntity order;

    public OrderLineEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPayedprice() {
        return payedprice;
    }

    public void setPayedprice(double payedprice) {
        this.payedprice = payedprice;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public ProductOrderEntity getOrder() {
        return order;
    }

    public void setOrder(ProductOrderEntity order) {
        this.order = order;
    }
}
