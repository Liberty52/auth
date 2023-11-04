package com.liberty52.auth.service.controller;


import com.liberty52.auth.service.applicationservice.NoticeCommentCreateService;
import com.liberty52.auth.service.applicationservice.NoticeCommentRetrieveService;
import com.liberty52.auth.service.controller.dto.NoticeCommentRequestDto;
import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.entity.Notice;
import com.liberty52.auth.service.entity.NoticeComment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoticeCommentController.class)
public class NoticeCommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private NoticeCommentCreateService noticeCommentCreateService;
    @MockBean
    private NoticeCommentRetrieveService noticeCommentRetrieveService;
    private final String testNoticeId = "NOTICE-001";
    private final String testWriterID = "TESTER-001";

    @Test
    @WithMockUser
    void 공지사항댓글생성_성공() throws Exception {
        //Given
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("content","Test Comment Content");
        String jsonData = objectMapper.writeValueAsString(requestData);

        NoticeComment noticeCommentMock = new NoticeComment();
        noticeCommentMock.setNotice(Notice.create("testTitle","testContent",true));
        noticeCommentMock.setWriter(new Auth());
        when(noticeCommentCreateService.createNoticeComment(anyString(), anyString(), any(NoticeCommentRequestDto.class)))
                .thenReturn(noticeCommentMock);

        //When
        mockMvc.perform(post("/notices/" + testNoticeId + "/comments")
                        .header(HttpHeaders.AUTHORIZATION, testWriterID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData)
                        .with(csrf()))
                //Then
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void 공지사항댓글생성_실패_내용없음() throws Exception {
        //given
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("content"," ");
        String jsonData = objectMapper.writeValueAsString(requestData);

        //when
        mockMvc.perform(post("/notices/" + testNoticeId + "/comments")
                        .header(HttpHeaders.AUTHORIZATION, testWriterID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData)
                        .with(csrf()))
                //then
                .andExpect(status().isBadRequest());
    }
}
