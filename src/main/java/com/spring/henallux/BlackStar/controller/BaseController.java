package com.spring.henallux.BlackStar.controller;

import com.spring.henallux.BlackStar.configuration.ConstantConfiguration;
import com.spring.henallux.BlackStar.model.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import static com.spring.henallux.BlackStar.configuration.ConstantConfiguration.CURRENTUSER;
import static com.spring.henallux.BlackStar.configuration.ConstantConfiguration.SESSION;

@Controller
@SessionAttributes({ CURRENTUSER, SESSION})
public class BaseController {

    @ModelAttribute(ConstantConfiguration.SESSION)
    public Session Session(){
        return  new Session();
    }
}
