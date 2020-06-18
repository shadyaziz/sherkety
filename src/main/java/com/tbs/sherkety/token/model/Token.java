package com.tbs.sherkety.token.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tbs.sherkety.user.model.User;
import com.tbs.sherkety.user.model.constant.UserOperation;

@Entity
public class Token implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id_token")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer idToken;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_id_user_token", referencedColumnName = "id_user")
  @JsonIgnore
  @NotNull
  private User user;

  @Column
  private String code;

  @Column(name = "creation_date")
  @CreationTimestamp
  private LocalDateTime creationDate;

  @Column(name = "expiry_date")
  private LocalDateTime expiryDate;

  @Column(name = "token_count")
  private Integer tokenCount;

  @Column(name = "operation")
  @Enumerated(EnumType.STRING)
  private UserOperation operation;

  public Integer getIdToken() {
    return idToken;
  }

  public void setIdToken(Integer idToken) {
    this.idToken = idToken;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public LocalDateTime getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(LocalDateTime expiryDate) {
    this.expiryDate = expiryDate;
  }

  public Integer getTokenCount() {
    return tokenCount;
  }

  public void setTokenCount(Integer tokenCount) {
    this.tokenCount = tokenCount;
  }

  public UserOperation getOperation() {
    return operation;
  }

  public void setOperation(UserOperation operation) {
    this.operation = operation;
  }
}
