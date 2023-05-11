package com.liberty52.auth.service.applicationservice;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.liberty52.auth.global.exception.external.NoticeNotFoundById;
import com.liberty52.auth.global.exception.external.PageNumberOutOfRangeException;
import com.liberty52.auth.service.controller.dto.NoticeDetailResponse;
import com.liberty52.auth.service.controller.dto.NoticeRetrieveResponse;
import com.liberty52.auth.service.entity.Notice;
import com.liberty52.auth.service.repository.NoticeRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class UserNoticeRetrieveServiceImplTest {


    @Autowired
    UserNoticeRetrieveService userNoticeRetrieveService;

    @Autowired
    EntityManager em;

    @Autowired
    NoticeRepository noticeRepository;

    Notice notice;
    @BeforeEach
    void beforeEach(){
        noticeRepository.deleteAll();
        notice = Notice.create("TITLE", "CONTENT", false);
        noticeRepository.save(notice);
    }



    @Test
    void retrieveUserNotice () throws Exception{
        //given
        PageRequest pageRequest = PageRequest.of(0,10);
        //when
        NoticeRetrieveResponse response = userNoticeRetrieveService.retrieveUserNotice(
                pageRequest);
        //then
        assertThat(response.getContents()).hasSize(1);
        assertThat(response.getStartPage()).isSameAs(1L);
        assertThat(response.getCurrentPage()).isSameAs(1L);
        assertThat(response.getLastPage()).isSameAs(1L);
        assertThat(response.getTotalPage()).isSameAs(1L);
    }

    @Test
    void retrieveUserNotice_PageNumberOutOfRangeException () throws Exception{
        //given
        PageRequest pageRequest = PageRequest.of(100,10);
        //when
        assertThatThrownBy(() -> userNoticeRetrieveService.retrieveUserNotice(
                pageRequest))
                .isInstanceOf(PageNumberOutOfRangeException.class);

    }

    @Test
    void retrieveUserNoticeDetail () throws Exception{
        //given
        //when
        NoticeDetailResponse response = userNoticeRetrieveService.retrieveUserNoticeDetail(
                notice.getId());
        //then
        assertThat(response.getNoticeId()).isEqualTo(notice.getId());
        assertThat(response.getContent()).isEqualTo(notice.getContent());
        assertThat(response.getCreatedAt()).isEqualTo(notice.getCreatedAt().toLocalDate().toString());
        assertThat(response.isCommentable()).isEqualTo(notice.isCommentable());
    }

    @Test
    void retrieveUserNoticeDetail_NoticeNotFoundById () throws Exception{
        //given
        final String FAKE_ID = "MOCK_IDID";
        //when //then
        assertThatThrownBy(() -> userNoticeRetrieveService.retrieveUserNoticeDetail(
                FAKE_ID))
                .isInstanceOf(NoticeNotFoundById.class);


    }


}