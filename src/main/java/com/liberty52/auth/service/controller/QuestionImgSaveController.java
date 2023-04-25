package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.QuestionImgSaveService;
import com.liberty52.auth.service.controller.dto.ReviewerProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class QuestionImgSaveController {

    private final QuestionImgSaveService questionImgSaveService;

    @PostMapping("/questions/img")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> saveImg(@RequestPart(value = "file") MultipartFile imageFile){
        return ResponseEntity.ok(questionImgSaveService.saveImg(imageFile));
    }
}
