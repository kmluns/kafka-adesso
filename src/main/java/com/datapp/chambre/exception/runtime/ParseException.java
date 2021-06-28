package com.datapp.chambre.exception.runtime;

import com.datapp.chambre.constant.ErrorMessageConstant;
import com.datapp.chambre.utils.app_static.StaticUtils;

/**
 * created by kmluns 21.01.2019
 **/
public class ParseException extends ApplicationRuntimeException {
    public ParseException() {
        super(StaticUtils.getErrorMessageService().getErrorMessageText(ErrorMessageConstant.PARSE_ERROR), ErrorMessageConstant.PARSE_ERROR);
    }
}
