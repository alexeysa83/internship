package com.andersenlab.aadamovich.repositories;

import com.andersenlab.aadamovich.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    boolean existsUserByUsername(String username);
}
