package com.datapp.chambre.service.authentication;

import com.datapp.chambre.authorization.dto.AccountLoginDTO;
import com.datapp.chambre.authorization.model.LoginType;
import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.service.GenericResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by kmluns
 **/
@Service
public class AuthenticationService {

    @Autowired
    AppAuthenticationService appAuthenticationService;

    @Autowired
    LDAPAuthenticationService ldapAuthenticationService;

    @Autowired
    GenericResponseService genericResponseService;

    IAuthenticationService authenticationService = null;

    public GenericResponse login(AccountLoginDTO loginDto, LoginType type) {
        switch (type) {
            case APP: {
                authenticationService = appAuthenticationService;
                break;
            }
            case LDAP: {
                authenticationService = ldapAuthenticationService;
                break;
            }
            case GOOGLE: {

            }
            case FACEBOOK: {

            }
            default: {
                authenticationService = appAuthenticationService;
            }
        }
        return (GenericResponse) authenticationService.login(loginDto);
    }
}
