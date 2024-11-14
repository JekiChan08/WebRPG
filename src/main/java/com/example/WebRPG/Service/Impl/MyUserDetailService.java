package com.example.WebRPG.Service.Impl;

import com.example.WebRPG.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository repository;

    public MyUserDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.example.WebRPG.Model.User> myUserOptional = repository.findByLogin(username);
        if (myUserOptional.isPresent()) {
            com.example.WebRPG.Model.User myUser = myUserOptional.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(myUser.getLogin())
                    .password(myUser.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
