package com.datapp.chambre.config;

import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.config.mongo.UserAuditing;
import com.datapp.chambre.utils.EmailUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * created by kmluns
 **/
@Configuration
public class BeanConfig {

    @Bean
    public AuditorAware<Account> auditorProvider() {
        return new UserAuditing();
    }


    @Bean
    public EmailUtils userDomain() {
        return new EmailUtils();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
