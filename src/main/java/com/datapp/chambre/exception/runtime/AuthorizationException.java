package com.datapp.chambre.exception.runtime;

import com.datapp.chambre.utils.app_static.StaticUtils;
import com.datapp.chambre.constant.ErrorMessageConstant;

/**
 * created by kmluns
 **/
public class AuthorizationException extends ApplicationRuntimeException {

    public AuthorizationException() {
        super(StaticUtils.getErrorMessageService().getErrorMessageText(ErrorMessageConstant.AUTHORIZATION_ERROR), ErrorMessageConstant.AUTHORIZATION_ERROR);
    }
}
