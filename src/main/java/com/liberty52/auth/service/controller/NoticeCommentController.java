package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.NoticeCommentCreateService;
import com.liberty52.auth.service.applicationservice.NoticeCommentRetrieveService;
import com.liberty52.auth.service.controller.dto.NoticeCommentRequestDto;
import com.liberty52.auth.service.controller.dto.NoticeCommentResponseDto;
import com.liberty52.auth.service.entity.NoticeComment;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class NoticeCommentController {
    private final NoticeCommentCreateService noticeCommentCreateService;
    private final NoticeCommentRetrieveService noticeCommentRetrieveService;
    @Operation(summary = "공지사항 댓글 생성", description = "공지사항에 대한 댓글을 생성합니다.")
    @PostMapping("/notices/{noticeId}/comments")
    @CrossOrigin
    public ResponseEntity<NoticeCommentResponseDto> createNoticeComment(@RequestHeader(HttpHeaders.AUTHORIZATION) String userId,
                                                                        @PathVariable String noticeId,
                                                                        @RequestBody @Valid NoticeCommentRequestDto requestDto){
        NoticeComment resultEntity = noticeCommentCreateService.createNoticeComment(userId, noticeId, requestDto);
        NoticeCommentResponseDto responseDto = new NoticeCommentResponseDto(resultEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(summary = "공지사항 댓글 조회", description = "공지사항에 대한 댓글을 조회합니다.")
    @GetMapping("/notices/{noticeId}/comments")
    public ResponseEntity<Page<NoticeCommentResponseDto>> retrieveNoticeComment(@PathVariable String noticeId, Pageable pageable){
        Page<NoticeComment> resultEntityPage = noticeCommentRetrieveService.retrieveNoticeComment(noticeId, pageable);
        Page<NoticeCommentResponseDto> responseDtoPage = NoticeCommentResponseDto.convertEntityPageToDtoPage(resultEntityPage);
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoPage);
    }


}
