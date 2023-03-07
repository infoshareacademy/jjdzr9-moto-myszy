package com.isa.jjdzr.walletweb;

import java.util.List;

public class UserService {
    private List<User> users;

    public User login(User user){
        return new User();
    }

    public void addUser(User user){
        users.add(user);
    }
}
