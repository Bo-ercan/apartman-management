package com.example.controller;


import com.example.dto.LoginDTO;
import com.example.response.LogInResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.UserService;
import com.example.dto.UserDTO;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public String saveUser(@RequestBody UserDTO userDTO)
    {
        String id = userService.addUser(userDTO);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO)
    {
        LogInResponse loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

}
