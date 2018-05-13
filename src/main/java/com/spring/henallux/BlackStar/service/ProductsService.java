package com.spring.henallux.BlackStar.service;

import com.spring.henallux.BlackStar.dataAccess.dao.ProductDAO;
import com.spring.henallux.BlackStar.dataAccess.entity.ProductEntity;
import com.spring.henallux.BlackStar.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

@Service
public class ProductsService extends BaseService {

    @Autowired
    private ProductDAO productDAO;

    public ProductModel getProduct(Integer id, Locale locale){ return productDAO.get(id, locale); }
    public ArrayList<ProductModel> getProducts(Locale locale){
        return productDAO.getAll(locale);
    }
}
