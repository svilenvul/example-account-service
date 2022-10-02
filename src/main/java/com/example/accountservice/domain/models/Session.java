package com.example.accountservice.domain.models;

import lombok.Data;

@Data
public class Session {

  String id;
  String ipAddress;
  String userId;
}
