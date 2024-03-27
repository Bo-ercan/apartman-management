package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User,Integer>
{
    Optional<User> findOneByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    boolean existsByEmail(String email);
}