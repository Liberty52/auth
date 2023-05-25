package com.liberty52.auth.service.applicationservice.impl;

import com.liberty52.auth.global.utils.AdminRoleUtils;
import com.liberty52.auth.service.applicationservice.NoticeCreateService;
import com.liberty52.auth.service.controller.dto.NoticeCreateRequestDto;
import com.liberty52.auth.service.entity.Notice;
import com.liberty52.auth.service.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeCreateServiceImpl implements NoticeCreateService {
    
    private final NoticeRepository noticeRepository;
    
    @Override
    public void createNotice(String role, NoticeCreateRequestDto dto) {
        AdminRoleUtils.checkRole(role);
        Notice notice = Notice.create(dto.getTitle(), dto.getContent(), dto.isCommentable());
        noticeRepository.save(notice);
    }
}
