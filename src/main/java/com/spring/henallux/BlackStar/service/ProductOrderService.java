package com.spring.henallux.BlackStar.service;

import com.spring.henallux.BlackStar.dataAccess.dao.OrderLineDAO;
import com.spring.henallux.BlackStar.dataAccess.dao.ProductDAO;
import com.spring.henallux.BlackStar.dataAccess.dao.ProductOrderDAO;
import com.spring.henallux.BlackStar.dataAccess.entity.OrderLineEntity;
import com.spring.henallux.BlackStar.dataAccess.entity.ProductEntity;
import com.spring.henallux.BlackStar.dataAccess.entity.ProductOrderEntity;
import com.spring.henallux.BlackStar.dataAccess.entity.UserEntity;
import com.spring.henallux.BlackStar.dataAccess.util.OrderState;
import com.spring.henallux.BlackStar.model.BasketModel;
import com.spring.henallux.BlackStar.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProductOrderService extends BaseService  {

    @Autowired
    private OrderLineDAO orderLineDAO;

    @Autowired
    private ProductOrderDAO productOrderDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductsService productsService;

    public void add(BasketModel basket, int productId, int quantity, Locale locale, UserEntity user){
        if(basket.isAlreadyIn(productId)){
            basket.addMore(productId, quantity);
            if(user != null) orderLineDAO.update(productId, user.getUsername(), quantity);
        }
        else{
            ProductModel product;
            // User null if not authenticated
            if (user != null){
                product = orderLineDAO.addNew(productId, user, quantity, locale);
            }else{
                product = productDAO.get(productId, locale);
            }
            basket.addNewProduct(product, quantity);
        }
    }
    public void removeOne(BasketModel basket, int productId,  UserEntity user){
        if(basket.getBasketLine(productId) != null){
        // User null if not authenticated
        if (user != null){
                orderLineDAO.removeOne(productId, user);
        }
            basket.removeOne(productId);
        }
    }
    public void remove(BasketModel basket, int productId, UserEntity user){
        basket.remove(productId);
        // User null if not authenticated
        if (user != null) {
            orderLineDAO.remove(productId, user);
        }
    }

    public void sync(BasketModel basket, UserEntity user, Locale locale) {
        productOrderDAO.sync( basket,  user, locale);
    }

    public void refreshTrad(BasketModel basket,  Locale locale){
        productOrderDAO.refreshTrad(basket,  locale);
    }

    public void valid(String username){
        ProductOrderEntity pO = productOrderDAO.getOrderInPreparation(username);
        pO.setState(OrderState.ORDER);
        productOrderDAO.save(pO);
    }
}
