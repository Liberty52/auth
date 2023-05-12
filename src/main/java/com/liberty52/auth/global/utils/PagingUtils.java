package com.liberty52.auth.global.utils;

import com.liberty52.auth.global.exception.external.PageNumberOutOfRangeException;
import java.util.HashMap;
import java.util.Map;

public class PagingUtils {

    public static final String START_PAGE = "startPage";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String LAST_PAGE = "lastPage";
    public static final String TOTAL_PAGE = "totalPage";

    public static final String CREATED_AT = "createdAt";


    public static Map<String, Long> getPageInfo(long totalPages, long pageNumber) {

        long currentPage = pageNumber + 1; // 0부터 시작하는 페이지 번호를 1부터 시작하는 번호로 변환
        long startPage = currentPage % 10 == 0 ? (currentPage / 10 - 1) * 10 + 1
                : (currentPage / 10) * 10 + 1;
        long lastPage = Math.min(totalPages,
                10L * (currentPage % 10 == 0 ? currentPage / 10 : currentPage / 10 + 1));
        Map<String, Long> returnMap = new HashMap<>();
        returnMap.put(START_PAGE, startPage);
        returnMap.put(CURRENT_PAGE, currentPage);
        returnMap.put(LAST_PAGE, lastPage);
        returnMap.put(TOTAL_PAGE, totalPages);
        return returnMap;
    }

    public static void validatePageNumber(int pageNumber, int totalPages) {
        if (pageNumber < 0 || pageNumber >= totalPages) {
            throw new PageNumberOutOfRangeException();
        }
    }

}
