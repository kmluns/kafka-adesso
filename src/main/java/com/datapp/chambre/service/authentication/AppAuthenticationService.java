package com.datapp.chambre.service.authentication;

import com.datapp.chambre.authorization.dto.AccountLoginDTO;
import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.constant.ErrorMessageConstant;
import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.service.GenericResponseService;
import com.datapp.chambre.service.common.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * created by kmluns 30.05.2019
 **/
@Service
public class AppAuthenticationService implements IAuthenticationService {

    @Autowired
    AccountService accountService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    GenericResponseService genericResponseService;


    public GenericResponse login(AccountLoginDTO loginDto) {

        Account user = accountService.loadUserByUsername(loginDto.getUsername());
        if (user != null && bCryptPasswordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            return genericResponseService.createResponseNoError("", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }

        return genericResponseService.createResponseWithError(ErrorMessageConstant.AUTHENTICATION_ERROR);
    }
}
