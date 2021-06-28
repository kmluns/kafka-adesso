//package com.datapp.chambre.exception;
//
//import com.datapp.chambre.dto.response.GenericResponse;
//import com.datapp.chambre.service.GenericResponseService;
//import com.datapp.chambre.constant.ErrorMessageConstant;
//import com.datapp.chambre.exception.runtime.ApplicationRuntimeException;
//import com.datapp.chambre.exception.runtime.AuthenticationException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
///**
// * created by kmluns
// **/
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @Autowired
//    GenericResponseService genericResponseService;
//
//    @ExceptionHandler({AuthenticationException.class, AccessDeniedException.class})
//    public final ResponseEntity<GenericResponse<Object>> handleAuthenticationException(Exception e) {
//        return new ResponseEntity<GenericResponse<Object>>(genericResponseService.createResponseWithError(e.getMessage()),
//                HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler({ApplicationRuntimeException.class})
//    public final ResponseEntity<GenericResponse<Object>> handleApplicationRuntimeException(ApplicationRuntimeException e) {
//        return new ResponseEntity<GenericResponse<Object>>(genericResponseService.createResponseWithError(e.getMessage(), e.getError_code()),
//                HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler({Exception.class})
//    public final ResponseEntity<GenericResponse<Object>> handleOtherException(Exception e) {
//        return new ResponseEntity<GenericResponse<Object>>(genericResponseService.createResponseWithError(ErrorMessageConstant.UNKNOWN_ERROR),
//                HttpStatus.BAD_REQUEST);
//
//    }
//
//}
