package com.example.service.impl;

import com.example.dto.UserDTO;
import com.example.dto.LoginDTO;
import com.example.model.User;
import com.example.repository.UserRepo;
import com.example.response.LogInResponse;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserIMPL implements UserService {


    @Autowired
    private UserRepo userRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public String addUser(UserDTO userDTO) {
        // E-posta adresini veritabanında kontrol et
        if (userRepo.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Bu e-posta adresi zaten kullanımda.");
        }

        User user = new User(
                userDTO.getUserid(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );
        userRepo.save(user);
        return user.getUsername();
    }


    @Override
    public LogInResponse loginUser(LoginDTO loginDTO) {

        User user1 = userRepo.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    return new LogInResponse("Login Success", true);
                } else {
                    return new LogInResponse("Login Failed", false);
                }
            } else {
                return new LogInResponse("password does not Match", false);
            }
        }else {
            return new LogInResponse("Email not exist", false);
        }

    }


}
