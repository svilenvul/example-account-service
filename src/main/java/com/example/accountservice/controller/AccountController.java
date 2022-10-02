package com.example.accountservice.controller;

import com.example.accountservice.controller.models.account.AccountDTO;
import com.example.accountservice.controller.models.account.CreateAccountRequestBody;
import com.example.accountservice.controller.models.account.UpdateAccountRequestBody;
import com.example.accountservice.domain.mapping.Mapper;
import com.example.accountservice.domain.models.Account;
import com.example.accountservice.service.AccountService;
import java.util.List;
import java.util.stream.Collectors;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final Logger logger = LoggerFactory.getLogger(AccountService.class.getName());

  @Autowired
  private AccountService accountService;

  @Autowired
  private Mapper<Account, AccountDTO> accountDTOMapper;


  @PostMapping
  ResponseEntity<AccountDTO> createAccount(@RequestBody CreateAccountRequestBody requestBody) {
    logger.debug("Received createAccount request");
    if (StringUtils.isBlank(requestBody.getPassword())) {
      return ResponseEntity.badRequest().build();
    }

    if (StringUtils.isBlank(requestBody.getEmail())) {
      return ResponseEntity.badRequest().build();
    }

    Account account = accountService.createAccount(requestBody.getEmail(), requestBody.getPassword());
    return ResponseEntity.ok().body(accountDTOMapper.map(account));
  }

  @PutMapping("/{id}")
  ResponseEntity<AccountDTO> updateAccount(@PathVariable String id, @RequestBody UpdateAccountRequestBody requestBody) {
    logger.debug("Received updateAccount request");

    if (StringUtils.isBlank(requestBody.getPassword())) {
      return ResponseEntity.badRequest().build();
    }

    if (StringUtils.isBlank(requestBody.getEmail())) {
      return ResponseEntity.badRequest().build();
    }

    accountService.modifyAccount(id, requestBody.getEmail(), requestBody.getPassword());
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteAccount(@PathVariable String id) {
    logger.debug("Received deleteAccount request");

    if (StringUtils.isBlank(id)) {
      return ResponseEntity.badRequest().build();
    }

    accountService.deleteAccount(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  ResponseEntity<AccountDTO> getAccount(@PathVariable String id) {
    logger.debug("Received getAccount request");

    if (StringUtils.isBlank(id)) {
      return ResponseEntity.badRequest().build();
    }

    Account account = accountService.getAccount(id);
    return ResponseEntity.ok().body(accountDTOMapper.map(account));
  }

  @GetMapping("/active")
  ResponseEntity<List<AccountDTO>> getActiveAccounts() {
    logger.debug("Received getActiveAccounts request");

    List<Account> accounts = accountService.getActiveAccounts();
    List<AccountDTO> accountDTOs = accounts.stream().map(accountDTOMapper::map).collect(Collectors.toList());
    return ResponseEntity.ok().body(accountDTOs);
  }

}
