package com.spring.henallux.BlackStar.controller;

import com.spring.henallux.BlackStar.dataAccess.entity.UserEntity;
import com.spring.henallux.BlackStar.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.Authentication;

import java.util.Locale;

@Controller
@RequestMapping(value = "/home")
public class HomeController extends BaseController{

    @RequestMapping (method = RequestMethod.GET)
    public String home (@ModelAttribute(value="session")Session session,Model model, Locale locale){
        init(session,model,locale,"home");
        return "integrated:home";
    }
}
