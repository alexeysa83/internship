package com.andersenlab.aadamovich.dao.repository;

import com.andersenlab.aadamovich.dao.entity.UserEntity;
import com.andersenlab.aadamovich.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository <UserEntity, Integer> {

    UserEntity findByLogin (String login);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE UserEntity u SET u.login=:login, u.password=:password, u.role=:role WHERE u.id=:id")
    int updateLoginPasswordRole(@Param("id") Integer id,
                                @Param("login") String login,
                                @Param("password") String password,
                                @Param("role") Role role);

    int deleteUserEntityById(Integer id);
}
