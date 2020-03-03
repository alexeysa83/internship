package com.andersenlab.aadamovich.service.security;

import com.andersenlab.aadamovich.dao.user.UserBaseDao;
import com.andersenlab.aadamovich.model.Role;
import com.andersenlab.aadamovich.model.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;

/**
 * In web or service layer? Use dao or service?
 */
//TODO Testing
public class AuthenticationService implements UserDetailsService {

    private final UserBaseDao userDao;

    public AuthenticationService(UserBaseDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        final UserDto userFromDB = userDao.findByLogin(login);
        if (userFromDB == null) {
            throw new UsernameNotFoundException(String.format("User: %s is not found!", login));
        }
        return getUserDetailsObject(userFromDB);
    }

    //TODO UserDto implement UserDetails?
    private UserDetails getUserDetailsObject(UserDto dto) {
        final String principalLogin = dto.getLogin();
        final String principalPassword = dto.getPassword();
        final List<GrantedAuthority> authorities = getAuthorities(dto.getRole());
        return new User(principalLogin, principalPassword, authorities);
    }

    private List<GrantedAuthority> getAuthorities(Role role) {
        switch (role) {
            case USER:
                return Collections.singletonList((GrantedAuthority) () -> "ROLE_USER");
            case ADMIN:
                return Collections.singletonList((GrantedAuthority) () -> "ROLE_ADMIN");
            case GRAND_USER:
                return Collections.singletonList((GrantedAuthority) () -> "ROLE_GRAND_USER");
            default:
                throw new RuntimeException("Wrong role");
        }
    }
}
