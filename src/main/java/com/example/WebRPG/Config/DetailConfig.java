package com.example.WebRPG.Config;

import com.example.WebRPG.Repositories.UserRepository;
import com.example.WebRPG.Service.Impl.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DetailConfig {
    private final UserRepository repository;

    public DetailConfig(UserRepository repository) {
        this.repository = repository;
    }

    @Bean
    public MyUserDetailService myUserDetailService() {
        return new MyUserDetailService(repository);
    }
}
