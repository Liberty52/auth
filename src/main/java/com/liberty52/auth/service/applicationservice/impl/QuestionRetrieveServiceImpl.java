package com.liberty52.auth.service.applicationservice.impl;

import com.liberty52.auth.global.exception.badrequest.PageNumberOutOfRangeException;
import com.liberty52.auth.global.exception.badrequest.PageSizeException;
import com.liberty52.auth.global.exception.forbidden.InvalidAdminRoleException;
import com.liberty52.auth.global.exception.notfound.AuthNotFoundException;
import com.liberty52.auth.global.exception.notfound.QuestionNotFoundById;
import com.liberty52.auth.global.utils.AdminRoleUtils;
import com.liberty52.auth.service.applicationservice.QuestionRetrieveService;
import com.liberty52.auth.service.controller.dto.AdminQuestionRetrieveResponse;
import com.liberty52.auth.service.controller.dto.QuestionDetailResponseDto;
import com.liberty52.auth.service.controller.dto.QuestionReplyResponse;
import com.liberty52.auth.service.controller.dto.QuestionRetrieveResponseDto;
import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.entity.Question;
import com.liberty52.auth.service.entity.QuestionReply;
import com.liberty52.auth.service.entity.Role;
import com.liberty52.auth.service.repository.AuthRepository;
import com.liberty52.auth.service.repository.QuestionRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class QuestionRetrieveServiceImpl implements QuestionRetrieveService {
  private final String startPage = "startPage";
  private final String currentPage = "currentPage";
  private final String lastPage = "lastPage";
  private final String totalPage = "totalPage";
  private final QuestionRepository questionRepository;
  private final AuthRepository authRepository;
  @Override
  public QuestionRetrieveResponseDto retrieveQuestions(String writerId, int pageNumber,int pageSize) {
    if(authRepository.findById(writerId).isEmpty()){
      throw new AuthNotFoundException();
    }
    if (pageSize <= 0) {//페이지당 출력할 데이터 개수를 의미
      throw new PageSizeException();
    }
    PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, Sort.by("createdAt").descending());
    Page<Question> questionList = questionRepository.findAllByWriterId(writerId, pageRequest);
    int totalPages = questionList.getTotalPages();
    if (totalPages == 0 && pageNumber == 0) {
      return new QuestionRetrieveResponseDto(questionList, 0L, 0L, 0L, 0L);
    } else if (pageNumber < 0 || pageNumber >= totalPages) {
      throw new PageNumberOutOfRangeException();
    } else {
      Map<String, Long> pageInfo = getPageInfo(questionList, pageNumber);
      return new QuestionRetrieveResponseDto(questionList, pageInfo.get(currentPage), pageInfo.get(startPage), pageInfo.get(lastPage), pageInfo.get(totalPage));
    }
  }

  @Override
  public QuestionDetailResponseDto retrieveQuestionDetail(String questionId, String writerId) {
    Question question = questionRepository.findById(questionId)
        .orElseThrow(() -> new QuestionNotFoundById(questionId));
    if (!question.getWriterId().equals(writerId)) {
      throw new AuthNotFoundException();
    }
    QuestionReply questionReply = question.getQuestionReply();
    QuestionReplyResponse questionReplyResponse = null;
    if (questionReply != null) {
      questionReplyResponse = new QuestionReplyResponse(questionReply);
    }
    return QuestionDetailResponseDto.create(question, questionReplyResponse);
  }

  private Map<String,Long> getPageInfo(Page<Question> questionList, int pageNumber){
    long totalPages = questionList.getTotalPages();
    long currentPage = pageNumber + 1; // 0부터 시작하는 페이지 번호를 1부터 시작하는 번호로 변환
    long startPage =  currentPage %10 ==0 ? (currentPage/10-1)*10+1 : (currentPage/10)*10 +1;
    long lastPage = Math.min(totalPages, 10L * (currentPage%10 == 0 ? currentPage/10 : currentPage / 10 + 1));
    Map<String,Long> returnMap = new HashMap<>();
    returnMap.put("startPage", startPage);
    returnMap.put("currentPage", currentPage);
    returnMap.put("lastPage", lastPage);
    returnMap.put("totalPage", totalPages);
    return returnMap;
  }

  //admin method
  @Override
  public AdminQuestionRetrieveResponse retrieveQuestionByAdmin(String role, int pageNumber, int pageSize) {
    validateRoleAndPageSize(role,pageSize);
    Page<Question> questionList = findQuestionPage(pageNumber, pageSize);
    if (questionList.isEmpty()){
      return new AdminQuestionRetrieveResponse(questionList.getContent(), Collections.emptyList(),0L, 0L, 0L, 0L);
    }
    List<String> emailList = getEmailList(questionList);
    Map<String, Long> pageInfo = getPageInfo(questionList, pageNumber);
    return new AdminQuestionRetrieveResponse(questionList.getContent(),emailList,
        pageInfo.get(currentPage),pageInfo.get(startPage), pageInfo.get(lastPage),pageInfo.get(totalPage));
  }

  @Override
  public QuestionDetailResponseDto retrieveQuestionDetailByAdmin(String role, String questionId) {
    AdminRoleUtils.checkRole(role);
    Question question = questionRepository.findById(questionId)
        .orElseThrow(() -> new QuestionNotFoundById(questionId));
    QuestionReply questionReply = question.getQuestionReply();
    QuestionReplyResponse questionReplyResponse = null;
    if (questionReply != null) {
      questionReplyResponse = new QuestionReplyResponse(questionReply);
    }
    return QuestionDetailResponseDto.create(question, questionReplyResponse);
  }


  private Page<Question> findQuestionPage(int pageNumber, int pageSize) {
    PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("createdAt").descending());
    Page<Question> questionList = questionRepository.findAll(pageRequest);
    int totalPages = questionList.getTotalPages();
    if (totalPages == 0 && pageNumber == 0) {
      return questionList;
    } else if (pageNumber < 0 || pageNumber >= totalPages) {
      throw new PageNumberOutOfRangeException();
    }
    return questionList;
  }

  private void validateRoleAndPageSize(String role, int pageSize) {
    AdminRoleUtils.checkRole(role);
    if (pageSize <= 0) {
      throw new PageSizeException();
    }
  }

  private List<String> getEmailList(Page<Question> questionList) {
    Map<String, String> emailByWriterIdMap = new HashMap<>();
    for (Auth auth : authRepository.findAll()) {
      emailByWriterIdMap.put(auth.getId(), auth.getEmail());
    }

    List<String> emailList = new ArrayList<>();
    for (Question question : questionList) {
      String email = emailByWriterIdMap.get(question.getWriterId());
      emailList.add(email);
    }
    return emailList;
  }
}
