package com.andersenlab.aadamovich.services;

import com.andersenlab.aadamovich.entities.Role;
import com.andersenlab.aadamovich.entities.UserEntity;
import com.andersenlab.aadamovich.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final UserEntity userEntityFromDb = userRepository.findByUsername(username);
        if (userEntityFromDb == null) {
            throw new UsernameNotFoundException(String.format("User: %s is not found!", username));
        }
        return getUserDetailsObject(userEntityFromDb);
    }

    private UserDetails getUserDetailsObject(UserEntity userEntity) {
        final String principalLogin = userEntity.getUsername();
        final String principalPassword = userEntity.getPassword();
        final List<GrantedAuthority> authorities = getAuthorities(userEntity.getRoles());
        return new User(principalLogin, principalPassword, authorities);
    }

    private List<GrantedAuthority> getAuthorities(Set<Role> userRoles) {
        if (userRoles != null && !userRoles.isEmpty()) {
            List<GrantedAuthority> listOfRoles = new ArrayList();
            userRoles.stream().forEach(role -> listOfRoles.add((GrantedAuthority) role::toString));
            return listOfRoles;
        } else {
            throw new RuntimeException("Wrong role");
        }
    }

}

