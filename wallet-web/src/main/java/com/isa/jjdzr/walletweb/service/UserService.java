package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletweb.dto.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User addUser(User user);
    boolean checkUserName(User user);
    Long login(User user);
    User find(Long userId);

}
