package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletcore.common.Constants;
import com.isa.jjdzr.walletweb.dto.User;
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
    public List<User> getAll() {
        return fileUserRepository.getAll();
    }

    @Override
    public User addUser(User user) {
        return fileUserRepository.save(user);
    }

    @Override
    public boolean checkUserName(User user) {
        for (User u : fileUserRepository.getAll()) {
            if (user.getUsername().equals(u.getUsername())) return true;
        }
        return false;
    }

    @Override
    public Long login(User user) {
        List<User> allUsers = getAll();
        User existingUser = allUsers.stream()
                .filter(u -> u.getUsername().equals(user.getUsername()))
                .findAny()
                .orElse(null);
        if (isNull(existingUser)) return Constants.WRONG_USERNAME;
        if (existingUser.getPassword().equals(user.getPassword())) {
            return existingUser.getId();
        } else {
            return Constants.WRONG_PASSWORD;
        }
    }

    @Override
    public User find(Long userId) {
        return fileUserRepository.find(userId);
    }
}
