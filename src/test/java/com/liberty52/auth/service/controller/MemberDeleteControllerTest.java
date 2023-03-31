package com.liberty52.auth.service.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.liberty52.auth.service.applicationservice.MemberDeleteService;
import com.netflix.discovery.converters.Auto;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    final String DELETE_C1 = "/member/c1";
    final String DELETE_C2 = "/member/c2";
    final String DELETE_C3 = "/member/c3";

    @Test
    void case1_getEmailFromGateway () throws Exception{
        //given
        final String mockAuth = "foo@bar.com";
        //when
        mockMvc.perform(delete(DELETE_C1)
                .header(HttpHeaders.AUTHORIZATION,
                        mockAuth))
                //then
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void case2_getIdFromGateway () throws Exception{
        //given
        final String mockAuth = UUID.randomUUID().toString();
        //when
        mockMvc.perform(delete(DELETE_C2)
                        .header(HttpHeaders.AUTHORIZATION,
                                mockAuth))
                //then
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void case3_getAuthentication () throws Exception{
        //given
        final String mockAuth = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
                + "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ."
                + "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"; // randomJWT
        //when
        mockMvc.perform(delete(DELETE_C3)
                        .header(HttpHeaders.AUTHORIZATION,
                                mockAuth))
                //then
                .andExpect(status().isOk())
                .andDo(print());
    }



}
