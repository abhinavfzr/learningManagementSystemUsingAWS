package com.simplecoding.jwt.controllers;

import com.simplecoding.jwt.dto.*;
import com.simplecoding.jwt.model.JwtUser;
import com.simplecoding.jwt.repo.JwtUserRepository;
import com.simplecoding.jwt.service.CustomUserDetailsService;
import com.simplecoding.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1.0/lms")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtUserRepository userRepository;

    @GetMapping("/hello")
    public String hello()
    {
        return  "Nil here";
    }


    @GetMapping("/checkUser")
    public String checkUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return  currentPrincipalName;
    }

    @GetMapping("/checkRole")
    public ResponseEntity<?> authenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return ResponseEntity.ok(userDetailsService.loadRoleByUsername(currentPrincipalName));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsernameOrEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt,userDetailsService.loadRoleByUsername(loginRequest.getUsernameOrEmail())));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {


        if(userRepository.findUserByEmail(signUpRequest.getEmail()) != null) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        JwtUser jwtUser = new JwtUser();
        jwtUser.setEmail(signUpRequest.getEmail());
        jwtUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        jwtUser.setUsername(signUpRequest.getUsername());
        jwtUser.setRole(signUpRequest.getRole());
        userRepository.save(jwtUser);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }


}
