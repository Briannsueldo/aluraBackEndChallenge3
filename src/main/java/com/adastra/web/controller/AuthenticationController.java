package com.adastra.web.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adastra.web.domain.users.RecordUserAuthentication;
import com.adastra.web.domain.users.User;
import com.adastra.web.infra.security.RecordJWTToken;
import com.adastra.web.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity userAuthentication(@RequestBody @Valid RecordUserAuthentication recordUserAuthentication) {
    
        org.springframework.security.core.Authentication authToken = new UsernamePasswordAuthenticationToken(recordUserAuthentication.username(), recordUserAuthentication.password());
        var userAuthenticated = authenticationManager.authenticate(authToken);

        var JWTtoken = tokenService.generateToken((User) userAuthenticated.getPrincipal());
        return ResponseEntity.ok(new RecordJWTToken(JWTtoken));
    }
}
