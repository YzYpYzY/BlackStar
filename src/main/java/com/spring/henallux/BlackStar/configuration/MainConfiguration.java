package com.spring.henallux.BlackStar.configuration;

import com.spring.henallux.BlackStar.model.Session;
import com.spring.henallux.BlackStar.service.ProductOrderService;
import com.spring.henallux.BlackStar.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
public class MainConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private ProductOrderService productOrderService;

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DefaultMessageCodesResolver defaultMessageCodesResolver() {
        DefaultMessageCodesResolver defaultMessageCodesResolver = new DefaultMessageCodesResolver();
        return defaultMessageCodesResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames("translations/general", "translations/errors");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("fr"));
        resolver.setCookieName("LocaleCookie");
        resolver.setCookieMaxAge(-1);
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new localChange();
        interceptor.setParamName("locale");
        registry.addInterceptor(interceptor);
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    public class localChange extends LocaleChangeInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
            String newLocale = request.getParameter(this.getParamName());
            if (newLocale != null && this.checkHttpMethod(request.getMethod())) {
                LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
                if (localeResolver == null) {
                    throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
                }

                try {
                    Locale locale = this.parseLocaleValue(newLocale);
                    localeResolver.setLocale(request, response, locale);
                    Session session = (Session) request.getSession().getAttribute(ConstantConfiguration.SESSION);
                    productOrderService.refreshTrad(session.getBasket(), locale);
                } catch (IllegalArgumentException var7) {
                    if (!this.isIgnoreInvalidLocale()) {
                        throw var7;
                    }

                    this.logger.debug("Ignoring invalid locale value [" + newLocale + "]: " + var7.getMessage());
                }
            }
            return true;
        }

        private boolean checkHttpMethod(String currentMethod) {
            String[] configuredMethods = this.getHttpMethods();
            if (ObjectUtils.isEmpty(configuredMethods)) {
                return true;
            } else {
                String[] var3 = configuredMethods;
                int var4 = configuredMethods.length;

                for (int var5 = 0; var5 < var4; ++var5) {
                    String configuredMethod = var3[var5];
                    if (configuredMethod.equalsIgnoreCase(currentMethod)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }
}
