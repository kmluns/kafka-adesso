package com.datapp.chambre.controller.account;

import com.datapp.chambre.authorization.dto.ChangePasswordDTO;
import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.service.GenericResponseService;
import com.datapp.chambre.service.common.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kmluns on 12.05.2020
 */
@RestController
@RequestMapping("me")
public class AccountController {

    @Autowired
    GenericResponseService genericResponseService;

    @Autowired
    AccountService accountService;

    @PostMapping("change-password")
    public GenericResponse changePassword(@RequestBody ChangePasswordDTO changePasswordDTO, @AuthenticationPrincipal Account authenticatedAccount) {
        return accountService.changePassword(authenticatedAccount, changePasswordDTO);
    }

}
