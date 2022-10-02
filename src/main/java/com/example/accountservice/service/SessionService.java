package com.example.accountservice.service;

import com.example.accountservice.data.entities.SessionEntity;
import com.example.accountservice.data.respository.SessionRepository;
import com.example.accountservice.domain.exception.ElementNotFoundException;
import com.example.accountservice.domain.exception.PreconditionFailedException;
import com.example.accountservice.domain.mapping.Mapper;
import com.example.accountservice.domain.models.Session;
import com.example.accountservice.domain.util.IPAddressValidator;
import com.example.accountservice.domain.util.UUIDValidator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
  private final Logger logger = LoggerFactory.getLogger(SessionService.class.getName());

  @Autowired
  SessionRepository sessionRepository;

  @Autowired
  Mapper<SessionEntity, Session> sessionMapper;


  public Session createSession(String userId, String ip) throws PreconditionFailedException {
    logger.debug("Create session for user {}", userId);

    if (!UUIDValidator.isValidUUID(userId)) {
      throw new IllegalArgumentException("Invalid user ID");
    }
    if (!IPAddressValidator.isIPAddressValid(ip)) {
      throw new IllegalArgumentException("Invalid IP address");
    }
    SessionEntity sessionEntity = new SessionEntity();
    sessionEntity.setUserId(userId);
    sessionEntity.setIpAddress(ip);

    try {
      SessionEntity sessionEntityResult = sessionRepository.save(sessionEntity);
      return sessionMapper.map(sessionEntityResult);
    } catch (DataIntegrityViolationException e) {
      throw new PreconditionFailedException("Can't find user " + userId);
    }
  }

  public List<Session> getSessions(Integer page, Integer size) {
    logger.debug("Get sessions with page {} and size {}", page, size);

    Iterable<SessionEntity> entities;
    if (page != null && size != null) {
      Pageable pagable = PageRequest.of(page, size);
      entities = sessionRepository.findAll(pagable);
    } else {
      entities = sessionRepository.findAll();
    }
    return StreamSupport.stream(entities.spliterator(), false)
        .map(sessionMapper::map).collect(Collectors.toList());
  }

  public void deleteSession(String id) throws ElementNotFoundException {
    logger.debug("Delete session for user {}", id);
    if (!UUIDValidator.isValidUUID(id)) {
      throw new IllegalArgumentException("Invalid ID");
    }
    try {
      sessionRepository.deleteById(UUID.fromString(id));
    } catch (EmptyResultDataAccessException e) {
      throw new ElementNotFoundException();
    }
  }
}
