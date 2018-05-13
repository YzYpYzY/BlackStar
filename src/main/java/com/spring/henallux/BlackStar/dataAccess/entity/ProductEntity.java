package com.spring.henallux.BlackStar.dataAccess.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="product")
public class ProductEntity {

    @Id
    @GeneratedValue
    private int id;

    private double price;

    private String photo;

    @OneToMany(mappedBy="product", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Collection<OrderLineEntity> orderLines;

    @OneToMany(mappedBy="product", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Collection<ProductLabelEntity> labels;

    @OneToMany(mappedBy="product", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Collection<DescriptionEntity> descriptions;

    public ProductEntity() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Collection<OrderLineEntity> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Collection<OrderLineEntity> orderLines) {
        this.orderLines = orderLines;
    }

    public Collection<ProductLabelEntity> getLabels() {
        return labels;
    }

    public void setLabels(Collection<ProductLabelEntity> labels) {
        this.labels = labels;
    }

    public Collection<DescriptionEntity> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Collection<DescriptionEntity> descriptions) {
        this.descriptions = descriptions;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
