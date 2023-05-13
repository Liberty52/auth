package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.external.InvalidAdminRoleException;
import com.liberty52.auth.service.controller.dto.UserInfoListResponseDto;
import com.liberty52.auth.service.controller.dto.UserInfoResponseDto;
import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.entity.Role;
import com.liberty52.auth.service.repository.AuthRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class UserInfoRetrieveServiceImplTest {
    @Autowired
    UserInfoRetrieveService service;
    @Autowired
    private AuthRepository authRepository;

    @Test
    void basicPath() {
        List<Auth> all = authRepository.findAll();
        int size = 3;
        int page = 0;
        Iterator<Auth> allIter = all.iterator();
        while(true) {
            UserInfoListResponseDto dto = service.retrieveAllByAdmin(Role.ADMIN.name(), PageRequest.of(page, size));
            if(dto.getInfoList().size() == 0) break;
            assertEquals(all.size(), dto.getTotalCount());
            assertEquals(page, dto.getPageNumber());
            assertEquals(dto.getInfoList().size(), dto.getNumberOfElements());
            for (UserInfoResponseDto actual : dto.getInfoList()) {
                assertTrue(allIter.hasNext());
                Auth expected = allIter.next();
                assertEquals(expected.getId(), actual.getId());
            }
            page++;
        }
    }

    @Test
    void InvalidAdminRoleException() {
        assertThrows(InvalidAdminRoleException.class, () -> service.retrieveAllByAdmin(Role.USER.name(), PageRequest.of(0, 1)));
    }
}