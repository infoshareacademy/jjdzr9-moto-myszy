package com.isa.jjdzr.walletweb.repository;

import com.isa.jjdzr.walletweb.dto.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User save (User user);
}
