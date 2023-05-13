package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.external.BadRequestException;
import com.liberty52.auth.global.exception.external.ResourceNotFoundException;
import com.liberty52.auth.global.exception.internal.InvalidNoticeContentException;
import com.liberty52.auth.global.exception.internal.InvalidNoticeTitleException;
import com.liberty52.auth.global.utils.AdminRoleUtils;
import com.liberty52.auth.service.controller.dto.NoticeModifyRequestDto;
import com.liberty52.auth.service.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeModifyServiceImpl implements NoticeModifyService {
    private final NoticeRepository noticeRepository;

    @Override
    public void modify(String role, String noticeId, NoticeModifyRequestDto dto) {
        AdminRoleUtils.checkRole(role);
        noticeRepository.findById(noticeId)
                .orElseThrow(() -> new ResourceNotFoundException("notice", "id", noticeId))
                .modify(dto.getTitle(), dto.getContent(), dto.isCommentable());
    }
}
