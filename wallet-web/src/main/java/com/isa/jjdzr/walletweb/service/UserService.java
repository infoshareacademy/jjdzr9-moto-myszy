package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletweb.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();
    UserDto addUser(UserDto userDto);
    boolean checkUserName(UserDto userDto);
    Long login(UserDto userDto);
    UserDto find(Long userId);

}
