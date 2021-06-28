package com.datapp.chambre.service;

import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.authorization.model.AccountDetail;
import com.datapp.chambre.authorization.model.Role;
import com.datapp.chambre.authorization.dto.AccountDTO;
import com.datapp.chambre.repository.authorization.AccountRepository;
import com.datapp.chambre.service.common.user.AccountService;
import com.datapp.chambre.ChambreApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * created by kmluns
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChambreApplication.class)
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    private AccountDetail accountDetail;
    private AccountDetail accountDetail2;
    private AccountDTO accountDTO;
    private AccountDTO accountDTO2;


    @Before
    public void init() {
        accountRepository.deleteAll();

        accountDetail = new AccountDetail()
                .setName("admin")
                .setLastname("adminLastName")
                .setPhone("9999999999");

        accountDTO = new AccountDTO()
                .setUsername("admin")
                .setPassword("12345")
                .setAccountDetail(accountDetail);

        accountDetail2 = new AccountDetail()
                .setName("test")
                .setLastname("testLastName")
                .setPhone("9999999999");

        accountDTO2 = new AccountDTO()
                .setUsername("test")
                .setPassword("12345")
                .setAccountDetail(accountDetail2);
    }

    @Test
    public void createAccount() {
        accountService.addAccount(accountDTO);

        Optional<Account> optionalActualAccount = accountRepository.findByUsername(accountDTO.getUsername());

        Assert.assertTrue(optionalActualAccount.isPresent());
        Assert.assertEquals("The name in Account Detail should be same!", accountDetail.getName(), optionalActualAccount.get().getAccountDetail().getName());
    }


    @Test
    public void countByRoles() {
        accountService.addAccount(accountDTO);
        accountService.addAccount(accountDTO2);

        int countUser = accountService.countByRole(Role.USER );
        int countAdmin = accountService.countByRole(Role.ADMIN );

        Assert.assertEquals("The count should be 2!", 2, countUser);
        Assert.assertEquals("The count should be 0!", 0, countAdmin);
    }
}
