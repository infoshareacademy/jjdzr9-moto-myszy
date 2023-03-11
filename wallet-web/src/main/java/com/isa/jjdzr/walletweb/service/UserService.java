package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletweb.dto.User;
import com.isa.jjdzr.walletweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository fileUserRepository;

    public List<User> getAll(){
        return fileUserRepository.getAll();
    }

    public User addUser(User user){
        return fileUserRepository.save(user);
    }

    public boolean checkUserName(User user) {
        for (User u : fileUserRepository.getAll()) {
            if (user.getUsername().equals(u.getUsername())) return true;
        }
        return false;
    }
}
