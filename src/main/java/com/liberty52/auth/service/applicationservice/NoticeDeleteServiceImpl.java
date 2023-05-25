package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.notfound.ResourceNotFoundException;
import com.liberty52.auth.global.utils.AdminRoleUtils;
import com.liberty52.auth.service.entity.Notice;
import com.liberty52.auth.service.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeDeleteServiceImpl implements NoticeDeleteService{
  private final NoticeRepository noticeRepository;
  @Override
  public void deleteNotice(String role, String noticeId) {
    AdminRoleUtils.checkRole(role);
    Notice notice = noticeRepository.findById(noticeId)
        .orElseThrow(() -> new ResourceNotFoundException("notice", "id", noticeId));
    noticeRepository.delete(notice);
  }
}
