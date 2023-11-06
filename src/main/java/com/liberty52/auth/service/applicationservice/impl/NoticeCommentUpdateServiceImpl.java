package com.liberty52.auth.service.applicationservice.impl;

import com.liberty52.auth.global.exception.external.notfound.NoticeCommentNotFoundById;
import com.liberty52.auth.global.exception.external.notfound.NoticeNotFoundById;
import com.liberty52.auth.global.exception.external.notfound.ResourceNotFoundException;
import com.liberty52.auth.global.exception.external.unauthorized.AuthNotFoundException;
import com.liberty52.auth.service.applicationservice.NoticeCommentUpdateService;
import com.liberty52.auth.service.controller.dto.NoticeCommentRequestDto;
import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.entity.Notice;
import com.liberty52.auth.service.entity.NoticeComment;
import com.liberty52.auth.service.repository.AuthRepository;
import com.liberty52.auth.service.repository.NoticeCommentRepository;
import com.liberty52.auth.service.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeCommentUpdateServiceImpl implements NoticeCommentUpdateService {
    private final AuthRepository authRepository;
    private final NoticeRepository noticeRepository;
    private final NoticeCommentRepository noticeCommentRepository;
    @Override
    public NoticeComment updateNoticeComment(String userId, String noticeId, String commentId, NoticeCommentRequestDto requestDto) {
        Auth auth = authRepository.findById(userId).orElseThrow(AuthNotFoundException::new);
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(()-> new NoticeNotFoundById(noticeId));
        NoticeComment noticeComment = noticeCommentRepository.findById(commentId).orElseThrow(()-> new NoticeCommentNotFoundById(commentId));
        noticeComment.setContent(requestDto.getContent());
        noticeComment.setUpdatedAt(LocalDateTime.now());
        return noticeCommentRepository.save(noticeComment);
    }
}
