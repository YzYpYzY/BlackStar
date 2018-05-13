package com.spring.henallux.BlackStar.dataAccess.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="language")
public class LanguageEntity {
    @Id
    @GeneratedValue
    private int id;

    private String isocode;

    @OneToMany(mappedBy="language", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Collection<LanguageLabelEntity> labels;

    @OneToMany(mappedBy="languagetrad", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Collection<LanguageLabelEntity> labelsinlanguage;

    @OneToMany(mappedBy="language", fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
    private Collection<UserEntity> usersthatprefer;

    @OneToMany(mappedBy="language", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Collection<ProductLabelEntity> productlabels;

    @OneToMany(mappedBy="language", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Collection<DescriptionEntity> descriptions;

    public LanguageEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<LanguageLabelEntity> getLabels() {
        return labels;
    }

    public void setLabels(Collection<LanguageLabelEntity> labels) {
        this.labels = labels;
    }

    public Collection<DescriptionEntity> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Collection<DescriptionEntity> descriptions) {
        this.descriptions = descriptions;
    }

    public Collection<UserEntity> getUsersthatprefer() {
        return usersthatprefer;
    }

    public void setUsersthatprefer(Collection<UserEntity> usersthatprefer) {
        this.usersthatprefer = usersthatprefer;
    }

    public Collection<ProductLabelEntity> getProductlabels() {
        return productlabels;
    }

    public void setProductlabels(Collection<ProductLabelEntity> productlabels) {
        this.productlabels = productlabels;
    }

    public String getIsocode() {
        return isocode;
    }

    public void setIsocode(String isocode) {
        this.isocode = isocode;
    }
}
