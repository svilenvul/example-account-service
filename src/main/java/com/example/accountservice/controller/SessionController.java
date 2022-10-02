package com.example.accountservice.controller;

import com.example.accountservice.controller.models.session.CreateSessionRequestBody;
import com.example.accountservice.controller.models.session.SessionDTO;
import com.example.accountservice.domain.mapping.Mapper;
import com.example.accountservice.domain.models.Session;
import com.example.accountservice.service.SessionService;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts/sessions")
public class SessionController {
  private final Logger logger = LoggerFactory.getLogger(SessionController.class.getName());

  @Autowired
  private SessionService sessionService;
  @Autowired
  private Mapper<Session, SessionDTO> sessionDTOMapper;

  @PostMapping
  ResponseEntity<SessionDTO> createsNewSession(@RequestBody CreateSessionRequestBody requestBody, HttpServletRequest request) {
    logger.debug("Received createsNewSession request");

    if (StringUtils.isBlank(requestBody.getUserId())) {
      return ResponseEntity.badRequest().build();
    }

    Session session = sessionService.createSession(requestBody.getUserId(), request.getRemoteAddr());
    return ResponseEntity.ok().body(sessionDTOMapper.map(session));
  }

  @GetMapping
  ResponseEntity<List<SessionDTO>> loadsSession(@RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer size) {
    logger.debug("Received loadsSession request");

    if (page != null && page < 0) {
      return ResponseEntity.badRequest().build();
    }
    if (size != null && size < 0) {
      return ResponseEntity.badRequest().build();
    }

    List<Session> sessions = sessionService.getSessions(page, size);

    List<SessionDTO> sessionDTOs = sessions.stream().map(sessionDTOMapper::map).collect(Collectors.toList());
    return ResponseEntity.ok().body(sessionDTOs);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteSession(@PathVariable String id) {
    logger.debug("Received deleteSession request");

    if (StringUtils.isBlank(id)) {
      return ResponseEntity.badRequest().build();
    }
    sessionService.deleteSession(id);
    return ResponseEntity.noContent().build();
  }
}
