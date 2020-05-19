package com.spring.henallux.BlackStar.controller;

import com.spring.henallux.BlackStar.model.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController{

    @RequestMapping (value={ "/home", "/"}, method = RequestMethod.GET)
    public String home (@ModelAttribute(value="session")Session session,Model model, Locale locale){
        init(session,model,locale,"home");
        return "integrated:home";
    }
}
