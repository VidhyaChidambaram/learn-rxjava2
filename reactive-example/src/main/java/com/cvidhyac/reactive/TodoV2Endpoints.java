package com.cvidhyac.reactive;

import com.cvidhyac.reactive.dao.TodoV1Repository;
import com.cvidhyac.reactive.dao.TodoV1RepositoryImpl;
import com.cvidhyac.reactive.entities.Todo;
import com.cvidhyac.reactive.entities.TodoRequest;
import com.cvidhyac.reactive.entities.TodoResponse;
import com.mawashi.nio.annotations.Api;
import com.mawashi.nio.utils.Action;
import com.mawashi.nio.utils.Endpoints;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is going to implement all the CRUD operations for the model
 */
public class TodoV2Endpoints extends Endpoints {

  TodoV1Repository todoV1Repository = TodoV1RepositoryImpl.getInstance();

  public TodoV2Endpoints() {
    setEndpoint("/api/v2/create", create);
    setEndpoint("/api/v2/read/{id}", read);
    setEndpoint("/api/v2/read", readAll);
    setEndpoint("/api/v2/update", update);
    setEndpoint("/api/v2/delete/{id}", delete);
  }

  @Api(path = "/api/v2/create", method = "POST", consumes = "application/json", produces = "application/json")
  private final Action create = (HttpServletRequest request, HttpServletResponse response) -> {
    //return null
  };

  @Api(path = "/api/v2/read/{id}", consumes = "application/json", produces = "application/json")
  private final Action read = (HttpServletRequest request, HttpServletResponse response) -> {
    // implement logic here
  };

  @Api(path = "/api/v2/read", consumes = "application/json", produces = "application/json")
  private final Action readAll = (HttpServletRequest request, HttpServletResponse response) -> {
    // implement logic here
  };

  @Api(path = "/api/v2/update", method = "PUT", consumes = "application/json", produces = "application/json")
  private final Action update = (HttpServletRequest request, HttpServletResponse response) -> {
    // implement logic here
  };

  @Api(path = "/api/v2/delete/{id}", method = "DELETE", consumes = "application/json", produces = "application/json")
  private final Action delete = (HttpServletRequest request, HttpServletResponse response) -> {
    // implement logic here
  };

}
