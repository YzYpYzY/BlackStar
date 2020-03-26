package com.spring.henallux.BlackStar.controller;

import com.spring.henallux.BlackStar.dataAccess.entity.UserEntity;
import com.spring.henallux.BlackStar.model.BasketLineModel;
import com.spring.henallux.BlackStar.model.ProductModel;
import com.spring.henallux.BlackStar.model.Session;
import com.spring.henallux.BlackStar.service.ProductOrderService;
import com.spring.henallux.BlackStar.service.ProductsService;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.spring.henallux.BlackStar.configuration.ConstantConfiguration.CURRENTUSER;
import static com.spring.henallux.BlackStar.configuration.ConstantConfiguration.SESSION;

@Controller
@RequestMapping(value = "/products")
@SessionAttributes({ CURRENTUSER, SESSION})
public class ProductsController extends BaseController {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductOrderService productOrderService;
    private UserEntity user = null;

    @RequestMapping (method = RequestMethod.GET)
    public String all (
            @ModelAttribute(value="session")Session session,
            Model model, Locale locale)
    {
        init(session,model,locale,"products");
        model.addAttribute("products", productsService.getProducts(locale));
        return "integrated:products";
    }

    @RequestMapping (value="/details/{productId}",method = RequestMethod.GET)
    public String details (
            @ModelAttribute(value="session")Session session,
            @PathVariable int productId,
            Model model,
            Locale locale)
    {
        session.setCurrentPage("products/details/" + productId);
        ProductModel product = productsService.getProduct(productId,locale);
        model.addAttribute("title", product.getLabel());
        model.addAttribute("product", product);
        BasketLineModel basketLine = session.getBasket().getBasketLine(productId);
        int quantity = 0;
        if (basketLine !=null) quantity = basketLine.getQuantity();
        model.addAttribute("quantity", quantity);
        return "integrated:product";
    }

    @RequestMapping (value="/add/{productId}",method = RequestMethod.GET)
    public String addProduct (
            @ModelAttribute(value="session")Session session,
            @PathVariable int productId,
            Locale locale,
            Authentication authentication)
    {
        initUser(authentication);
        productOrderService.add(session.getBasket(), productId, 1,locale, user);
        return  "redirect:/" + session.getCurrentPage();
    }

    @RequestMapping (value="/removeOne/{productId}",method = RequestMethod.GET)
    public String removeOneProduct (
            @ModelAttribute(value="session")Session session,
            @PathVariable int productId,
            Authentication authentication)
    {
        initUser(authentication);
        productOrderService.removeOne(session.getBasket(), productId, user);
        return  "redirect:/" + session.getCurrentPage();
    }

    @RequestMapping (value="/remove/{productId}",method = RequestMethod.GET)
    public String removeProduct (
            @ModelAttribute(value="session")Session session,
            @PathVariable int productId,
            Authentication authentication)
    {
        initUser(authentication);
        productOrderService.remove(session.getBasket(), productId, user);
        return  "redirect:/" + session.getCurrentPage();
    }

    private void initUser(Authentication authentication) {
        if(authentication != null) user = (UserEntity) authentication.getPrincipal();
    }

    @RequestMapping (value="/valid",method = RequestMethod.GET)
    public String valid(
            @ModelAttribute(value="session")Session session,
            Locale locale,
            Model model
    ){
        String title = messageSource.getMessage("title.valid", null, locale);
        model.addAttribute("title",title);
        model.addAttribute("locale",locale.toString());
        return "integrated:valid";
    }
    @RequestMapping (value="/pay",method = RequestMethod.POST)
    public String pay(
            @ModelAttribute(value="session")Session session,
            Authentication authentication,
            @RequestParam("stripeToken") String stripeToken
    ){
        initUser(authentication);
        // Set your secret key: remember to change this to your live secret key in production
        // See your keys here: https://dashboard.stripe.com/account/apikeys
        Stripe.apiKey = "sk_test_TVLuTcHauh7vRlTyQ5KsuFTA";

        Map<String, Object> params = new HashMap<>();
        params.put("amount", Math.round(session.getBasket().getRealPrice()*100));
        params.put("currency", "eur");
        params.put("description", "Blackstar command");
        params.put("source", stripeToken);
        try{
            Charge charge = Charge.create(params);
            session.getBasket().empty();
            productOrderService.valid(user.getUsername());
        }catch(Exception e){
            System.out.print(e.toString());
        }

        return  "redirect:/products";
    }
}
