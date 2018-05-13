package com.spring.henallux.BlackStar.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class BasketModelTest {

    private BasketModel basket = new BasketModel();

    @Before
    public void setUp() throws Exception {
        this.basket.addNewProduct( new ProductModel(1, 22), 1);
        this.basket.addNewProduct( new ProductModel(2,200), 1);
    }

    @Test
    public void TestTotalAmountAndPromotionCompute() throws Exception {
        Assert.assertEquals(222, this.basket.getTotalAmount(), 5);
        Assert.assertEquals(0, this.basket.getPromotionAmount(), 5);
        this.basket.addNewProduct( new ProductModel(3,100), 1);
        this.basket.addNewProduct( new ProductModel(4,30), 1);
        Assert.assertEquals(352, this.basket.getTotalAmount(), 5);
        Assert.assertEquals(35.2, this.basket.getPromotionAmount(), 5);
        this.basket.addNewProduct( new ProductModel(5,100), 1);
        this.basket.addNewProduct( new ProductModel(6,400), 1);
        this.basket.addNewProduct( new ProductModel(7,350), 1);
        Assert.assertEquals(1200, this.basket.getTotalAmount(), 5);
        Assert.assertEquals(240, this.basket.getPromotionAmount(), 5);
    }

}