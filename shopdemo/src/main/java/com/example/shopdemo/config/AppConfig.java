package com.example.shopdemo.config;

import com.example.shopdemo.model.Customer;
import com.example.shopdemo.model.Staff;
import com.example.shopdemo.repository.CustomerRepository;
import com.example.shopdemo.repository.StaffRepository;
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

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final StaffRepository staffRepository;
    private final CustomerRepository customerRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            Staff staff = staffRepository.getByUserName(username);
            if (staff != null) {
                return staff;
            }

            Customer customer = customerRepository.getByUserName(username);
            if (customer != null) {
                return customer;
            }

            throw new UsernameNotFoundException("User not found");
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
