package com.spring.henallux.BlackStar.configuration;

import com.spring.henallux.BlackStar.dataAccess.entity.UserEntity;
import com.spring.henallux.BlackStar.model.Session;
import com.spring.henallux.BlackStar.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_REQUEST = "/user/login";
    private static final String[] AUTHORIZED_REQUESTS_ANYBODY = new String[]{"/home",
            "/css/stylesheets/*",
            "/images/*",
            "/images/coffees/*",
            "/user/register",
            "/products",
            "/products/*/*"
    };
    private static final String[] AUTHORIZED_REQUESTS_ADMIN = new String[]{"/admin"};

    private UserDetailsService userDetailsServiceImpl;

    @Autowired
    public WebSecurityConfiguration(UserDetailsService userDetailsServiceImpl){
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers(AUTHORIZED_REQUESTS_ADMIN).hasRole("ADMIN")
                .antMatchers(AUTHORIZED_REQUESTS_ANYBODY).permitAll()
                .antMatchers(LOGIN_REQUEST + "/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .successHandler(new LoginSuccessCallback())
                .loginPage(LOGIN_REQUEST)
                .defaultSuccessUrl("/user/loginCallBack")
                .permitAll()

                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
    }

    public class LoginSuccessCallback extends SavedRequestAwareAuthenticationSuccessHandler{

        @Autowired
        private ProductOrderService productOrderService;

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
            Session session = (Session) request.getSession().getAttribute(ConstantConfiguration.SESSION);
            productOrderService.sync(session.getBasket(), (UserEntity) authentication.getPrincipal(),request.getLocale());
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
