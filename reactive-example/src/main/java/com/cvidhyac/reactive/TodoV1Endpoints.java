package com.cvidhyac.reactive;

import com.cvidhyac.reactive.dao.TodoV1Repository;
import com.cvidhyac.reactive.dao.TodoV1RepositoryImpl;
import com.cvidhyac.reactive.entities.Todo;
import com.cvidhyac.reactive.entities.TodoRequest;
import com.cvidhyac.reactive.entities.TodoResponse;
import com.mawashi.nio.annotations.Api;
import com.mawashi.nio.utils.Action;
import com.mawashi.nio.utils.Endpoints;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is going to implement all the CRUD operations for the model
 */
public class TodoV1Endpoints extends Endpoints {

  TodoV1Repository todoV1Repository = TodoV1RepositoryImpl.getInstance();

  public TodoV1Endpoints() {
    setEndpoint("/api/v1/create", create);
    setEndpoint("/api/v1/read/{id}", read);
    setEndpoint("/api/v1/read", readAll);
    setEndpoint("/api/v1/update", update);
    setEndpoint("/api/v1/delete/{id}", delete);
  }

  @Api(path = "/api/v1/create", method = "POST", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
  private final Action create = (HttpServletRequest request, HttpServletResponse response) -> {
    TodoRequest todoRequest = (TodoRequest) getDataFromJsonBodyRequest(request, TodoRequest.class);
    Todo todo = todoV1Repository.create(todoRequest);
    toJsonResponse(request, response, new TodoResponse(200, todo));
  };

  @Api(path = "/api/v1/read/{id}", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
  private final Action read = (HttpServletRequest request, HttpServletResponse response) -> {
    String id = getPathVariables(request).get("id");
    Optional<Todo> output = todoV1Repository.read(id);
    toJsonResponse(request, response, new TodoResponse(200, output.isPresent() ? output.get() : "No Todos found"));
  };

  @Api(path = "/api/v1/read", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
  private final Action readAll = (HttpServletRequest request, HttpServletResponse response) -> {
    List<Todo> todoList = todoV1Repository.readAll();
    toJsonResponse(request, response, new TodoResponse(200, todoList));
  };

  @Api(path = "/api/v1/update", method = "PUT", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
  private final Action update = (HttpServletRequest request, HttpServletResponse response) -> {
    Todo beforeUpdate = (Todo) getDataFromJsonBodyRequest(request, Todo.class);
    Optional<Todo> updatedResponse = todoV1Repository.update(beforeUpdate);
    toJsonResponse(request, response, new TodoResponse(200, updatedResponse.isPresent() ? updatedResponse.get() : "No todos found"));
  };

  @Api(path = "/api/v1/delete/{id}", method = "DELETE", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
  private final Action delete = (HttpServletRequest request, HttpServletResponse response) -> {
    String id = getPathVariables(request).get("id");
    boolean deleteStatus = todoV1Repository.delete(id);
    toJsonResponse(request, response, new TodoResponse(200, deleteStatus ? "Todo Deleted" : "Todo not deleted"));
  };

}
