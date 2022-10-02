package com.example.accountservice.domain.mapping;

public interface Mapper<I, O> {

  O map(I input);
}
