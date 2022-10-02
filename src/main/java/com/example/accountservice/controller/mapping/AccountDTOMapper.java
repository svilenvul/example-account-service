package com.example.accountservice.controller.mapping;

import com.example.accountservice.controller.models.account.AccountDTO;
import com.example.accountservice.domain.mapping.Mapper;
import com.example.accountservice.domain.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDTOMapper implements Mapper<Account, AccountDTO> {

  @Override
  public AccountDTO map(Account input) {
    AccountDTO accountDTO = new AccountDTO();
    accountDTO.setId(input.getId());
    accountDTO.setEmail(input.getEmail());
    accountDTO.setRegisteredAt(input.getRegisteredAt());
    return accountDTO;
  }
}
