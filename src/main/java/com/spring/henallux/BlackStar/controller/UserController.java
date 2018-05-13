package com.spring.henallux.BlackStar.controller;

import com.spring.henallux.BlackStar.dataAccess.entity.UserEntity;
import com.spring.henallux.BlackStar.model.RegisterModel;
import com.spring.henallux.BlackStar.model.Session;
import com.spring.henallux.BlackStar.service.ProductOrderService;
import com.spring.henallux.BlackStar.service.ServiceResponse;
import com.spring.henallux.BlackStar.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

import java.util.Locale;

import static com.spring.henallux.BlackStar.configuration.ConstantConfiguration.CURRENTUSER;
import static com.spring.henallux.BlackStar.configuration.ConstantConfiguration.SESSION;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    @Autowired
    private UsersService usersService;
    @Autowired
    private ProductOrderService productOrderService;

    @RequestMapping (value="/login", method = RequestMethod.GET)
    public String login (@ModelAttribute(value="session")Session session,Model model){
        model.addAttribute("title","Login");
        model.addAttribute("userEntity", new UserEntity());
        return "integrated:login";
    }

    @RequestMapping (value="/register", method = RequestMethod.GET)
    public String register (@ModelAttribute(value="session")Session session,Model model){
        model.addAttribute("title","Register");
        model.addAttribute("registerModel", new RegisterModel());
        return "integrated:register";
    }


    @RequestMapping (value="/register", method = RequestMethod.POST)
    public String register (@ModelAttribute(value="session")Session session,Model model, @Valid @ModelAttribute(value="registerModel") RegisterModel registerModel, final BindingResult errors){
        if(!registerModel.getPassword().equals(registerModel.getPasswordConfirm())) {
            String[] errorCodes = new String[]{"PasswordConfirm.NotSame"};
            errors.addError(new FieldError("DifferentPasswordError", "passwordConfirm", null, false , errorCodes,null,""));
        }else{
            errors.recordSuppressedField("DifferentPasswordError");
        }

        if(errors.hasErrors()){
            return "integrated:register";
        }

        ServiceResponse response = usersService.register(registerModel);

        if(!response.isSuccess()){
            model.addAttribute("generalErrors", response.getError());
            return "integrated:register";
        }

        return "redirect:/home";
    }

    @RequestMapping (value="/loginCallBack", method = RequestMethod.GET)
    public String loginCallBack (@ModelAttribute(value="session")Session session,Model model,Authentication authentication, Locale locale){
        productOrderService.sync(session.getBasket(), (UserEntity) authentication.getPrincipal(),locale);
        return "redirect:/home";
    }
}
