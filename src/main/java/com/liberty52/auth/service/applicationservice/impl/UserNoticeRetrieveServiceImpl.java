package com.liberty52.auth.service.applicationservice.impl;

import static com.liberty52.auth.global.utils.PagingUtils.*;
import static com.liberty52.auth.global.utils.PagingUtils.getPageInfo;

import com.liberty52.auth.global.exception.notfound.NoticeNotFoundById;
import com.liberty52.auth.service.applicationservice.UserNoticeRetrieveService;
import com.liberty52.auth.service.controller.dto.NoticeDetailResponse;
import com.liberty52.auth.service.controller.dto.NoticeRetrieveResponse;
import com.liberty52.auth.service.entity.Notice;
import com.liberty52.auth.service.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserNoticeRetrieveServiceImpl implements
    UserNoticeRetrieveService {
    private final NoticeRepository repository;

    @Override
    public NoticeRetrieveResponse retrieveUserNotice(Pageable pageable) {

        int pageNumber = pageable.getPageNumber();
        pageable = PageRequest.of(pageNumber, pageable.getPageSize(),
                Sort.by(CREATED_AT).descending());

        Page<Notice> notices = repository.findAll(pageable);
        int totalPages = notices.getTotalPages();
        validatePageNumber(pageNumber, totalPages);
        return createResponse(pageable, pageNumber, notices, totalPages);

    }
    @Override
    public NoticeDetailResponse retrieveUserNoticeDetail(String noticeId) {
        return new NoticeDetailResponse(repository.findById(noticeId)
                .orElseThrow(() ->
                        new NoticeNotFoundById(noticeId)));
    }

    private NoticeRetrieveResponse createResponse(Pageable pageable, int pageNumber,
            Page<Notice> notices, int totalPages) {
        if (isEmptyPageNum(pageable, totalPages))
            return NoticeRetrieveResponse.EMPTY_RESOURCE_RESPONSE;

        return new NoticeRetrieveResponse(notices.getContent(),
                getPageInfo(totalPages, pageNumber));
    }

    private boolean isEmptyPageNum(Pageable pageable, int totalPages) {
        return totalPages == 0 && pageable.getPageNumber() == 0;
    }

}
