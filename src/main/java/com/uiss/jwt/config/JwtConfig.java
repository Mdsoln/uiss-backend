package com.uiss.jwt.config;

import core.entity.Users;
import core.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class JwtConfig {

    private final UserRepository userRepo;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return identifier -> {
            Optional<Users> user = userRepo.findByEmail(identifier);

            if (user.isEmpty()) {
                user = userRepo.findByUsername(identifier);
            }
            Users foundUser = user.orElseThrow(() -> new UsernameNotFoundException("Username or Email not found"));
            return new org.springframework.security.core.userdetails.User(
                    foundUser.getEmail(),
                    foundUser.getPassword(),
                    foundUser.getAuthorities()
            );
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
        daoAuth.setUserDetailsService(userDetailsService());
        daoAuth.setPasswordEncoder(passwordEncoder());
        return daoAuth;
    }
}
