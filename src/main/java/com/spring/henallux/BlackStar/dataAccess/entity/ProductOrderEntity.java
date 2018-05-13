package com.spring.henallux.BlackStar.dataAccess.entity;

import com.spring.henallux.BlackStar.dataAccess.util.OrderState;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="productorder")
public class ProductOrderEntity {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private double promotionamount;

    private Date date;

    @JoinColumn(name="userid", referencedColumnName="username")
    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy="order", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Collection<OrderLineEntity> orderLines;

    public ProductOrderEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Enumerated(EnumType.STRING)
    public OrderState getState() {
        return state;
    }
    @Enumerated(EnumType.STRING)
    public void setState(OrderState state) {
        this.state = state;
    }

    public double getPromotionamount() {
        return promotionamount;
    }

    public void setPromotionamount(double promotionamount) {
        this.promotionamount = promotionamount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Collection<OrderLineEntity> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Collection<OrderLineEntity> orderLines) {
        this.orderLines = orderLines;
    }

    public void mergeOrderLines(HashMap<Integer, OrderLineEntity> orderLinesFromSession) {
        for (OrderLineEntity orderLine : orderLines){
            int productId = orderLine.getProduct().getId();

            if(orderLinesFromSession.containsKey(productId)){
                orderLine.setQuantity(orderLinesFromSession.get(productId).getQuantity() + orderLine.getQuantity());
                orderLinesFromSession.remove(productId);
            }
        }
        Iterator basketIt = orderLinesFromSession.entrySet().iterator();
        while (basketIt.hasNext()) {
            Map.Entry entry = (Map.Entry) basketIt.next();
            OrderLineEntity oLine = (OrderLineEntity) entry.getValue();
            orderLines.add(oLine);
        }
    }
}
