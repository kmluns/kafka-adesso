package com.datapp.chambre.utils.app_static;

import com.datapp.chambre.service.error.ErrorMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * created by kmluns 21.01.2019
 **/
@Component
public class StaticContextInitializer {

    @Autowired
    private ErrorMessageService errorMessageService;


    @PostConstruct
    public void init() {
        StaticUtils.setErrorMessageService(errorMessageService);
    }


}
