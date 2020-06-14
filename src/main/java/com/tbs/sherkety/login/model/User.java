package com.tbs.sherkety.login.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.tbs.sherkety.company.review.model.Review;
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
  private String encodedPassword;

  @Column(name = "error_counter")
  @JsonIgnore
  private Integer errorCounter;

  @Column
  private String role;

  @OneToMany
  @JoinColumn(name = "fk_id_user", referencedColumnName = "id_user")
  @JsonIgnore
  @JsonProperty(access = Access.WRITE_ONLY)
  @NotNull
  private List<Review> reviewList;

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

  public Integer getErrorCounter() {
    return errorCounter;
  }

  public void setErrorCounter(Integer errorCounter) {
    this.errorCounter = errorCounter;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getEncodedPassword() {
    return encodedPassword;
  }

  public void setEncodedPassword(String encodedPassword) {
    this.encodedPassword = encodedPassword;
  }

}
