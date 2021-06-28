package com.datapp.chambre.utils.app_static;

import com.datapp.chambre.service.error.ErrorMessageService;

/**
 * created by kmluns 21.01.2019
 **/
public final class StaticUtils {

    private StaticUtils() {
    }


    private static ErrorMessageService errorMessageService;

    public static ErrorMessageService getErrorMessageService() {
        return errorMessageService;
    }

    public static void setErrorMessageService(ErrorMessageService errorMessageService) {
        StaticUtils.errorMessageService = errorMessageService;
    }
}
