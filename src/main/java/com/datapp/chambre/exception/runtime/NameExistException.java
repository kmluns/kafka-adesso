package com.datapp.chambre.exception.runtime;

import com.datapp.chambre.constant.ErrorMessageConstant;
import com.datapp.chambre.utils.app_static.StaticUtils;

/**
 * Created by kmluns on 4.06.2019
 */
public class NameExistException extends ApplicationRuntimeException {
    public NameExistException() {
        super(StaticUtils.getErrorMessageService().getErrorMessageText(ErrorMessageConstant.USERNAME_EXIST), ErrorMessageConstant.USERNAME_EXIST);
    }
}
