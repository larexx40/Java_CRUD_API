package com.larexx40.first_project.repository;

import com.larexx40.first_project.model.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    @Override
    <S extends UserEntity> Optional<S> findOne(Example<S> example);

    @Override
    Optional<UserEntity> findById(Long aLong);
    //you can as write mysql query annotate with @Query
//    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    Optional<UserEntity> getUserByEmail(String email);
}
