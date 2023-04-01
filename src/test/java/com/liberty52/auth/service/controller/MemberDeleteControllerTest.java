package com.liberty52.auth.service.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.liberty52.auth.service.applicationservice.MemberDeleteService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = MemberDeleteController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
class MemberDeleteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    MemberDeleteController memberDeleteController;

    @MockBean
    MemberDeleteService memberDeleteService;

    final String DELETE = "/member";


    @Test
    void getTokenFromGateway() throws Exception{
        //given
        final String token = UUID.randomUUID().toString();
        //when
        mockMvc.perform(delete(DELETE)
                        .header(HttpHeaders.AUTHORIZATION,
                                token))
                //then
                .andExpect(status().isOk())
                .andDo(print());
    }



}
