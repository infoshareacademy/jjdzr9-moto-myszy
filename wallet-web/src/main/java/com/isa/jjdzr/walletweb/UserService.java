package com.isa.jjdzr.walletweb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@AllArgsConstructor
public class UserService {
    private List<UserDto> users;

    public UserDto login(UserDto user){
        for (UserDto u : users) {
            if (user.getUsername().equals(u.getUsername())) {
                if (user.getPassword().equals(u.getPassword())) return u;
            }
        }
        return null;
    }

    public void addUser(UserDto user){
        users.add(user);
    }
}
