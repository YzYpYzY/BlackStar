package com.spring.henallux.BlackStar.dataAccess.dao;

import com.spring.henallux.BlackStar.dataAccess.entity.*;
import com.spring.henallux.BlackStar.dataAccess.repository.ProductOrderRepository;
import com.spring.henallux.BlackStar.dataAccess.repository.ProductRepository;
import com.spring.henallux.BlackStar.dataAccess.util.Converter;
import com.spring.henallux.BlackStar.dataAccess.util.OrderState;
import com.spring.henallux.BlackStar.model.BasketLineModel;
import com.spring.henallux.BlackStar.model.BasketModel;
import com.spring.henallux.BlackStar.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ProductOrderDAO {

    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Converter converter;

    public ProductOrderEntity getOrderInPreparation(String username) {
        return productOrderRepository.findFirst(username, OrderState.PREPARE);
    }

    public void sync(BasketModel basket, UserEntity user, Locale locale) {
        if (basket.getLines().size() == 0) {
            loadBasketFromDb(basket, user, locale);
        } else {
            saveBasketToDb(basket, user);
            basket.clear();
            loadBasketFromDb(basket, user, locale);
        }
    }

    private void saveBasketToDb(BasketModel basket, UserEntity user) {
        ProductOrderEntity order = getOrderInPreparation(user.getUsername());
        if (order == null) {
            order = new ProductOrderEntity();
            order.setUser(user);
            order.setDate(new Date());
            order.setState(OrderState.PREPARE);
        }
        order.setPromotionamount(basket.getPromotionAmount());

        HashMap<Integer, OrderLineEntity> orderLines = converter.basketLinesToOrderLines(basket.getLines());
        order.mergeOrderLines(orderLines);

        productOrderRepository.save(order);
    }

    private void loadBasketFromDb(BasketModel basket, UserEntity user, Locale locale) {
        ProductOrderEntity order = getOrderInPreparation(user.getUsername());
        if (order == null) return;
        for (OrderLineEntity orderLine : order.getOrderLines()) {
            if (basket.isAlreadyIn(orderLine.getProduct().getId())) {
                basket.addMore(orderLine.getProduct().getId(), orderLine.getQuantity());
            } else {
                basket.addNewProduct(converter.productEntityToProductModel(orderLine.getProduct(), locale), orderLine.getQuantity());
            }
        }
    }

    public void refreshTrad(BasketModel basket, Locale locale) {
        HashMap<Integer, BasketLineModel> basketLines = basket.getLines();
        Iterator basketIt = basketLines.entrySet().iterator();
        while (basketIt.hasNext()) {
            Map.Entry entry = (Map.Entry) basketIt.next();
            BasketLineModel bLine = (BasketLineModel) entry.getValue();
            ProductEntity product = productRepository.findById(bLine.getProductId());
            for (ProductLabelEntity label : product.getLabels()) {
                if (label.getLanguage().getIsocode().equals(locale.toString())) {
                   bLine.setLabel(label.getContent());
                    break;
                }
            }
        }
    }

    public void save(ProductOrderEntity pO) {
        productOrderRepository.save(pO);
    }
}
