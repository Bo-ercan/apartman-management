package com.example.service;

import com.example.dto.UserDTO;
import com.example.dto.LoginDTO;
import com.example.response.LogInResponse;

public interface UserService {
    String addUser(UserDTO userDTO);

    LogInResponse loginUser(LoginDTO loginDTO);

}