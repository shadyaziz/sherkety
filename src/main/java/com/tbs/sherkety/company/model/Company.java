package com.tbs.sherkety.company.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import com.tbs.sherkety.company.review.model.Review;

@Entity
public class Company implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id_company")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer idCompany;

  @Column
  @NotBlank
  private String name;

  @Column
  @NotBlank
  private String description;

  @Column
  @NotBlank
  private String address;

  @Column
  private String size;

  @Column
  private String logo;

  @OneToMany(mappedBy = "company")
  private List<Review> reviewList;

  @Column
  @Min(1)
  @Max(5)
  private float rating;

  public Integer getIdCompany() {
    return idCompany;
  }

  public void setIdCompany(Integer idCompany) {
    this.idCompany = idCompany;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public List<Review> getReviewList() {
    return reviewList;
  }

  public void setReviewList(List<Review> reviewList) {
    this.reviewList = new ArrayList<>(reviewList);
  }

  // public float getRating() {
  // if(rating == 0) {
  // if(reviewList == null || reviewList.isEmpty()){
  // return 0;
  // }
  // float accumaltedRating = 0;
  // for(Review review : reviewList) {
  // accumaltedRating += review.getRating();
  // }
  // rating = Math.round(accumaltedRating/reviewList.size());
  // }
  //
  //
  // return rating;
  // }

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

}
