package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.service.controller.dto.NoticeCreateRequestDto;
import com.liberty52.auth.service.controller.dto.QuestionCreateRequestDto;
import com.liberty52.auth.service.entity.Notice;
import com.liberty52.auth.service.entity.Question;
import com.liberty52.auth.service.entity.QuestionStatus;
import com.liberty52.auth.service.repository.NoticeRepository;
import com.liberty52.auth.service.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.liberty52.auth.service.entity.Role.ADMIN;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class NoticeCreateServiceTest {

    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    NoticeCreateService noticeCreateService;

    @Test
    void 공지추가() {
        String title = "테스트제목";
        NoticeCreateRequestDto dto = NoticeCreateRequestDto.create(title, "내용", true);
        noticeCreateService.createNotice(ADMIN.name(), dto);

        Notice notice = noticeRepository.findByTitle(title).orElse(null);
        assertThat(notice.getTitle()).isEqualTo(title);
        assertThat(notice.getContent()).isEqualTo("내용");
        assertThat(notice.isCommentable()).isEqualTo(true);
        assertThat(notice.getCreatedAt().toLocalDate()).isEqualTo(LocalDate.now());
    }
}
