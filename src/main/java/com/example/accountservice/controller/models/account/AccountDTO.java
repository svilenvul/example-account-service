package com.example.accountservice.controller.models.account;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AccountDTO {

  String id;
  String email;
  LocalDateTime registeredAt;
}
