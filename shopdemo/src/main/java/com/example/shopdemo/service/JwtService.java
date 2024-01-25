package com.example.shopdemo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.shopdemo.model.Customer;
import com.example.shopdemo.model.Staff;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private static final String secret_key = "123";

    public String generateToken(Object obj, Collection<SimpleGrantedAuthority> authorities) {
        if (obj instanceof Staff) {
            Staff nv = (Staff) obj;
            Algorithm algorithm = Algorithm.HMAC256(secret_key.getBytes());
            return JWT.create().withSubject(nv.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
                    .withClaim("roles",
                            authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                    )
                    .sign(algorithm);
        } else if (obj instanceof Customer) {
            Customer kh = (Customer) obj;
            Algorithm algorithm = Algorithm.HMAC256(secret_key.getBytes());
            return JWT.create().withSubject(kh.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
                    .withClaim("roles",
                            authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                    )
                    .sign(algorithm);
        }
        return null;
    }

    public String generateRefreshToken(Object obj) {
        if (obj instanceof Staff) {
            Staff nv = (Staff) obj;
            Algorithm algorithm = Algorithm.HMAC256(secret_key.getBytes());
            return JWT.create().withSubject(nv.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + 120 * 60 * 1000))
                    .sign(algorithm);
        } else if (obj instanceof Customer) {
            Customer kh = (Customer) obj;
            Algorithm algorithm = Algorithm.HMAC256(secret_key.getBytes());
            return JWT.create().withSubject(kh.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + 120 * 60 * 1000))
                    .sign(algorithm);
        }
        return null;
    }
}
