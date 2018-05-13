package com.spring.henallux.BlackStar.dataAccess.util;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    private String name;

    public Role(){}

    public Role(String name){
        this.name = name;
    }

    public String getAuthority() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
