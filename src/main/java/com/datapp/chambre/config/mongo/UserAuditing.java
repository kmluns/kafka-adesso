package com.datapp.chambre.config.mongo;

import com.datapp.chambre.authorization.model.Account;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * created by kmluns 31.05.2019
 **/
public class UserAuditing implements AuditorAware<Account> {

    @Override
    public Optional<Account> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return Optional.ofNullable((Account) authentication.getPrincipal());
    }
}
