package com.example.WebRPG.Service.Impl;

import com.example.WebRPG.Model.User;
import com.example.WebRPG.Repositories.UserRepository;
import com.example.WebRPG.Service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
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
    public User saveUser(User myUser) {
        myUser.setPassword(bCryptPasswordEncoder.encode(myUser.getPassword()));
        return userRepository.save(myUser);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }


    @Override
    public User registerUser(String username, String password) {
        if (userRepository.findByLogin(username).isPresent()) {
            throw new RuntimeException("Пользователь уже существует");
        }

        User user = new User();
        user.setLogin(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }
}
