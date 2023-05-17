package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.service.controller.dto.CustomerInfoListResponseDto;
import org.springframework.data.domain.Pageable;

public interface CustomerInfoRetrieveService {
    CustomerInfoListResponseDto retrieveAllByAdmin(String role, Pageable pageable);
}
