package com.datapp.chambre.service.common.user;

import com.datapp.chambre.authorization.dto.AccountDTO;
import com.datapp.chambre.authorization.dto.ChangePasswordDTO;
import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.authorization.model.Role;
import com.datapp.chambre.authorization.model.UserGetIndicatorType;
import com.datapp.chambre.dto.request.FilterDTO;
import com.datapp.chambre.dto.request.devicestatus.organization.OrganizationCreateEditDTO;
import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.exception.runtime.ParseException;
import com.datapp.chambre.exception.runtime.UserNotFoundException;
import com.datapp.chambre.exception.runtime.UsernameExistException;
import com.datapp.chambre.model.devicestatus.organization.ObservableOrganization;
import com.datapp.chambre.model.devicestatus.organization.Organization;
import com.datapp.chambre.model.devicestatus.organization.role.OrganizationRole;
import com.datapp.chambre.repository.authorization.AccountRepository;
import com.datapp.chambre.service.GenericResponseService;
import com.datapp.chambre.service.devicestatus.organization.OrganizationService;
import com.datapp.chambre.service.setting.SettingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * created by kmluns
 **/
@Service
public class AccountService implements UserDetailsService {

    @Autowired
    GenericResponseService genericResponseService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SettingService settingService;

    OrganizationService organizationService;

    @Autowired
    public void setAccountService(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @PostConstruct
    public void saveFirstAdminAccount() {
        final String adminUsername = "admin";
        final String adminFirstPassword = "12345";
        long adminCount = accountRepository.countByRoles(Role.ADMIN );
        if (adminCount == 0) {
            Optional<Account> optinalOldAdmin = accountRepository.findByUsername(adminUsername);
            optinalOldAdmin.ifPresent( account -> accountRepository.delete( account ) );
            Account account = new Account()
                    .setUsername(adminUsername)
                    .setPassword(bCryptPasswordEncoder.encode(adminFirstPassword))
                    .setRoles( Collections.singletonList( Role.ADMIN ) );
            account.setId(UUID.randomUUID().toString());

            accountRepository.save(account);
        }
    }

    @PostConstruct
    @Profile("dev")
    public void create() {
        long adminCount = accountRepository.countByRoles(Role.IT );
        if (adminCount == 0) {
            Optional<Account> optionalOldTest = accountRepository.findByUsername("test");
            optionalOldTest.ifPresent( account -> accountRepository.delete( account ) );
            Account account = new Account()
                    .setUsername("test")
                    .setPassword(bCryptPasswordEncoder.encode("test12345"))
                    .setRoles( Collections.singletonList( Role.IT ) );
            account.setId(UUID.randomUUID().toString());

            accountRepository.save(account);
        }
    }

    public GenericResponse addAccount(AccountDTO accountDTO) {
        if (usernameExists(accountDTO.getUsername())) {
            throw new UsernameExistException();
        }

        String defaultPassword = settingService.getDefaultPassword();
        Account user = modelMapper.map(accountDTO, Account.class);
        user.setPassword(defaultPassword)
                .setRoles( Collections.singletonList( Role.USER ) );
        user.setId(UUID.randomUUID().toString());

        Account newAccount = createAccount(user);

        organizationService.addOrganization( newAccount, new OrganizationCreateEditDTO("Standart Organization") );

        return genericResponseService.createResponseNoError(newAccount);
    }

    public GenericResponse editAccount(AccountDTO accountDTO) {
        Account account = getAccount(accountDTO.getId(), UserGetIndicatorType.ID);
        account.setAccountDetail(accountDTO.getAccountDetail());
        account = createAccount(account);
        return genericResponseService.createResponseNoError(account);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account loadUserByUsername(String username) {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (!optionalAccount.isPresent()) {
            throw new UsernameNotFoundException("Kullanıcı Adı/Şifre Yanlış");
        } else {
            return optionalAccount.get();
        }
    }

    public void autologin(String username) {
        Account account = loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(account, account.getPassword(), account.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    public boolean delete(Account currentAccount, String indicator, String indicatorType) {
        Optional<Account> optionalAccount = Optional.empty();

        if (indicatorType.equalsIgnoreCase("USERNAME")) {
            optionalAccount = accountRepository.findByUsername(indicator);
        } else if (indicatorType.equalsIgnoreCase("ID")) {
            optionalAccount = accountRepository.findById(indicator);
        }

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();

            if (currentAccount.getId().equals(account.getId())) {
                throw new ParseException();
            } else if (account.getRoles().contains(Role.ADMIN )) {
                throw new ParseException();
            } else if (account.getUsername().equalsIgnoreCase("admin")) {
                throw new ParseException();
            } else {
                accountRepository.delete(account);
                return true;
            }
        } else {
            return false;
        }
    }

    public GenericResponse changePassword(Account account, ChangePasswordDTO changePasswordDTO) {
        Account accountDB = getAccount( account.getId(), UserGetIndicatorType.ID );
        if ( bCryptPasswordEncoder.matches(changePasswordDTO.getOldPassword(), accountDB.getPassword())) {
            accountDB.setPassword(bCryptPasswordEncoder.encode(changePasswordDTO.getNewPassword()));
            accountRepository.save(accountDB);
            return genericResponseService.createResponseNoError(null);
        } else {
            throw new ParseException();
        }
    }

    public boolean usernameExists(String username) {
        return accountRepository
                .findByUsername(username).isPresent();
    }

    public boolean isDemo(String id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map( Account::isDemo ).orElse( false );
    }

    public int countByRole(Role role) {
        return accountRepository.countByRoles(role);
    }


    public GenericResponse tablePage(FilterDTO filterDTO) {
        Sort sort = Sort.by(filterDTO.getSortingColumn());
        if (filterDTO.isAsc()) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }
        Page<Account> accountPage = accountRepository.findAll(
                PageRequest.of(
                        filterDTO.getCurrentPage(),
                        filterDTO.getPageSize(),
                        sort
                )
        );
        return genericResponseService.createResponseNoError(accountPage);
    }

    public GenericResponse deleteList(Account currentAccount, List<String> accountIdList) {
        accountIdList.forEach((accountId -> delete(currentAccount, accountId, "ID")));
        return genericResponseService.createResponseNoError(true);
    }

    protected Account getAccount(String indicator, UserGetIndicatorType indicatorType){
        Optional<Account> optionalAccount = Optional.empty();

        if (UserGetIndicatorType.USERNAME.equals(indicatorType)) {
            optionalAccount = accountRepository.findByUsername(indicator);
        } else if (UserGetIndicatorType.ID.equals(indicatorType)) {
            optionalAccount = accountRepository.findById(indicator);
        }

        return optionalAccount.orElseThrow( UserNotFoundException::new );
    }

    public GenericResponse getUserList() {
        List<Account> accountList = accountRepository.findAll();
        return genericResponseService.createResponseNoError(accountList);
    }

    public void addOrganization ( Account account, Organization organization, OrganizationRole role )
    {
        if(OrganizationRole.OBSERVER.equals( role )){
            account.getObservableOrganizations().add( new ObservableOrganization(organization) );
        } else {
            account.getManageableOrganizations().add( organization );
        }
        accountRepository.save( account );
    }
}
