package com.datapp.chambre.config;

import com.datapp.chambre.authorization.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * created by kmluns
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // @formatter:off
//        httpSecurity.csrf().disable()
//                .addFilterBefore(corsFilter(), SessionManagementFilter.class)
//                .headers().frameOptions().disable();
//                                                                        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        ;


        httpSecurity
                .csrf()
                .disable()
                .formLogin()
                .disable()
//                .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                .cors()

//                                                                        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //TODO : swagger-ui sholud be closed before prod
//                .antMatchers("/v2/api-docs", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**").permitAll()

                .antMatchers("/","/ws-chat", "/ws-chat/**", "/chambre/**", "/bundle.js", "/authentication/*", "/custom-login", "/test/**", "/index", "/get-user", "/register/*", "/activation/*", "/api/send").permitAll()
                .antMatchers("/help-desk/", "/help-desk/**").hasAnyAuthority(Role.ADMIN.toString(), Role.IT.toString(), Role.USER
                .toString())
                .antMatchers("/asset-management/", "/asset-management/**").hasAnyAuthority(Role.ADMIN.toString(), Role.IT.toString(), Role.USER
                .toString())
                .antMatchers("/it", "/it/**").hasAuthority(Role.IT.toString())
                .antMatchers("/admin", "/admin/**").hasAuthority(Role.ADMIN.toString())

                .anyRequest()
                .authenticated()
                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/loginError")
//                .defaultSuccessUrl("/loginCorrect",true)
//                .successHandler((req,res,auth)->{    //Success handler invoked after successful authentication
//                    res.sendRedirect("/"); // Redirect user to index/home page
//                    res.addCookie(new Cookie("JSESSION",auth.getCredentials().toString()));
//                })
//                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutUrl("/authentication/logout")
                .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.ACCEPTED)));

//                .logoutSuccessUrl("/")

//        ;
        // @formatter:on
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // you USUALLY want this
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return source;
    }


}
