package com.spring.henallux.BlackStar.model;


import com.spring.henallux.BlackStar.configuration.ConstantConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
public class Session {

    private String currentPage = "home";

    private BasketModel basket = new BasketModel();

    public BasketModel getBasket() {
        return basket;
    }

    public void setBasket(BasketModel basket) {
        this.basket = basket;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
}
