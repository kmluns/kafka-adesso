package com.datapp.chambre.authorization;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

/**
 * created by kmluns
 **/
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasRole(T(com.pavza.assetmang.authorization.model.Role).ADMIN)")
public @interface AdminAuthorization {
}
