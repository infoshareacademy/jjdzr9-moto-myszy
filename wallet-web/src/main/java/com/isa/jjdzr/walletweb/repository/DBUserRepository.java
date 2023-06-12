package com.isa.jjdzr.walletweb.repository;

import com.isa.jjdzr.walletweb.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface DBUserRepository extends CrudRepository<User, Long> {


}
