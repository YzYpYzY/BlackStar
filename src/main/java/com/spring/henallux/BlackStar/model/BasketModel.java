package com.spring.henallux.BlackStar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BasketModel {

    private HashMap<Integer, BasketLineModel> basket = new HashMap<Integer, BasketLineModel>();

    private int orderId = 0;
    private double totalAmount = 0;
    private double promotionAmount = 0;

    private boolean isExtend = false;


    public boolean isExtend() {
        return isExtend;
    }

    public void toggleExtend(){
        isExtend = !isExtend;
    }

    private void computeAmount(){
        totalAmount = 0;
        for (Integer id : basket.keySet()){
            totalAmount += basket.get(id).getTotalPrice();
        }
    }

    private void computePromotion() {
        if (basket.size() > 5) {
            promotionAmount = 0.2 * totalAmount;
        } else if (basket.size() >= 3) {
            promotionAmount = 0.1 * totalAmount;
        } else {
            promotionAmount = 0;
        }
    }

    private void refreshComputes(){
        computeAmount();
        computePromotion();
    }

    public int addNewProduct(ProductModel product, int quantity){
        basket.put(product.getId(), new BasketLineModel(product, quantity));
        refreshComputes();
        return 1;
    }

    public void addMore(Integer productId, int quantity){
        BasketLineModel basketLine = getBasketLine(productId);
        basketLine.addMore(quantity);
        refreshComputes();
    }

    public  void removeOne(Integer productId){
        BasketLineModel basketLine = getBasketLine(productId);
        if(basketLine.getQuantity() == 1) {
            remove(productId);
        }
        else{
            basketLine.removeOne();
        }
        refreshComputes();
    }

    public void remove(Integer productId){
        basket.remove(productId);
        refreshComputes();
    }

    public boolean isAlreadyIn(Integer id){
        return (getBasketLine(id))!= null;
    }

    public BasketLineModel getBasketLine(Integer id){
        return basket.getOrDefault(id, null);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public HashMap<Integer, BasketLineModel> getLines() {
        return basket;
    }

    public double getRealPrice(){
        return totalAmount - promotionAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int count(){
        int sum = 0;
        for(BasketLineModel basketLine : basket.values()){
            sum += basketLine.getQuantity();
        }
        return sum;
    }

    public double getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(double promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void clear(){
        basket.clear();
    }

    public void empty() {
        this.basket.clear();
        this.refreshComputes();
    }
}
