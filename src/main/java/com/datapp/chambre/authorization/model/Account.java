package com.datapp.chambre.authorization.model;

import com.datapp.chambre.model.devicestatus.organization.ObservableOrganization;
import com.datapp.chambre.model.devicestatus.organization.Organization;
import com.datapp.chambre.model.utils.Base;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * created by kmluns
 **/
@Document(collection = "user")
@Accessors(chain = true)
@JsonIgnoreProperties({"password", "lastLogin", "accountNonExpired",
        "accountNonLocked", "credentialsNonExpired", "enabled"})
public class Account extends Base implements UserDetails {

    @Getter
    @Setter
    public boolean demo = true;

    @Getter
    @Setter
    public boolean enabled = true;

    @NotNull
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private AccountDetail accountDetail;

    @Getter
    @Setter
    private Date finishDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new LinkedList<>();

        for (Role role : this.getRoles()) {
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(role.toString()));
        }

        return simpleGrantedAuthorityList;
    }

    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    @Getter
    @Setter
    @NotNull
    private List<Role> roles = new LinkedList<>();
    @Getter
    @Setter
    private Date lastLogin;
    @Getter
    @Setter
    private boolean accountNonExpired = true;
    @Getter
    @Setter
    private boolean accountNonLocked = true;
    @Getter
    @Setter
    private boolean credentialsNonExpired = true;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            return username.equals(((Account) obj).getUsername());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Getter
    @Setter
    @DBRef
    @JsonBackReference
    private Set<Organization> manageableOrganizations = new HashSet<>();

    @Getter
    @Setter
    @DBRef
    @JsonBackReference
    private Set<ObservableOrganization> observableOrganizations = new HashSet<>();

}
