package com.liberty52.auth.service.applicationservice.impl;

import com.liberty52.auth.service.applicationservice.ProfileRetrieveService;
import com.liberty52.auth.service.controller.dto.ReviewerProfileResponse;
import com.liberty52.auth.service.repository.AuthRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProfileRetrieveServiceImpl implements
    ProfileRetrieveService {

    private final AuthRepository authRepository;

    @Override
    public Map<String, ReviewerProfileResponse> retrieveReviewerProfile(Set<String> ids) {
        Map<String, ReviewerProfileResponse> response = new HashMap<>();
        for (String id : ids)
            response.put(id, authRepository.findReviewerProfileById(id)
                    .orElseGet(() -> new ReviewerProfileResponse(id,null)));
        return response;
    }
}
