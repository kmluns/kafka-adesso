package com.datapp.chambre.controller;

import com.datapp.chambre.authorization.dto.AccountLoginDTO;
import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.authorization.model.LoginType;
import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.service.GenericResponseService;
import com.datapp.chambre.exception.runtime.AuthenticationException;
import com.datapp.chambre.exception.runtime.ParseException;
import com.datapp.chambre.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * created by kmluns
 **/
@RestController
@RequestMapping
public class PublicController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    GenericResponseService genericResponseService;

    @PostMapping({"authentication/login"})
    public GenericResponse login(@Validated @RequestBody AccountLoginDTO loginDto, @RequestParam(value = "type", required = false, defaultValue = "APP") LoginType type, Errors errors) {
        if (errors.hasErrors()) {
            throw new ParseException();
        }
        return authenticationService.login(loginDto, type);
    }

    @GetMapping("authentication/account")
    public GenericResponse getUser(@AuthenticationPrincipal Account account) {
        if (account == null) {
            throw new AuthenticationException();
        }
        return genericResponseService.createResponseNoError(account);
    }

}
