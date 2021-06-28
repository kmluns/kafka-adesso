package com.datapp.chambre.model.authorization;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

/**
 * created by kmluns 27.05.2019
 **/
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasRole(T(com.pavza.assetmang.authorization.model.Role).IT)")
public @interface ITAuthorization {
}
