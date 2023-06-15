package com.isa.jjdzr.walletweb.repository;

import com.isa.jjdzr.walletweb.dto.UserDto;

import java.util.List;

public interface UserRepository {
    List<UserDto> getAll();
    UserDto find(Long userId);
    UserDto save (UserDto userDto);
}
