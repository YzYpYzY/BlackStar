package com.spring.henallux.BlackStar.dataAccess.dao;

import com.spring.henallux.BlackStar.dataAccess.entity.OrderLineEntity;
import com.spring.henallux.BlackStar.dataAccess.entity.ProductEntity;
import com.spring.henallux.BlackStar.dataAccess.entity.ProductOrderEntity;
import com.spring.henallux.BlackStar.dataAccess.entity.UserEntity;
import com.spring.henallux.BlackStar.dataAccess.repository.OrderLineRepository;
import com.spring.henallux.BlackStar.dataAccess.repository.ProductOrderRepository;
import com.spring.henallux.BlackStar.dataAccess.repository.ProductRepository;
import com.spring.henallux.BlackStar.dataAccess.util.Converter;
import com.spring.henallux.BlackStar.dataAccess.util.OrderState;
import com.spring.henallux.BlackStar.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Locale;

@Service
@Transactional
public class OrderLineDAO {
    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Converter converter;

    public ProductModel addNew (int productId, UserEntity user, int quantity, Locale locale) {
        ProductOrderEntity order = productOrderRepository.findFirst(user.getUsername(), OrderState.PREPARE);
        if(order == null) {
            order = new ProductOrderEntity();
            order.setUser(user);
            order.setDate(new Date());
            order.setState(OrderState.PREPARE);
            productOrderRepository.save(order);
        }
        ProductEntity product = productRepository.findById(productId);
        OrderLineEntity orderLine = new OrderLineEntity();
        orderLine.setOrder(order);
        orderLine.setProduct(product);
        orderLine.setQuantity(quantity);
        orderLine.setPayedprice(product.getPrice());
        orderLineRepository.save(orderLine);
        return converter.productEntityToProductModel(product, locale);
    }
    

    public void update(int productId, String username, int newQuantity) {
        ProductOrderEntity order = productOrderRepository.findFirst(username, OrderState.PREPARE);
        for (OrderLineEntity orderLine : order.getOrderLines()){
            if(orderLine.getProduct().getId() == productId){
                orderLine.setQuantity(newQuantity);
                orderLineRepository.save(orderLine);
                return;
            }
        }
    }

    public void remove(int productId, UserEntity user) {
        ProductOrderEntity order = productOrderRepository.findFirst(user.getUsername(), OrderState.PREPARE);
        for (OrderLineEntity orderLine : order.getOrderLines()){
            if(orderLine.getProduct().getId() == productId){
                if(order.getOrderLines().size() == 1){
                    productOrderRepository.delete(order);
                }else {
                    order.getOrderLines().remove(orderLine);
                    orderLineRepository.delete(orderLine.getId());
                }
                break;
            }
        }
    }

    public void removeOne(int productId, UserEntity user) {
        ProductOrderEntity order = productOrderRepository.findFirst(user.getUsername(), OrderState.PREPARE);
        for (OrderLineEntity orderLine : order.getOrderLines()){
            if(orderLine.getProduct().getId() == productId){
                if(orderLine.getQuantity() == 1) {
                    if(order.getOrderLines().size() == 1){
                        productOrderRepository.delete(order);
                    }else{
                        order.getOrderLines().remove(orderLine);
                        orderLineRepository.delete(orderLine.getId());
                    }
                }else{
                    orderLine.setQuantity(orderLine.getQuantity()-1);
                    orderLineRepository.save(orderLine);
                }
                return;
            }
        }
    }
}
