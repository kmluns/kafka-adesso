package com.datapp.chambre.exception.runtime;

import com.datapp.chambre.constant.ErrorMessageConstant;

/**
 * created by kmluns
 **/
public class EmailFormatException extends ApplicationRuntimeException {
    public EmailFormatException(String email) {
        super(email + " is not valid", ErrorMessageConstant.PARSE_ERROR);
    }
}
