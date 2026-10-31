package org.karthik.propbookingsys.services;


import org.jspecify.annotations.NonNull;
import org.karthik.propbookingsys.models.Users;
import org.karthik.propbookingsys.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users createUser(@RequestBody Users users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return authRepository.save(users);
    }


    public Users getUserByMail(String mail) {
        return authRepository.findByMail(mail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found."));
    }
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Users users = authRepository.findByMail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found."));

        return new User(users.getMail(),users.getPassword(), List.of());
    }
}
