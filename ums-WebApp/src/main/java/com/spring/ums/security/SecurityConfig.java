package com.spring.ums.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.spring.ums.service.UserService;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
    	// utilizza l'algoritmo di hash BCrypt per crittografare le password
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    	//La configurazione di Spring Security utilizza questo bean per la gestione dell'autenticazione
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/registration**").permitAll()
                .antMatchers("/search").authenticated() // Allow authenticated users to access /search
                .antMatchers("/notifications").authenticated()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                // Configurazione del logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();

    	/**
        * Disabilitazione di CSRF e configurazione per H2 Console
        * La nostra applicazione e' un'API RESTful in cui le richieste non mantengono uno stato di sessione,
        * la protezione CSRF potrebbe non essere necessaria.
         */
        
          http.csrf().disable();
          http.headers().frameOptions().disable();

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        //UserDetailsService è un'interfaccia di Spring Security 
        // che deve essere implementata per caricare i dettagli dell'utente
        auth.setUserDetailsService(userService);
        //Questo indica al provider come deve codificare le password degli utenti durante il processo di autenticazione 
        //e come confrontarle con quelle memorizzate
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
