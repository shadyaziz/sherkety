package com.tbs.sherkety.login.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.tbs.sherkety.login.validation.PasswordCustomValidator;

@Entity
public class User implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_user")
  private Integer idUser;

  @Column
  @NotNull
  @Email
  private String email;

  @Column(name = "display_name")
  private String displayName;

  @PasswordCustomValidator
  @Transient
  @JsonIgnore
  @JsonProperty(access = Access.WRITE_ONLY)
  @NotNull
  private String password;

  @Column(name = "password")
  @JsonIgnore
  private String hashedPassword;

  @Column(name = "error_counter")
  @JsonIgnore
  private String errorCounter;

  @Column
  private String role;

  public Integer getIdUser() {
    return idUser;
  }

  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getErrorCounter() {
    return errorCounter;
  }

  public void setErrorCounter(String errorCounter) {
    this.errorCounter = errorCounter;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public void setHashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

}
