package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.QuestionImgSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class QuestionImgSaveController {

    private final QuestionImgSaveService questionImgSaveService;

    @PostMapping("/questions/img")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> saveQuestionImage(@RequestPart(value = "file") MultipartFile imageFile){
        return ResponseEntity.ok(questionImgSaveService.saveQuestionImage(imageFile));
    }
}
