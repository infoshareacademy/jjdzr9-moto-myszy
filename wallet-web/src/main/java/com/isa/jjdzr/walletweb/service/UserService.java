package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletweb.Constants;
import com.isa.jjdzr.walletweb.dto.User;
import com.isa.jjdzr.walletweb.repository.FileUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class UserService {
    private final FileUserRepository fileUserRepository;

    public List<User> getAll() {
        return fileUserRepository.getAll();
    }

    public User addUser(User user) {
        return fileUserRepository.save(user);
    }

    public boolean checkUserName(User user) {
        for (User u : fileUserRepository.getAll()) {
            if (user.getUsername().equals(u.getUsername())) return true;
        }
        return false;
    }

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

    public User find(Long userId) {
        return fileUserRepository.find(userId);
    }
}
