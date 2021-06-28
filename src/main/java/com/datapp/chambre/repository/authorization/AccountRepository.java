package com.datapp.chambre.repository.authorization;

import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.authorization.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * created by kmluns
 **/
public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findByUsername(String username);

    int countByRoles(Role role);

    List<Account> findByDemoAndFinishDateLessThanAndAccountNonExpired(Boolean isDemo, Date period, Boolean isAccountNonExpired);

}
