package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletcore.common.Constants;
import com.isa.jjdzr.walletweb.mapper.UserMapper;
import com.isa.jjdzr.walletweb.dto.UserDto;
import com.isa.jjdzr.walletweb.entity.UserEntity;
import com.isa.jjdzr.walletweb.repository.DBUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final DBUserRepository dbUserRepository;


    @Override
    public List<UserDto> getAll() {
        List<UserEntity> userEntities = dbUserRepository.findAll();
        return userEntities.stream()
                .map(UserMapper.MAPPER::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        UserEntity userEntity = UserMapper.MAPPER.dtoToEntity(userDto);
        userEntity = dbUserRepository.save(userEntity);
        return UserMapper.MAPPER.entityToDto(userEntity);
    }

    @Override
    public boolean checkUserName(UserDto userDto) {
        for (UserDto u : getAll()) {
            if (userDto.getUsername().equals(u.getUsername())) return true;
        }
        return false;
    }

    @Override
    public Long login(UserDto userDto) {
        List<UserDto> allUsers = getAll();
        UserDto existingUserDto = allUsers.stream()
                .filter(u -> u.getUsername().equals(userDto.getUsername()))
                .findAny()
                .orElse(null);
        if (isNull(existingUserDto)) return Constants.WRONG_USERNAME;
        if (existingUserDto.getPassword().equals(userDto.getPassword())) {
            return existingUserDto.getId();
        } else {
            return Constants.WRONG_PASSWORD;
        }
    }

    @Override
    public UserDto find(Long userId) {
        UserEntity userEntity = dbUserRepository.findUserById(userId);
        return UserMapper.MAPPER.entityToDto(userEntity);
    }
}
