package com.example.WebRPG.Service.Impl;

import com.example.WebRPG.Model.User;
import com.example.WebRPG.Repositories.UserRepository;
import com.example.WebRPG.Service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    @Override
    public User saveUser(User myUser) {
        return userRepository.save(myUser);
    }
}
