package com.datapp.chambre.controller;

import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.service.GenericResponseService;
import com.datapp.chambre.service.upload.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * created by kmluns
 **/
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    Environment environment;

    @Autowired
    ImageUploadService imageUploadService;

    @Autowired
    GenericResponseService genericResponseService;

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "image")
    public GenericResponse uploadImage(@AuthenticationPrincipal Account account, @RequestBody MultipartFile file) {
        if (imageUploadService.storeImageFile(account, file)) {
            return genericResponseService.createResponseNoError("SUCCESS!", null);
        } else {
            return genericResponseService.createResponseWithError("FAILED!", null);
        }
    }
}