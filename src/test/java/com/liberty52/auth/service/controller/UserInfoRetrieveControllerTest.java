package com.liberty52.auth.service.controller;

import com.liberty52.auth.global.config.WebSecurityConfig;
import com.liberty52.auth.global.exception.external.AuthExceptionHandler;
import com.liberty52.auth.service.applicationservice.UserInfoRetrieveService;
import com.liberty52.auth.service.controller.dto.UserInfoListResponseDto;
import com.liberty52.auth.service.controller.dto.UserInfoResponseDto;
import com.liberty52.auth.service.entity.Role;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {UserInfoRetrieveController.class, AuthExceptionHandler.class},
            excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfig.class)},
            excludeAutoConfiguration = {SecurityAutoConfiguration.class,
            SecurityFilterAutoConfiguration.class,
            OAuth2ClientAutoConfiguration.class,
            OAuth2ResourceServerAutoConfiguration.class})
class UserInfoRetrieveControllerTest {
    @InjectMocks
    UserInfoRetrieveController controller;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AuthExceptionHandler authExceptionHandler;
    @MockBean
    UserInfoRetrieveService service;
    final String LIST_RETRIEVE_API = "/user-info?page=%d&size=%d";

    @Test
    void userInfoListByAdmin() throws Exception {
        int page = 0;
        int size = 3;
        int totalCount = 10;
        List<UserInfoResponseDto> list = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (int i = 0; i < totalCount; i++) {
            String role = i % 2 == 0 ? Role.ADMIN.name() : Role.USER.name();
            list.add(UserInfoResponseDto.of(
                    "id" + i,
                    "name" + i,
                    "phoneNumber" + i,
                    now,
                    role
            ));
        }
        UserInfoListResponseDto dto = nextGiven(list, page, size);

        String api = String.format(LIST_RETRIEVE_API,page++,size);
        mockMvc.perform(get(api).header("LB-Role", Role.ADMIN.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.infoList").value(dto.getInfoList()))
                .andExpect(jsonPath("$.totalCount").value(dto.getTotalCount()))
                .andExpect(jsonPath("$.numberOfElements").value(dto.getNumberOfElements()));

    }

    UserInfoListResponseDto nextGiven(List<UserInfoResponseDto> elements, int page, int size) {
        UserInfoListResponseDto dto = UserInfoListResponseDto.of(
                elements.stream()
                        .skip((long) page * size)
                        .limit(size)
                        .toList(),
                elements.size(),
                page,
                size);
        given(service.retrieveAllByAdmin(any(), any()))
                .willReturn(dto);
        return dto;
    }
}