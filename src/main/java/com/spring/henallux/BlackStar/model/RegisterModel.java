package com.spring.henallux.BlackStar.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RegisterModel {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Pattern(regexp="^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", message="{validation.email}")
    private String email;

    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message="{validation.password}")
    private String password;

    @NotEmpty
    private String passwordConfirm;

    @NotEmpty
    private String deliveredAddress;

    @NotEmpty
    @Pattern(regexp="(^$|[0-9]{10})", message="{validation.phone}")
    private String phone;

    private String vatNum;
    private String society;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getDeliveredAddress() {
        return deliveredAddress;
    }

    public void setDeliveredAddress(String deliveredAddress) {
        this.deliveredAddress = deliveredAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVatNum() {
        return vatNum;
    }

    public void setVatNum(String vatnum) {
        this.vatNum = vatnum;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
