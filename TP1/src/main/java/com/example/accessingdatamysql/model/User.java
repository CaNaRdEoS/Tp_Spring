package com.example.accessingdatamysql.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;
  
  @Column(nullable = false)
  private String role;
  
  @OneToMany(mappedBy = "user")
  private List<Article> articles;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getRole() {
	    return role;
	  }

  public void setRole(String role) {
	  this.role = role;
  }
  
  public List<Article> getArticles() {
      return articles;
  }

  public void setArticles(List<Article> articles) {
      this.articles = articles;
  }
}