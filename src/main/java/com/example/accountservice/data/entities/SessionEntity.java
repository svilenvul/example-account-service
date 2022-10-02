package com.example.accountservice.data.entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Type;

@Data
@Entity
@Table(name = "session")
public class SessionEntity {

  @Id
  @Column(name = "id")
  @Type(type = "uuid-char")
  private UUID uuid = UUID.randomUUID();

  @Column(name = "ip_address")
  private String ipAddress;

  @Column(name = "user_id")
  private String userId;
}
