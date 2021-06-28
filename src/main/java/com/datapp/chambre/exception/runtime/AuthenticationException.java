package com.datapp.chambre.exception.runtime;

import com.datapp.chambre.constant.ErrorMessageConstant;
import com.datapp.chambre.utils.app_static.StaticUtils;

/**
 * created by kmluns
 **/
public class AuthenticationException extends ApplicationRuntimeException {

    public AuthenticationException() {
        super(StaticUtils.getErrorMessageService().getErrorMessageText(ErrorMessageConstant.AUTHENTICATION_ERROR), ErrorMessageConstant.AUTHENTICATION_ERROR);
    }

}
