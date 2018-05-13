package com.spring.henallux.BlackStar.dataAccess.dao;

import com.spring.henallux.BlackStar.dataAccess.entity.LanguageEntity;
import com.spring.henallux.BlackStar.dataAccess.entity.UserEntity;
import com.spring.henallux.BlackStar.dataAccess.repository.LanguageRepository;
import com.spring.henallux.BlackStar.dataAccess.repository.UserRepository;
import com.spring.henallux.BlackStar.dataAccess.util.Converter;
import com.spring.henallux.BlackStar.model.RegisterModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import javax.transaction.Transactional;
import java.util.Locale;

@Service
@Transactional
public class UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private Converter converter;

    public UserEntity findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public UserEntity save(RegisterModel model) {
        UserEntity user = converter.registerModelToEntity(model);
        LanguageEntity language = languageRepository.findByIsocode(LocaleContextHolder.getLocale().toString());
        user.setLanguage(language);
        userRepository.save(user);
        return user;
    }
}
