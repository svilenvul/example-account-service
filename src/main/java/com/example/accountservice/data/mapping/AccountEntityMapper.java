package com.example.accountservice.data.mapping;

import com.example.accountservice.data.entities.AccountEntity;
import com.example.accountservice.domain.mapping.Mapper;
import com.example.accountservice.domain.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountEntityMapper implements Mapper<AccountEntity, Account> {

  @Override
  public Account map(AccountEntity input) {
    Account account = new Account();
    account.setPassword(input.getPassword());
    account.setEmail(input.getEmail());
    account.setId(input.getUuid().toString());
    account.setRegisteredAt(input.getRegisteredAt());
    account.setMarkedForDeletion(input.isMarkedForDeletion());
    return account;
  }
}
