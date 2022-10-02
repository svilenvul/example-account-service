package com.example.accountservice.data.entities;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "account")
@SQLDelete(sql = "UPDATE account SET is_deleted = true WHERE id=?")
@EntityListeners(AuditingEntityListener.class)
public class AccountEntity {

  @Id
  @Column(name = "id")
  @Type(type = "uuid-char")
  private UUID uuid = UUID.randomUUID();

  private String email;

  private String password;

  @CreatedDate
  @Column(name = "registered_at")
  private LocalDateTime registeredAt;

  @Column(name = "is_deleted")
  private boolean markedForDeletion = Boolean.FALSE;
}
