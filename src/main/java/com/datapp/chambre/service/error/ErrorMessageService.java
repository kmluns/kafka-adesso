package com.datapp.chambre.service.error;

import com.datapp.chambre.constant.ErrorMessageConstant;
import com.datapp.chambre.model.localization.ErrorMessage;
import com.datapp.chambre.model.localization.Locale;
import com.datapp.chambre.repository.localization.ErrorMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * created by kmluns
 **/
@Service
public class ErrorMessageService {

    @Autowired
    private ErrorMessageRepository errorMessageRepository;

    public ErrorMessage getErrorMessage(int errorCode) {
        ErrorMessage errorMessage = errorMessageRepository.findByErrorCodeAndLocale(errorCode, Locale.valueOf(LocaleContextHolder.getLocale().getLanguage()));
        if (errorMessage == null) {
            return errorMessageRepository.findByErrorCodeAndLocale(ErrorMessageConstant.UNKNOWN_ERROR, Locale.valueOf(LocaleContextHolder.getLocale().getLanguage()));
        }
        return errorMessage;
    }

    public String getErrorMessageText(int errorCode) {
        ErrorMessage errorMessage = errorMessageRepository.findByErrorCodeAndLocale(errorCode, Locale.valueOf(LocaleContextHolder.getLocale().getLanguage()));
        if (errorMessage == null) {
            return errorMessageRepository.findByErrorCodeAndLocale(ErrorMessageConstant.UNKNOWN_ERROR, Locale.valueOf(LocaleContextHolder.getLocale().getLanguage())).getMessage();
        }
        return errorMessage.getMessage();
    }
}
