package com.datapp.chambre.controller;

import com.datapp.chambre.authorization.AdminAuthorization;
import com.datapp.chambre.service.GenericResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by kmluns 15.05.2019
 **/
@RestController
@RequestMapping("admin")
@AdminAuthorization
public class AdminController {

    @Autowired
    GenericResponseService genericResponseService;


}
