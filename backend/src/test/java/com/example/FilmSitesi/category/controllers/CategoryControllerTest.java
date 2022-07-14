package com.example.filmsitesi.category.controllers;

import com.example.filmsitesi.generic.response.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CategoryControllerTest {
    private static final String BASE_PATH ="/api/v1/categories";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }
    @Test
    void getAllCategories() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_PATH+"/all").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        boolean isSuccess = isSuccess(result);
        assertTrue(isSuccess);
    }

    @Test
    void getCategoryById() throws Exception{
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_PATH+"/302").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        boolean isSuccess = isSuccess(result);
        assertTrue(isSuccess);
    }
    private boolean isSuccess(MvcResult result) throws Exception {
        RestResponse restResponse = this.objectMapper.readValue(result.getResponse().getContentAsString(), RestResponse.class);
        boolean isSuccess = restResponse.isSuccess();
        return isSuccess;
    }
}