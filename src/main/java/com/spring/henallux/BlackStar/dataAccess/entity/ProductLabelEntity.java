package com.spring.henallux.BlackStar.dataAccess.entity;

import javax.persistence.*;

@Entity
@Table(name="productlabel")
public class ProductLabelEntity {

    @Id
    @GeneratedValue
    private int id;

    private String content;

    @JoinColumn(name="languageid", referencedColumnName="id")
    @ManyToOne
    private LanguageEntity language;

    @JoinColumn(name="productid", referencedColumnName="id")
    @ManyToOne
    private ProductEntity product;

    public ProductLabelEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
