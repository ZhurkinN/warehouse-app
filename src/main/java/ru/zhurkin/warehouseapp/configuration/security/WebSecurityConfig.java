package ru.zhurkin.warehouseapp.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.zhurkin.warehouseapp.service.security.CustomUserDetailService;

import java.util.Arrays;

import static ru.zhurkin.warehouseapp.support.constant.SecurityConstantsKeeper.*;
import static ru.zhurkin.warehouseapp.support.constant.SecurityRoleConstantsKeeper.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomUserDetailService customUserDetailService;

    @Bean
    public HttpFirewall getHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowedHttpMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        return firewall;
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers(RESOURCES_WHITE_LIST.toArray(String[]::new)).permitAll()
                                .requestMatchers(PRODUCTS_WHITE_LIST.toArray(String[]::new)).permitAll()
                                .requestMatchers(PROVIDERS_WHITE_LIST.toArray(String[]::new)).permitAll()
                                .requestMatchers(USERS_WHITE_LIST.toArray(String[]::new)).permitAll()
                                .requestMatchers(PRODUCTS_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(MODERATOR)
                                .requestMatchers(PROVIDERS_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(MODERATOR)
                                .requestMatchers(USERS_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(MODERATOR)
                                .requestMatchers(ORDERS_PERMITTED_LIST.toArray(String[]::new)).hasAnyRole(MODERATOR, SALES_MANAGER, ASSISTANT)
                                .requestMatchers(ORDERS_MANAGER_LIST.toArray(String[]::new)).hasAnyRole(SALES_MANAGER)
                                .requestMatchers(ORDERS_ASSISTANT_LIST.toArray(String[]::new)).hasAnyRole(ASSISTANT)
                                .requestMatchers(ORDERS_WORKER_LIST.toArray(String[]::new)).hasAnyRole(WORKER)
                                .requestMatchers(ORDERS_ASSISTANT_MANAGER_LIST.toArray(String[]::new)).hasAnyRole(MODERATOR, SALES_MANAGER, ASSISTANT))
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(form -> form
                        .logoutSuccessUrl("/login")
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")));
        return httpSecurity.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

}
