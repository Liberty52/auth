package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.external.InvalidAdminRoleException;
import com.liberty52.auth.global.exception.external.NoticeNotFoundById;
import com.liberty52.auth.service.controller.dto.NoticeDetailResponse;
import com.liberty52.auth.service.controller.dto.NoticeRetrieveResponse;
import com.liberty52.auth.service.repository.NoticeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AdminNoticeRetrieveServiceImplTest {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private AdminNoticeRetrieveService adminNoticeRetrieveService;

    private final String ROLE_ADMIN = "ADMIN";
    private final String ROLE_USER = "USER";

    @Test
    void test_retrieveNoticesByAdmin() {
        NoticeRetrieveResponse response = adminNoticeRetrieveService.retrieveNoticesByAdmin(ROLE_ADMIN, PageRequest.of(0, 10));

        Assertions.assertNotNull(response);
        Assertions.assertNotEquals(0, response.getStartPage());
        Assertions.assertNotEquals(0, response.getCurrentPage());
        Assertions.assertNotEquals(0, response.getLastPage());
        Assertions.assertNotEquals(0, response.getTotalPage());

        Assertions.assertFalse(response.getContents().isEmpty());
        response.getContents().forEach(res -> {
            Assertions.assertFalse(res.getNoticeId().isBlank());
            Assertions.assertFalse(res.getTitle().isBlank());
            Assertions.assertFalse(res.getCreatedAt().isBlank());
        });
    }

    @Test
    void test_retrieveNoticesByAdmin_when_user_throw_InvalidAdminRoleException() {
        Assertions.assertThrows(
                InvalidAdminRoleException.class,
                () -> adminNoticeRetrieveService.retrieveNoticesByAdmin(ROLE_USER, PageRequest.of(0, 10))
        );
    }

    @Test
    void test_retrieveNoticeDetailByAdmin() {
        final String noticeId = "NOTICE-001";

        NoticeDetailResponse response = adminNoticeRetrieveService.retrieveNoticeDetailByAdmin(ROLE_ADMIN, noticeId);

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.getNoticeId().isBlank());
        Assertions.assertEquals(noticeId, response.getNoticeId());
        Assertions.assertFalse(response.getTitle().isBlank());
        Assertions.assertFalse(response.getContent().isBlank());
        Assertions.assertFalse(response.getCreatedAt().isBlank());
        Assertions.assertFalse(response.isCommentable());
    }

    @Test
    void test_retrieveNoticeDetailByAdmin_when_user_throw_InvalidAdminRoleException() {
        final String noticeId = "NOTICE-000";
        Assertions.assertThrows(
                InvalidAdminRoleException.class,
                () -> adminNoticeRetrieveService.retrieveNoticeDetailByAdmin(ROLE_USER, noticeId)
        );
    }

    @Test
    void test_retrieveNoticeDetailByAdmin_when_wrongNoticeId_throw_NoticeNotFoundById() {
        final String noticeId = "WRONG_NOTICE_ID";
        Assertions.assertThrows(
                NoticeNotFoundById.class,
                () -> adminNoticeRetrieveService.retrieveNoticeDetailByAdmin(ROLE_ADMIN, noticeId)
        );
    }

}