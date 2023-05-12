package com.liberty52.auth.service.controller.dto;

import com.liberty52.auth.service.entity.Auth;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
public class UserInfoListResponseDto {
    private List<UserInfoResponseDto> infoList;
    private long totalCount;
    private int numberOfElements;
    private int pageNumber;
    private boolean hasPrev;
    private boolean hasNext;
    private boolean isFirst;
    private boolean isLast;

    public static UserInfoListResponseDto of(Page<Auth> page) {
        return builder()
                .infoList(page.getContent().stream().map(UserInfoResponseDto::of).toList())
                .totalCount(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .pageNumber(page.getNumber())
                .hasPrev(page.hasPrevious())
                .hasNext(page.hasNext())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    public static UserInfoListResponseDto of(List<UserInfoResponseDto> list, int totalCount, int pageNumber, int size) {
        return builder()
                .infoList(list)
                .totalCount(totalCount)
                .numberOfElements(list.size())
                .pageNumber(pageNumber)
                .hasPrev(pageNumber != 0)
                .hasNext(pageNumber * size < totalCount)
                .isFirst(pageNumber == 0)
                .isLast(pageNumber * size >= totalCount)
                .build();
    }
}
