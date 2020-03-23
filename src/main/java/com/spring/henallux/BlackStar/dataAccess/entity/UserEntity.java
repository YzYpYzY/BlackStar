package com.spring.henallux.BlackStar.dataAccess.entity;

import com.spring.henallux.BlackStar.dataAccess.util.Role;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="user")
public class UserEntity implements UserDetails{

    @Id
    @Column(name = "username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="authorities")
    private String authorities = "";

    @Column(name="accountnonexpired")
    private Boolean accountNonExpired = true;

    @Column(name="accountnonlocked")
    private Boolean accountNonLocked = true;

    @Column(name="credentialsnonexpired")
    private Boolean credentialsNonExpired = true;

    @Column(name="enabled")
    private Boolean enabled = true;

    private String firstname;

    private String lastname;

    private String email;

    private String deliveredaddress;

    private String phone;

    private String vatnum;

    private String society;

    @JoinColumn(name="languageid", referencedColumnName="id")
    @ManyToOne
    private LanguageEntity language;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Collection<ProductOrderEntity> orders;

    public UserEntity(){}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveredaddress() {
        return deliveredaddress;
    }

    public void setDeliveredaddress(String deliveredaddress) {
        this.deliveredaddress = deliveredaddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVatnum() {
        return vatnum;
    }

    public void setVatnum(String vatnum) {
        this.vatnum = vatnum;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }

    public Collection<ProductOrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Collection<ProductOrderEntity> orders) {
        this.orders = orders;
    }

    public void setUsername(String username){
        this.username = username;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public ArrayList<Role> getAuthorities() {
        String[] authoritiesArray = this.authorities.split(",");
        ArrayList<Role> authoritiesCollection = new ArrayList<Role>();

        for(String role : authoritiesArray){
            authoritiesCollection.add(new Role(role));
        }
        return authoritiesCollection;
    }
}
