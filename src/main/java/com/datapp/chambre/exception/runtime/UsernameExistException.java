package com.datapp.chambre.exception.runtime;

import com.datapp.chambre.utils.app_static.StaticUtils;
import com.datapp.chambre.constant.ErrorMessageConstant;

/**
 * created by kmluns
 **/
public class UsernameExistException extends ApplicationRuntimeException {

    public UsernameExistException() {
        super(StaticUtils.getErrorMessageService().getErrorMessageText(ErrorMessageConstant.USERNAME_EXIST), ErrorMessageConstant.USERNAME_EXIST);
    }
}
