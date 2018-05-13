package com.spring.henallux.BlackStar.dataAccess.util;

import com.spring.henallux.BlackStar.dataAccess.entity.*;
import com.spring.henallux.BlackStar.dataAccess.repository.LanguageRepository;
import com.spring.henallux.BlackStar.dataAccess.repository.ProductRepository;
import com.spring.henallux.BlackStar.dataAccess.repository.UserRepository;
import com.spring.henallux.BlackStar.model.BasketLineModel;
import com.spring.henallux.BlackStar.model.ProductModel;
import com.spring.henallux.BlackStar.model.RegisterModel;
import org.apache.tiles.locale.LocaleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Converter {

    @Autowired
    private LanguageRepository languageRepo;

    @Autowired
    private ProductRepository productRepo;

    public UserEntity registerModelToEntity(RegisterModel model){
        UserEntity user = new UserEntity();
        user.setUsername(model.getEmail());
        user.setFirstname(model.getFirstName());
        user.setLastname(model.getLastName());
        user.setEmail(model.getEmail());
        user.setPassword(model.getPassword());
        user.setDeliveredaddress(model.getDeliveredAddress());
        user.setPhone(model.getPhone());
        user.setVatnum(model.getVatNum());
        user.setSociety(model.getSociety());
        return user;
    }

    public ArrayList<ProductModel> productsEntityToProductsModel(ArrayList<ProductEntity> products, Locale locale) {
        ArrayList<ProductModel> productModels = new ArrayList<ProductModel>();
        for(ProductEntity product : products){
            productModels.add(productEntityToProductModelTrad(product,  locale.toString()));
        }
        return productModels;
    }

    public ProductModel productEntityToProductModel(ProductEntity product, Locale locale) {
        return productEntityToProductModelTrad(product,  locale.toString());
    }

    private ProductModel productEntityToProductModelTrad(ProductEntity product, String iso){
        ProductModel pModel = new ProductModel();
        pModel.setId(product.getId());
        pModel.setPrice(product.getPrice());
        pModel.setPhoto(product.getPhoto());
        for(ProductLabelEntity label : product.getLabels()){
            if(label.getLanguage().getIsocode().equals(iso)){
                pModel.setLabel(label.getContent());
                break;
            }
        }
        for(DescriptionEntity description : product.getDescriptions()){
            if(description.getLanguage().getIsocode().equals(iso)){
                pModel.setDescription(description.getContent());
                break;
            }
        }
        return pModel;
    }

    public HashMap<Integer, OrderLineEntity> basketLinesToOrderLines(HashMap<Integer, BasketLineModel> lines) {
        HashMap<Integer, OrderLineEntity> orderLines = new  HashMap<Integer, OrderLineEntity>();
        Iterator basketIt = lines.entrySet().iterator();
        while (basketIt.hasNext()) {
            Map.Entry entry = (Map.Entry) basketIt.next();
            BasketLineModel bLine = (BasketLineModel) entry.getValue();
            OrderLineEntity orderLine = new OrderLineEntity();
            orderLine.setQuantity(bLine.getQuantity());
            orderLine.setPayedprice(bLine.getPrice());
            orderLine.setProduct(productRepo.findById(bLine.getProductId()));
            orderLines.put(orderLine.getProduct().getId(),orderLine);
        }
        return orderLines;
    }
}
