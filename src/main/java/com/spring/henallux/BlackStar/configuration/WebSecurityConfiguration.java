package com.spring.henallux.BlackStar.configuration;

import com.spring.henallux.BlackStar.dataAccess.entity.UserEntity;
import com.spring.henallux.BlackStar.model.Session;
import com.spring.henallux.BlackStar.service.ProductOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_REQUEST = "/user/login";
    private static final String[] AUTHORIZED_REQUESTS_ANYBODY = new String[]{
            "/",
            "/home",
            "/css/stylesheets/*",
            "/images/*",
            "/images/coffees/*",
            "/user/register",
            "/products",
            "/products/*/*"
    };
    private static final String[] AUTHORIZED_REQUESTS_ADMIN = new String[]{"/admin"};

    private UserDetailsService userDetailsServiceImpl;
    private Logger logger = LoggerFactory.getLogger(LoginSuccessCallback.class);
    @Autowired
    private ProductOrderService productOrderService;
    @Autowired
    public WebSecurityConfiguration(UserDetailsService userDetailsServiceImpl){
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        logger.info("CONFIGURE SUCCESS --------------------------");

        http
                .authorizeRequests()
                .antMatchers(AUTHORIZED_REQUESTS_ADMIN).hasRole("ADMIN")
                .antMatchers(AUTHORIZED_REQUESTS_ANYBODY).permitAll()
                .antMatchers(LOGIN_REQUEST + "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(new LoginSuccessCallback(productOrderService))
                .loginPage(LOGIN_REQUEST)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessCallback())
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
    }

    public class LoginSuccessCallback extends SavedRequestAwareAuthenticationSuccessHandler{
        private Logger logger = LoggerFactory.getLogger(LoginSuccessCallback.class);
        private ProductOrderService productOrderService;

        public LoginSuccessCallback(ProductOrderService productOrderService){
            super();
            this.productOrderService = productOrderService;
        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
            logger.info("AUTH SUCCESS --------------------------");
            Session session = (Session) request.getSession().getAttribute(ConstantConfiguration.SESSION);
            productOrderService.sync(session.getBasket(), (UserEntity) authentication.getPrincipal(),request.getLocale());
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

    public class LogoutSuccessCallback implements LogoutSuccessHandler {
        private Logger logger = LoggerFactory.getLogger(LoginSuccessCallback.class);

        public LogoutSuccessCallback(){
            super();
        }

        @Override
        public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            logger.info("LOGOUT SUCCESS --------------------------");
            httpServletResponse.sendRedirect("/");
        }
    }
}
