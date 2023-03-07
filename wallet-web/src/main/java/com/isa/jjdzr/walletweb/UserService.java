package com.isa.jjdzr.walletweb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@AllArgsConstructor
public class UserService {
    private List<User> users;

    public User login(User user){
        for (User u : users) {
            if (user.getUsername().equals(u.getUsername())) {
                if (user.getPassword().equals(u.getPassword())) return u;
            }
        }
        return null;
    }

    public void addUser(User user){
        users.add(user);
    }
}
