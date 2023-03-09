package com.isa.jjdzr.walletweb.userservice;

import com.isa.jjdzr.walletweb.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserService {
    private final FileUserRepository fileUserRepository;
    
    public List<User> getAll(){
        return fileUserRepository.getAll();
    }
    public User login(User user){
        for (User u : getAll()) {
            if (user.getUsername().equals(u.getUsername())) {
                if (user.getPassword().equals(u.getPassword())) return u;
            }
        }
        return null;
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
