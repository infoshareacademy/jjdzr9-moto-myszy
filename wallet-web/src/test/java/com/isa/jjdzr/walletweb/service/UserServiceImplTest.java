package com.isa.jjdzr.walletweb.service;
import com.isa.jjdzr.walletweb.dto.UserDto;
import com.isa.jjdzr.walletweb.entity.UserEntity;
import com.isa.jjdzr.walletweb.repository.DBUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private UserServiceImpl userService;

    @Mock
    private DBUserRepository dbUserRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(dbUserRepository);
    }

    @Test
    void getAll() {
        when(dbUserRepository.findAll()).thenReturn(Arrays.asList(new UserEntity()));
        List<UserDto> result = userService.getAll();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void addUser() {
        UserDto userDto = new UserDto();
        when(dbUserRepository.save(any(UserEntity.class))).thenReturn(new UserEntity());
        UserDto result = userService.addUser(userDto);
        assertNotNull(result);
    }

    @Test
    void checkUserName() {
        UserDto userDto = new UserDto();
        userDto.setUsername("testUser");
        when(dbUserRepository.findAll()).thenReturn(Arrays.asList(new UserEntity()));
        boolean result = userService.checkUserName(userDto);
        assertFalse(result);
    }


    @Test
    void find() {
        when(dbUserRepository.findUserById(any(Long.class))).thenReturn(new UserEntity());
        UserDto result = userService.find(1L);
        assertNotNull(result);
    }
}