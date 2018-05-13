package com.spring.henallux.BlackStar.service;

public class ServiceResponse{

    private boolean success = true;

    private Object value = null;

    private String error = "";

    public ServiceResponse(){

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}