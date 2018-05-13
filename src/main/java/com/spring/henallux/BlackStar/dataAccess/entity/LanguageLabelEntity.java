package com.spring.henallux.BlackStar.dataAccess.entity;

import javax.persistence.*;

@Entity
@Table(name="languagelabel")
public class LanguageLabelEntity {

    @Id
    @GeneratedValue
    private int id;

    private String content;

    @JoinColumn(name="languageid", referencedColumnName="id")
    @ManyToOne
    private LanguageEntity language;

    @JoinColumn(name="languagetradid", referencedColumnName="id")
    @ManyToOne
    private LanguageEntity languagetrad;

    public LanguageLabelEntity(){}

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
}
