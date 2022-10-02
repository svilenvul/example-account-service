package com.example.accountservice.service;

import com.example.accountservice.data.entities.AccountEntity;
import com.example.accountservice.data.respository.AccountRepository;
import com.example.accountservice.domain.exception.ElementNotFoundException;
import com.example.accountservice.domain.mapping.Mapper;
import com.example.accountservice.domain.models.Account;
import com.example.accountservice.domain.util.EmailValidator;
import com.example.accountservice.domain.util.UUIDValidator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private final Logger logger = LoggerFactory.getLogger(AccountService.class.getName());
  @Autowired
  AccountRepository accountRepository;
  @Autowired
  Mapper<AccountEntity, Account> accountMapper;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public Account createAccount(String email, String password) {
    logger.debug("Create account for user");
    if (!EmailValidator.isEmailValid(email)) {
      throw new IllegalArgumentException("Invalid email");
    }
    if (password.isBlank()) {
      throw new IllegalArgumentException("Blank password");
    }

    password = passwordEncoder.encode(password);
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setEmail(email);
    accountEntity.setPassword(password);
    AccountEntity accountEntityResult = accountRepository.save(accountEntity);

    return accountMapper.map(accountEntityResult);
  }

  public void modifyAccount(String id, String email, String password) throws ElementNotFoundException {
    logger.debug("Modify account for user {}", id);
    if (!EmailValidator.isEmailValid(email)) {
      throw new IllegalArgumentException("Invalid email");
    }
    if (password.isBlank()) {
      throw new IllegalArgumentException("Blank password");
    }
    if (!UUIDValidator.isValidUUID(id)) {
      throw new IllegalArgumentException("Invalid ID");
    }

    password = passwordEncoder.encode(password);
    int result = accountRepository.setAccountInfoById(email, password, UUID.fromString(id));
    if (result == 0) {
      throw new ElementNotFoundException();
    }
  }

  public void deleteAccount(String id) throws ElementNotFoundException {
    logger.debug("Delete account for user {}", id);

    if (!UUIDValidator.isValidUUID(id)) {
      throw new IllegalArgumentException("Invalid ID");
    }
    // Handle not found use case
    try {
      accountRepository.deleteById(UUID.fromString(id));
    } catch (EmptyResultDataAccessException e) {
      throw new ElementNotFoundException();
    }
  }

  public Account getAccount(String id) throws ElementNotFoundException {
    logger.debug("Get account for user {}", id);
    if (!UUIDValidator.isValidUUID(id)) {
      throw new IllegalArgumentException("Invalid ID");
    }
    Optional<AccountEntity> accountEntity = accountRepository.findById(UUID.fromString(id));

    return accountEntity
        .map(accountMapper::map)
        .orElseThrow(ElementNotFoundException::new);
  }

  public List<Account> getActiveAccounts() {
    logger.debug("Get active accounts");

    List<AccountEntity> accountEntities = accountRepository.findByMarkedForDeletion(false);

    return accountEntities.stream().map(accountMapper::map).collect(Collectors.toList());
  }

}
