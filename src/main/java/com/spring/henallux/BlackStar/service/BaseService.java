package com.spring.henallux.BlackStar.service;

import org.springframework.stereotype.Service;

@Service
public class BaseService {

    public ServiceResponse Ok(){
        ServiceResponse response = new ServiceResponse();
        response.setSuccess(true);
        return response;
    }

    public ServiceResponse Ok(Object value){
        ServiceResponse response = new ServiceResponse();
        response.setSuccess(true);
        response.setValue(value);
        return response;
    }

    public ServiceResponse Error(String error){
        ServiceResponse response = new ServiceResponse();
        response.setSuccess(false);
        response.setError(error);
        return response;
    }

}
