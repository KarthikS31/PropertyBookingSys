package org.karthik.propbookingsys.controllers;


import org.karthik.propbookingsys.models.Users;
import org.karthik.propbookingsys.security.JwtSecurity;
import org.karthik.propbookingsys.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtSecurity jwtSecurity;

    @PostMapping("/add")
    public ResponseEntity<Users> addUser(@RequestBody Users users){
        return new ResponseEntity<>(authService.createUser(users), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Users users){
        try{
            Authentication authentication=authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(users.getMail(),users.getPassword()));
            UserDetails userDetails= (UserDetails) authentication.getPrincipal();

            String token= jwtSecurity.generateToken(userDetails);

            return ResponseEntity.ok(Map.of("token",token));
        }catch (Exception e){
            return ResponseEntity.
                    status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error","Invalid username or password"));
        }

    }

}
