package com.cvidhyac.reactive;

import com.cvidhyac.reactive.dao.TodoV2Repository;
import com.cvidhyac.reactive.dao.TodoV2RepositoryImpl;
import com.cvidhyac.reactive.entities.Todo;
import com.cvidhyac.reactive.entities.TodoRequest;
import com.cvidhyac.reactive.entities.TodoResponse;
import com.mawashi.nio.annotations.Api;
import com.mawashi.nio.utils.Action;
import com.mawashi.nio.utils.Endpoints;
import io.reactivex.Observable;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is going to implement all the CRUD operations for the model
 */
public class TodoV2Endpoints extends Endpoints {

  TodoV2Repository todoV2Repository = TodoV2RepositoryImpl.getInstance();

  public TodoV2Endpoints() {
    setEndpoint("/api/v2/create", create);
    setEndpoint("/api/v2/read/{id}", read);
    setEndpoint("/api/v2/read", readAll);
    setEndpoint("/api/v2/update", update);
    setEndpoint("/api/v2/delete/{id}", delete);
  }

  @Api(path = "/api/v2/create", method = "POST", consumes = "application/json", produces = "application/json")
  private final Action create = (HttpServletRequest request, HttpServletResponse response) -> Observable.just(request)
      .map(req -> (TodoRequest) getDataFromJsonBodyRequest(req, TodoRequest.class))
      .map(input -> todoV2Repository.create(input))
      .subscribe(todo -> {
        try {
          toJsonResponse(request, response, new TodoResponse(200, todo));
        } catch (IOException e) {
          e.printStackTrace();
        }
      });

  @Api(path = "/api/v2/read/{id}", consumes = "application/json", produces = "application/json")
  private final Action read = (HttpServletRequest request, HttpServletResponse response) -> Observable.just(request)
      .map(req -> getPathVariables(request).get("id"))
      .map(id -> todoV2Repository.read(id))
      .subscribe(output -> toJsonResponse(request,
          response,
          output.isPresent() ? new TodoResponse(200, output) : "No TODOs found"));

  @Api(path = "/api/v2/read", consumes = "application/json", produces = "application/json")
  private final Action readAll = (HttpServletRequest request, HttpServletResponse response) -> Observable.just(request)
      .map(req -> todoV2Repository.readAll())
      .subscribe(output -> toJsonResponse(request,
          response,
          output.isEmpty() ? "Nothing to read" : new TodoResponse(200, output)));

  @Api(path = "/api/v2/update", method = "PUT", consumes = "application/json", produces = "application/json")
  private final Action update = (HttpServletRequest request, HttpServletResponse response) -> Observable.just(request)
      .map(req -> (Todo) getDataFromJsonBodyRequest(req, Todo.class))
      .map(todo -> todoV2Repository.update(todo))
      .subscribe(output -> toJsonResponse(request,
          response,
          output.isPresent() ? new TodoResponse(200, output) : "No match found to update"),
          //TODO: Understand how to test errors
          error -> toJsonResponse(request, response, error));

  @Api(path = "/api/v2/delete/{id}", method = "DELETE", consumes = "application/json", produces = "application/json")
  private final Action delete = (HttpServletRequest request, HttpServletResponse response) -> Observable.just(request)
      .map(req -> getPathVariables(req).get("id"))
      .map(id -> todoV2Repository.delete(id))
      .subscribe(output -> toJsonResponse(request,
          response,
          output ? new TodoResponse(200, Boolean.TRUE) : "Nothing to delete"));

}
