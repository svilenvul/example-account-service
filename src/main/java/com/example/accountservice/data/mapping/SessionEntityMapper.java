package com.example.accountservice.data.mapping;

import com.example.accountservice.data.entities.SessionEntity;
import com.example.accountservice.domain.mapping.Mapper;
import com.example.accountservice.domain.models.Session;
import org.springframework.stereotype.Component;

@Component
public class SessionEntityMapper implements Mapper<SessionEntity, Session> {

  @Override
  public Session map(SessionEntity input) {
    Session session = new Session();
    session.setId(input.getUuid().toString());
    session.setIpAddress(input.getIpAddress());
    session.setUserId(input.getUserId());
    return session;
  }
}
