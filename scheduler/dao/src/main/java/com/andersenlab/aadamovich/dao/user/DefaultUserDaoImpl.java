package com.andersenlab.aadamovich.dao.user;

import com.andersenlab.aadamovich.dao.converter.UserConvert;
import com.andersenlab.aadamovich.dao.entity.UserEntity;
import com.andersenlab.aadamovich.dao.repository.UserRepository;
import com.andersenlab.aadamovich.model.dto.UserDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class DefaultUserDaoImpl implements UserBaseDao {

    private final UserRepository userRepository;

    public DefaultUserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public UserDto save(UserDto userDto) {
        final UserEntity userToSave = UserConvert.toEntity(userDto);
        final UserEntity savedUser = userRepository.save(userToSave);
        return UserConvert.toDto(savedUser);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserDto findById(Integer id) {
        final Optional<UserEntity> userFoundInDB = userRepository.findById(id);
        final UserEntity userEntityToReturn = userFoundInDB.orElse(null);
        return UserConvert.toDto(userEntityToReturn);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserDto findByLogin(String login) {
        final UserEntity userEntityFoundInDB = userRepository.findByLogin(login);
        return UserConvert.toDto(userEntityFoundInDB);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean update(UserDto userDto) {
        final int rowsUpdated = userRepository.updateLoginPasswordRole(
                userDto.getId(),
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getRole());
              return rowsUpdated > 0;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean delete(Integer id) {
        final int rowsDeleted = userRepository.deleteUserEntityById(id);
        return rowsDeleted > 0;
    }
}
