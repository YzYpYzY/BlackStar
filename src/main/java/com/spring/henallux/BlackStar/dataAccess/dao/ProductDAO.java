package com.spring.henallux.BlackStar.dataAccess.dao;

import com.spring.henallux.BlackStar.dataAccess.entity.LanguageEntity;
import com.spring.henallux.BlackStar.dataAccess.entity.ProductEntity;
import com.spring.henallux.BlackStar.dataAccess.entity.UserEntity;
import com.spring.henallux.BlackStar.dataAccess.repository.LanguageRepository;
import com.spring.henallux.BlackStar.dataAccess.repository.ProductRepository;
import com.spring.henallux.BlackStar.dataAccess.repository.UserRepository;
import com.spring.henallux.BlackStar.dataAccess.util.Converter;
import com.spring.henallux.BlackStar.model.ProductModel;
import com.spring.henallux.BlackStar.model.RegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Locale;

@Service
@Transactional
public class ProductDAO {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Converter converter;

    public ProductModel get(Integer id,Locale locale) {
        ProductEntity product = productRepository.findById(id);
        return converter.productEntityToProductModel(product, locale);
    }

    public ArrayList<ProductModel> getAll(Locale locale) {
        ArrayList<ProductEntity> products = productRepository.findAll();
        return converter.productsEntityToProductsModel(products, locale);
    }

}
