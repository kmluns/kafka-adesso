package com.datapp.chambre.controller;

import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.service.GenericResponseService;
import com.datapp.chambre.service.common.user.AccountService;
import com.datapp.chambre.service.error.ErrorMessageService;
import com.datapp.chambre.constant.ErrorMessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by kmluns
 **/
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    GenericResponseService genericResponseService;

    @Autowired
    ErrorMessageService errorMessageService;

    @Autowired
    AccountService accountService;

    @GetMapping("error-message")
    public GenericResponse errorMessage() {
        return genericResponseService.createResponseWithError(ErrorMessageConstant.PROCESS_ERROR);
    }

    @GetMapping("data")
    public GenericResponse getData() {
        return genericResponseService.createResponseNoError("DATA!");
    }

}
