package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletcore.common.Constants;
import com.isa.jjdzr.walletweb.dto.UserDto;
import com.isa.jjdzr.walletweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository fileUserRepository;

    @Override
    public List<UserDto> getAll() {
        return fileUserRepository.getAll();
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        return fileUserRepository.save(userDto);
    }

    @Override
    public boolean checkUserName(UserDto userDto) {
        for (UserDto u : fileUserRepository.getAll()) {
            if (userDto.getUsername().equals(u.getUsername())) return true;
        }
        return false;
    }

    @Override
    public Long login(UserDto userDto) {
        List<UserDto> allUserDtos = getAll();
        UserDto existingUserDto = allUserDtos.stream()
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
        return fileUserRepository.find(userId);
    }
}
