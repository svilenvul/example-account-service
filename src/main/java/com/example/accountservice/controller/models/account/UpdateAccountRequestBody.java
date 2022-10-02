package com.example.accountservice.controller.models.account;

import lombok.Data;

@Data
public class UpdateAccountRequestBody {

  private String email;
  private String password;
}
