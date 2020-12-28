package com.cvidhyac.reactive.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Todo {

  private String id;
  private String title;
  private String description;
  private LocalDate date;

  public Todo(String title, String description) {
    this.title = title;
    this.description = description;
    this.id = UUID.randomUUID().toString();
    this.date = LocalDate.now();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}
