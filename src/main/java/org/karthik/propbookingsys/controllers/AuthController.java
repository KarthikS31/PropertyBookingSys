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
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtSecurity jwtSecurity;

    @PostMapping("/add")
    public ResponseEntity<Users> addUser(@RequestBody Users users) {
        return new ResponseEntity<>(authService.createUser(users), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody Users users) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            users.getMail(),
                            users.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = jwtSecurity.generateToken(userDetails);

            Users loggedInUser = authService.getUserByMail(userDetails.getUsername());

            Map<String, Object> response = Map.of(
                    "token", token,
                    "user", Map.of(
                            "id", loggedInUser.getId(),
                            "name", loggedInUser.getName(),
                            "mail", loggedInUser.getMail()
                    )
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "error", "Invalid username or password"
                    ));
        }
    }

}
