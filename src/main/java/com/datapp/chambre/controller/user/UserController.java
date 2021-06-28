package com.datapp.chambre.controller.user;

import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.service.GenericResponseService;
import com.datapp.chambre.service.common.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kmluns on 24.05.2020
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    GenericResponseService genericResponseService;

    @Autowired
    AccountService accountService;

    @GetMapping("list")
    public GenericResponse getUserList() {
        return accountService.getUserList();
    }

}
