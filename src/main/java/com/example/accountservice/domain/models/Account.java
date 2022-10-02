package com.example.accountservice.domain.models;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Account {

  String id;
  String email;
  String password;
  LocalDateTime registeredAt;
  boolean markedForDeletion;
}
