package com.datapp.chambre.controller.setting.management.user;

import com.datapp.chambre.authorization.dto.AccountDTO;
import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.dto.request.FilterDTO;
import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.service.common.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kmluns on 5.03.2020
 */
@RestController
@RequestMapping("setting/user")
public class SettingsUserController {

    @Autowired
    AccountService accountService;

    @PostMapping("table")
    public GenericResponse<Page<Account>> userTablePage(@RequestBody FilterDTO filterDTO) {
        return accountService.tablePage(filterDTO);
    }

    @PostMapping("add")
    public GenericResponse addUser(@RequestBody AccountDTO accountDTO) {
        return accountService.addAccount( accountDTO);
    }

    @PostMapping("edit")
    public GenericResponse editUser(@RequestBody AccountDTO helpDeskUserDTO) {
        return accountService.editAccount(helpDeskUserDTO);
    }

    @PostMapping("delete-list")
    public GenericResponse deleteUser(@AuthenticationPrincipal Account currentAccount, @RequestBody List<String> accountIdList) {
        return accountService.deleteList(currentAccount, accountIdList);
    }


}
