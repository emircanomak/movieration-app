package com.example.filmsitesi.user.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserTest {

    @Test
     void CreateUserModels(){
      User user = new User();
      user.setFullname("AlpOZER");
      user.setUserName("deneme");
      user.setPassword("1");
      user.setId(1L);

      assertEquals("deneme",user.getUserName());
      assertEquals("AlpOZER",user.getFullname());
      assertEquals("1",user.getPassword());
      assertEquals(1L,user.getId());
    }

}