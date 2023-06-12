package com.isa.jjdzr.walletweb.repository;

import com.isa.jjdzr.walletweb.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DBUserRepository extends CrudRepository<UserEntity, Long> {

    List<UserEntity> findAll();
    UserEntity findUserById(Long id);
    UserEntity save(UserEntity userEntity);
}
