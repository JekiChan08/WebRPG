package com.example.WebRPG.controller.Dao;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDao {
    private String id;
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
}
