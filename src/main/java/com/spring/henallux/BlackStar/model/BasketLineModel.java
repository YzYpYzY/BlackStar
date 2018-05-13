package com.spring.henallux.BlackStar.model;

public class BasketLineModel {

    private ProductModel product;

    private int quantity;

    public BasketLineModel (ProductModel product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public int getProductId() {
        return product.getId();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return product.getPrice();
    }

    public double getTotalPrice(){
        return quantity * product.getPrice();
    }

    public void addMore(int quantity){
        this.quantity += quantity;
    }
    public void removeOne (){
        quantity --;
    }

    public String getLabel() {
        return product.getLabel();
    }

    public void setLabel(String label) {
        product.setLabel(label);
    }
}
