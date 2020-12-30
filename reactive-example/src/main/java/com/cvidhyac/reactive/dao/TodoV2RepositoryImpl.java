package com.cvidhyac.reactive.dao;

import com.cvidhyac.reactive.entities.Todo;
import com.cvidhyac.reactive.entities.TodoRequest;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TodoV2RepositoryImpl implements TodoV2Repository {

  private static TodoV2Repository todoV2Repository;
  private Map<String, Todo> todos;

  public static TodoV2Repository getInstance() {
    return todoV2Repository != null ? todoV2Repository : new TodoV2RepositoryImpl();
  }

  private TodoV2RepositoryImpl() {
    initDb();
  }

  private void initDb() {
    todos = new HashMap<>();
    Todo todo1 = new Todo("learn-reactive", "Beginners intro to reactive");
    Todo todo2 = new Todo("intermediate-reactive", "Deep dive into reactive");
    Todo todo3 = new Todo("advanced-reactive", "Reactive advanced");
    todos.put(todo1.getId(), todo1);
    todos.put(todo2.getId(), todo2);
    todos.put(todo3.getId(), todo3);
  }


  @Override
  public Observable<Todo> create(TodoRequest request) {
    Todo todo = new Todo(request.getTitle(), request.getDescription());
    todos.put(todo.getId(), todo);
    return Observable.just(todo);
  }

  @Override
  public Optional<Todo> read(String id) {
    return Optional.ofNullable(todos.get(id));
  }

  @Override
  public List<Todo> readAll() {
    return new ArrayList<>(todos.values());
  }

  @Override
  public Optional<Todo> update(Todo todo) {
    return todos.get(todo.getId()) != null ?
        Optional.of(todos.replace(todo.getId(), todo)) : Optional.empty();
  }

  @Override
  public Boolean delete(String id) {
    if(todos.get(id) != null) {
      todos.remove(id);
      return true;
    }
    return false;
  }
}
