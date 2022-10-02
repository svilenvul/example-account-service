package com.example.accountservice.data.respository;

import com.example.accountservice.data.entities.AccountEntity;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, UUID> {

  List<AccountEntity> findByMarkedForDeletion(boolean markedForDeletion);

  @Transactional
  @Modifying
  @Query("update AccountEntity a set a.email = ?1, a.password = ?2 where a.id = ?3")
  int setAccountInfoById(String email, String password, UUID userId);
}
