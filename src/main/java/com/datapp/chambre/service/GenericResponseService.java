package com.datapp.chambre.service;

import com.datapp.chambre.service.error.ErrorMessageService;
import com.datapp.chambre.constant.ErrorMessageConstant;
import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.dto.response.ResponseType;
import com.datapp.chambre.model.localization.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by kmluns
 **/
@Service
public class GenericResponseService {

    @Autowired
    ErrorMessageService errorMessageService;

    public <T> GenericResponse<T> createResponse(ResponseType responseType, String message, T data, int errorCode) {
        return new GenericResponse<T>()
                .setData(data)
                .setResponseType(responseType)
                .setMessage(message)
                .setErrorCode(errorCode);
    }

    public <T> GenericResponse createResponseNoError(String message, T data) {
        return createResponse(ResponseType.SUCCESS, message, data, ErrorMessageConstant.NO_ERROR);
    }

    public <T> GenericResponse<T> createResponseNoError(T data) {
        return createResponse(ResponseType.SUCCESS, null, data, ErrorMessageConstant.NO_ERROR);
    }

    public <T> GenericResponse createResponseWithError(String message) {
        return createResponse(ResponseType.ERROR, message, null, ErrorMessageConstant.UNKNOWN_ERROR);
    }

    public <T> GenericResponse createResponseWithError(String message, int errorCode) {
        return createResponse(ResponseType.ERROR, message, null, errorCode);
    }

    public <T> GenericResponse createResponseWithError(String message, T data) {
        return createResponse(ResponseType.ERROR, message, data, ErrorMessageConstant.UNKNOWN_ERROR);
    }

    public <T> GenericResponse createResponseWithError(int errorCode, T data) {
        ErrorMessage errorMessage = errorMessageService.getErrorMessage(errorCode);
        return createResponse(ResponseType.ERROR, errorMessage.getMessage(), data, errorCode);
    }

    public <T> GenericResponse createResponseWithError(int errorCode) {
        ErrorMessage errorMessage = errorMessageService.getErrorMessage(errorCode);
        return createResponse(ResponseType.ERROR, errorMessage.getMessage(), null, errorCode);
    }

    public <T> GenericResponse createResponseWithRedirect(String newURl, T data) {
        return createResponse(ResponseType.REDIRECT, newURl, data, ErrorMessageConstant.NO_ERROR);
    }

    public <T> GenericResponse createResponseWithRedirect(String relativeNewURL) {
        return createResponse(ResponseType.REDIRECT, relativeNewURL, null, ErrorMessageConstant.NO_ERROR);
    }

}

