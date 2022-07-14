package com.example.filmsitesi.user.service;

import com.example.filmsitesi.user.dtos.UserRequest;
import com.example.filmsitesi.user.entities.User;
import com.example.filmsitesi.user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    private  UserService userService;
    @Test
    void getAllUsers() {
        List<User> userlist = userRepository.findAll();
        Mockito.when(userRepository.findAll()).thenReturn(userlist);
        List<User>userListServ=userService.getAllUsers();
        assertEquals(userlist,userListServ);
    }

    @Test
    void saveOneUser() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.save(any())).thenReturn(user);
        Mockito.when(user.getUserName()).thenReturn("deneme");
        User userChecked = userService.saveOneUser(userRequest);
        assertEquals("deneme", userChecked.getUserName());
    }

    @Test
    void deleteAll() {

        String deleteChecked = userService.deleteAll();
        assertEquals("All clear!",deleteChecked);

    }

    @Test
    void deleteById() {
       Mockito.doNothing().when(userRepository).deleteById(anyLong());
       userService.deleteById(2L);
        verify(userRepository).deleteById(anyLong());
    }

    @Test
    void getOneUserByUserName() {
        User user = Mockito.mock(User.class);
        user.setFullname("deneme");
        userRepository.save(user);
        Optional<User> userchecked = userService.getOneUserByUserName("deneme");
        assertNotNull(userchecked);
    }
}