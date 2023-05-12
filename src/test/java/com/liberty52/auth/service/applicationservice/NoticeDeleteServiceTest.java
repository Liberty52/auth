package com.liberty52.auth.service.applicationservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.liberty52.auth.service.entity.Notice;
import com.liberty52.auth.service.repository.NoticeRepository;
import com.liberty52.auth.service.repository.QuestionRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class NoticeDeleteServiceTest {

  @Autowired
  QuestionRepository questionRepository;

  @Autowired
  NoticeDeleteService noticeDeleteService;

  @Autowired
  NoticeRepository noticeRepository;
  String noticeId;
  String role = "ADMIN";

  @BeforeEach
  void init() {
    String title = "제목";
    String content = "내용";
    Notice notice = Notice.create(title,content,false);
    noticeId = notice.getId();
    noticeRepository.save(notice);
  }

  @Test
  void 공지사항삭제() {
    Notice beforeNotice= noticeRepository.findById(noticeId).get();
    Assertions.assertEquals(beforeNotice.getTitle(), "제목");
    Assertions.assertEquals(beforeNotice.getContent(), "내용");
    noticeDeleteService.deleteNotice(role,noticeId);

    Optional<Notice> afterNotice = noticeRepository.findById(noticeId);
    Assertions.assertTrue(afterNotice.isEmpty());
  }

}
