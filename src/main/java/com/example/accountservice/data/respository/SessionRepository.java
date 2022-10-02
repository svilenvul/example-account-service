package com.example.accountservice.data.respository;

import com.example.accountservice.data.entities.SessionEntity;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends PagingAndSortingRepository<SessionEntity, UUID> {

}
