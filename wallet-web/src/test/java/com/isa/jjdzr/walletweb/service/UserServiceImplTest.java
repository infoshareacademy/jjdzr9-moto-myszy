package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletweb.dto.User;
import com.isa.jjdzr.walletweb.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void getAll_ShouldReturnAllUsers() {
        // Given
        List<User> expectedUsers = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setPassword("password1");
        expectedUsers.add(user1);
        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");
        user2.setPassword("password2");
        expectedUsers.add(user2);
        when(userRepository.getAll()).thenReturn(expectedUsers);

        // When
        List<User> actualUsers = userService.getAll();

        // Then
        assertEquals(expectedUsers, actualUsers);
        verify(userRepository, times(1)).getAll();
    }

    @Test
    void addUser_ShouldSaveUser() {
        // Given
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password1");
        when(userRepository.save(user)).thenReturn(user);

        // When
        User savedUser = userService.addUser(user);

        // Then
        assertNotNull(savedUser);
        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void checkUserName_ExistingUsername_ShouldReturnTrue() {
        // Given
        User user = new User();
        user.setUsername("existingUser");
        List<User> users = new ArrayList<>();
        User existingUser = new User();
        existingUser.setUsername("existingUser");
        users.add(existingUser);
        when(userRepository.getAll()).thenReturn(users);

        // When
        boolean result = userService.checkUserName(user);

        // Then
        assertTrue(result);
        verify(userRepository, times(1)).getAll();
    }

    @Test
    void checkUserName_NonExistingUsername_ShouldReturnFalse() {
        // Given
        User user = new User();
        user.setUsername("nonExistingUser");
        List<User> users = new ArrayList<>();
        User existingUser = new User();
        existingUser.setUsername("existingUser");
        users.add(existingUser);
        when(userRepository.getAll()).thenReturn(users);

        // When
        boolean result = userService.checkUserName(user);

        // Then
        assertFalse(result);
        verify(userRepository, times(1)).getAll();
    }

    @Test
    void login_ExistingUserWithCorrectPassword_ShouldReturnUserId() {
        // Given
        User user = new User();
        user.setUsername("existingUser");
        user.setPassword("password123");
        User existingUser = new User();
        existingUser.setUsername("existingUser");
        existingUser.setPassword("password123");
        existingUser.setId(1L);
        List<User> users = new ArrayList<>();
        users.add(existingUser);
        when(userRepository.getAll()).thenReturn(users);

        // When
        Long userId = userService.login(user);

        // Then
        assertEquals(existingUser.getId(), userId);
        verify(userRepository, times(1)).getAll();
    }

    @Test
    void find_ExistingUserId_ShouldReturnUser() {
        // Given
        Long userId = 1L;
        User expectedUser = new User();
        expectedUser.setId(userId);
        expectedUser.setUsername("user1");
        expectedUser.setPassword("password1");
        when(userRepository.find(userId)).thenReturn(expectedUser);

        // When
        User actualUser = userService.find(userId);

        // Then
        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).find(userId);
    }
}
