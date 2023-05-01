package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.external.AuthNotFoundException;
import com.liberty52.auth.global.exception.external.PageNumberOutOfRangeException;
import com.liberty52.auth.global.exception.external.PageSizeException;
import com.liberty52.auth.global.exception.external.QuestionNotFoundById;
import com.liberty52.auth.service.controller.dto.QuestionDetailResponseDto;
import com.liberty52.auth.service.controller.dto.QuestionRetrieveResponseDto;
import com.liberty52.auth.service.entity.Question;
import com.liberty52.auth.service.repository.QuestionRepository;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionRetrieveServiceImpl implements QuestionRetrieveService{
  private final String startPage = "startPage";
  private final String currentPage = "currentPage";
  private final String lastPage = "lastPage";
  private final String totalPage = "totalPage";
  private final QuestionRepository questionRepository;
  @Override
  public QuestionRetrieveResponseDto retrieveQuestions(String writerId, int pageNumber,int pageSize) {
    if (pageSize <= 0) {//페이지당 출력할 데이터 개수를 의미
      throw new PageSizeException();
    }
    PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, Sort.by("createdAt").descending());
    Page<Question> questionList = questionRepository.findAllByWriterId(writerId, pageRequest);
    if (questionList.isEmpty()){
      throw new PageNumberOutOfRangeException();
    }
    Map<String, Long> pageInfo = getPageInfo(questionList, pageNumber);
    return new QuestionRetrieveResponseDto(questionList,pageInfo.get(currentPage),pageInfo.get(startPage),
        pageInfo.get(lastPage),pageInfo.get(totalPage));
  }

  @Override
  public QuestionDetailResponseDto retrieveQuestionDetail(String questionId, String writerId) {
    Question question = questionRepository.findById(questionId)
        .orElseThrow(() -> new QuestionNotFoundById(questionId));
    if (!question.getWriterId().equals(writerId)) {
      throw new AuthNotFoundException();
    }
    return QuestionDetailResponseDto.create(question);
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

}
