package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.QuestionRetrieveService;
import com.liberty52.auth.service.controller.dto.QuestionRetrieveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuestionRetrieveController {
  private final QuestionRetrieveService questionRetrieveService;
  @GetMapping("/questions")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Page<QuestionRetrieveResponseDto>> retrieveQuestion(@RequestHeader(HttpHeaders.AUTHORIZATION) String writerId,
      @RequestParam(value = "page", defaultValue = "0") int pageNumber,
      @RequestParam(value = "size", defaultValue = "10") int size){
    return ResponseEntity.ok(questionRetrieveService.retrieveQuestions(writerId,pageNumber,size));
  }

}
