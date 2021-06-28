package com.datapp.chambre.exception.runtime;

import com.datapp.chambre.constant.ErrorMessageConstant;
import com.datapp.chambre.utils.app_static.StaticUtils;

/**
 * created by kmluns
 **/
public class NotFoundException extends ApplicationRuntimeException {

    public NotFoundException() {
        super(StaticUtils.getErrorMessageService().getErrorMessageText(ErrorMessageConstant.NOT_FOUND), ErrorMessageConstant.NOT_FOUND);
    }
}