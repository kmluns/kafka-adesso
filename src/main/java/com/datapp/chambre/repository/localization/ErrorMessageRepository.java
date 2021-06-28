package com.datapp.chambre.repository.localization;

import com.datapp.chambre.model.localization.ErrorMessage;
import com.datapp.chambre.model.localization.Locale;
import org.springframework.data.repository.CrudRepository;

/**
 * created by kmluns
 **/
public interface ErrorMessageRepository extends CrudRepository<ErrorMessage, String> {

    ErrorMessage findByErrorCodeAndLocale(int errorCode, Locale locale);


}
