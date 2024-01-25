package com.example.shopdemo.service;

import com.example.shopdemo.auth.AuthRequest;
import com.example.shopdemo.auth.AuthResponse;
import com.example.shopdemo.model.Customer;
import com.example.shopdemo.model.Staff;
import com.example.shopdemo.repository.CustomerRepository;
import com.example.shopdemo.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final StaffRepository staffRepository;
    private final CustomerRepository customerRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse authenticate(AuthRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPasswords()));
        Staff nv = staffRepository.getByUserName(authenticationRequest.getUserName());
        if (nv == null) {
            Customer kh = customerRepository.getByUserName(authenticationRequest.getUserName());
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
            var jwtToken = jwtService.generateToken(kh, authorities);
            var jwtRefreshToken = jwtService.generateRefreshToken(kh);
            return AuthResponse.builder().token(jwtToken).refresh_token(jwtRefreshToken)
                    .build();
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        var jwtToken = jwtService.generateToken(nv, authorities);
        var jwtRefreshToken = jwtService.generateRefreshToken(nv);
        return AuthResponse.builder().token(jwtToken).refresh_token(jwtRefreshToken)
                .build();
    }

}
