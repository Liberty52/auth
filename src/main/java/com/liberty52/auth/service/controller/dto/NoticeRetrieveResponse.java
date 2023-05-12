package com.liberty52.auth.service.controller.dto;

import static com.liberty52.auth.global.utils.PagingUtils.CURRENT_PAGE;
import static com.liberty52.auth.global.utils.PagingUtils.LAST_PAGE;
import static com.liberty52.auth.global.utils.PagingUtils.START_PAGE;
import static com.liberty52.auth.global.utils.PagingUtils.TOTAL_PAGE;

import com.liberty52.auth.service.entity.Notice;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class NoticeRetrieveResponse {

    public static final NoticeRetrieveResponse EMPTY_RESOURCE_RESPONSE = new NoticeRetrieveResponse();

    private List<NoticeContent> contents;
    private long startPage;
    private long currentPage;
    private long lastPage;
    private long totalPage;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoticeContent{
        private String noticeId;
        private String title;
        private String createdAt;

        public NoticeContent(Notice notice) {
            this.noticeId = notice.getId();
            this.title = notice.getTitle();
            this.createdAt = notice.getCreatedAt().toLocalDate().toString();
        }
    }

    private NoticeRetrieveResponse(){
        contents = new ArrayList<>();
    }

    public NoticeRetrieveResponse(List<Notice> notices, Map<String, Long> pageNum) {
        contents = notices.stream().map(NoticeContent::new)
                .toList();
        startPage = pageNum.get(START_PAGE);
        currentPage = pageNum.get(CURRENT_PAGE);
        lastPage = pageNum.get(LAST_PAGE);
        totalPage = pageNum.get(TOTAL_PAGE);
    }
}
