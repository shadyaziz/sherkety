package com.tbs.sherkety.company.review.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.tbs.sherkety.company.model.Company;
import com.tbs.sherkety.login.model.User;

@Entity
public class Review implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id_review")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer idReview;

  @ManyToOne
  @JoinColumn(name = "fk_id_user", referencedColumnName = "id_user")
  @JsonIgnore
  @JsonProperty(access = Access.WRITE_ONLY)
  @NotNull
  private User user;

  @ManyToOne
  @JoinColumn(name = "fk_id_company", referencedColumnName = "id_company")
  @JsonIgnore
  @JsonProperty(access = Access.WRITE_ONLY)
  @NotNull
  private Company company;

  @Column(name = "job_title")
  private String jobTitle;

  @Column
  @Min(1)
  @Max(5)
  private float rating;

  @Column(name = "creation_date")
  @CreationTimestamp
  private Timestamp creationDate;

  @Column
  private String text;

  public Integer getIdReview() {
    return idReview;
  }

  public void setId(Integer idReview) {
    this.idReview = idReview;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public Float getRating() {
    return rating;
  }

  public void setRating(Float rating) {
    this.rating = rating;
  }

  public Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Timestamp creationDate) {
    this.creationDate = creationDate;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
