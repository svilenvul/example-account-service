package com.example.accountservice.controller.models.account;

import lombok.Data;

@Data
public class CreateAccountRequestBody {

  String email;
  String password;
}
