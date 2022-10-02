package com.example.accountservice.controller.mapping;

import com.example.accountservice.controller.models.session.SessionDTO;
import com.example.accountservice.domain.mapping.Mapper;
import com.example.accountservice.domain.models.Session;
import org.springframework.stereotype.Component;

@Component
public class SessionDTOMapper implements Mapper<Session, SessionDTO> {

  @Override
  public SessionDTO map(Session input) {
    SessionDTO sessionDTO = new SessionDTO();
    sessionDTO.setId(input.getId());
    sessionDTO.setUserId(input.getUserId());
    return sessionDTO;
  }
}
