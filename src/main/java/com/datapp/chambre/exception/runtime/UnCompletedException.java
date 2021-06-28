package com.datapp.chambre.exception.runtime;

import com.datapp.chambre.constant.ErrorMessageConstant;
import com.datapp.chambre.utils.app_static.StaticUtils;

/**
 * created by kmluns 31.05.2019
 **/
public class UnCompletedException extends ApplicationRuntimeException {
    public UnCompletedException() {
        super(StaticUtils.getErrorMessageService().getErrorMessageText(ErrorMessageConstant.PARSE_ERROR), ErrorMessageConstant.PARSE_ERROR);
    }
}
