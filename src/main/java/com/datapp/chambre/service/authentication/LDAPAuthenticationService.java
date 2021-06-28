package com.datapp.chambre.service.authentication;

import com.datapp.chambre.authorization.dto.AccountLoginDTO;
import org.springframework.stereotype.Service;

/**
 * created by kmluns 30.05.2019
 **/
@Service
public class LDAPAuthenticationService implements IAuthenticationService {
    @Override
    public Object login(AccountLoginDTO loginDto) {
        throw new UnsupportedOperationException();
    }
}
