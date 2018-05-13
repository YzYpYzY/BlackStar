package com.spring.henallux.BlackStar.service;

import com.spring.henallux.BlackStar.dataAccess.dao.UserDAO;
import com.spring.henallux.BlackStar.dataAccess.entity.UserEntity;
import com.spring.henallux.BlackStar.model.RegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService extends BaseService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ServiceResponse register(RegisterModel model){
        if(userDAO.findByUserName(model.getEmail()) != null) {
            return Error("Email already in use");
        }
        model.setPasswordConfirm("");
        model.setPassword(passwordEncoder.encode(model.getPassword()));

        UserEntity user = userDAO.save(model);

        return Ok(user);
    }
}
