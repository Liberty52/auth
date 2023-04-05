package com.liberty52.auth.service.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ModifyRequestDto {
  @NotBlank
  private String originPassword;
  @NotBlank
  private String updatePassword;
  @NotBlank
  private String phoneNumber;

  private String name;
}
