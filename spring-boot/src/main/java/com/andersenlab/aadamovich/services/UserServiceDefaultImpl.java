package com.andersenlab.aadamovich.services;

import com.andersenlab.aadamovich.entities.user.UserEntity;
import com.andersenlab.aadamovich.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceDefaultImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceDefaultImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public boolean saveNewUser(UserEntity userEntity) {
        boolean wasSaved = false;
        final boolean usernameIsTaken = userRepository.existsUserByUsername(userEntity.getUsername());
        if (!usernameIsTaken) {
            final String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
            userEntity.setPassword(encodedPassword);
            userEntity.setEnabled(true);
            userEntity.setRegistrationDate(LocalDate.now());
            userRepository.save(userEntity);
            wasSaved=true;
        }
        return wasSaved;
    }

    @Override
    @Transactional
    public boolean deleteUser(String username) {
        final UserEntity userToDelete = userRepository.findByUsername(username);
        if (userToDelete != null) {
            userToDelete.setEnabled(false);
            userRepository.save(userToDelete);
        }
        return userToDelete != null;
    }
}
