package com.spring.henallux.BlackStar.controller;

import com.spring.henallux.BlackStar.configuration.ConstantConfiguration;
import com.spring.henallux.BlackStar.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Locale;

import static com.spring.henallux.BlackStar.configuration.ConstantConfiguration.CURRENTUSER;
import static com.spring.henallux.BlackStar.configuration.ConstantConfiguration.SESSION;

@Controller
@SessionAttributes({ CURRENTUSER, SESSION})
public class BaseController {

    @ModelAttribute(ConstantConfiguration.SESSION)
    public Session Session(){
        return  new Session();
    }

    @Autowired
    protected MessageSource messageSource;

    public void init(Session session, Model model, Locale locale, String name){
        session.setCurrentPage(name);
        String title = messageSource.getMessage("title."+name, null, locale);
        model.addAttribute("title",title);
    }
}
