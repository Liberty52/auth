package com.liberty52.auth.service.applicationservice.impl;

import com.liberty52.auth.global.exception.external.notfound.NoticeNotFoundById;
import com.liberty52.auth.global.utils.AdminRoleUtils;
import com.liberty52.auth.global.utils.PagingUtils;
import com.liberty52.auth.service.applicationservice.AdminNoticeRetrieveService;
import com.liberty52.auth.service.controller.dto.NoticeDetailResponse;
import com.liberty52.auth.service.controller.dto.NoticeRetrieveResponse;
import com.liberty52.auth.service.entity.Notice;
import com.liberty52.auth.service.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class AdminNoticeRetrieveServiceImpl implements AdminNoticeRetrieveService {

    private final NoticeRepository noticeRepository;

    @Override
    public NoticeRetrieveResponse retrieveNoticesByAdmin(String role, Pageable pageable) {
        AdminRoleUtils.checkRole(role);

        Page<Notice> notices = noticeRepository.findAllByOrderByCreatedAtDesc(pageable);
        if (CollectionUtils.isEmpty(notices.getContent())) {
            return NoticeRetrieveResponse.EMPTY_RESOURCE_RESPONSE;
        }
        PagingUtils.PageInfo pageInfo = PagingUtils.PageInfo.of(notices.getTotalPages(), pageable.getPageNumber());

        return NoticeRetrieveResponse.of(notices.getContent(), pageInfo);
    }

    @Override
    public NoticeDetailResponse retrieveNoticeDetailByAdmin(String role, String noticeId) {
        AdminRoleUtils.checkRole(role);

        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NoticeNotFoundById(noticeId));

        return new NoticeDetailResponse(notice);
    }
}
