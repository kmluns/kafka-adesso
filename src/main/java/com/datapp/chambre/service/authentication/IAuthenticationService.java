package com.datapp.chambre.service.authentication;

import com.datapp.chambre.authorization.dto.AccountLoginDTO;

/**
 * created by kmluns 30.05.2019
 **/
public interface IAuthenticationService {

    Object login(AccountLoginDTO loginDto);
}
