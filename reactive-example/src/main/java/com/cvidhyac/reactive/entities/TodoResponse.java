package com.cvidhyac.reactive.entities;

import java.util.Date;

public class TodoResponse {

  private int status;
  private Object response;
  private Date dateTime;

  public TodoResponse(int status, Object response) {
    this.status = status;
    this.response = response;
    this.dateTime = new Date();
  }

}
