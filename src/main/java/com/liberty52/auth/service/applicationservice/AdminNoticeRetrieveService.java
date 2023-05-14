package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.service.controller.dto.NoticeDetailResponse;
import com.liberty52.auth.service.controller.dto.NoticeRetrieveResponse;
import org.springframework.data.domain.Pageable;

public interface AdminNoticeRetrieveService {

    NoticeRetrieveResponse retrieveNoticesByAdmin(String role, Pageable pageable);

    NoticeDetailResponse retrieveNoticeDetailByAdmin(String role, String noticeId);

}
