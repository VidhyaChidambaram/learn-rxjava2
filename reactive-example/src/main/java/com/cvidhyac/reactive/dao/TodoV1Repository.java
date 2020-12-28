package com.cvidhyac.reactive.dao;

import com.cvidhyac.reactive.entities.Todo;
import com.cvidhyac.reactive.entities.TodoRequest;
import java.util.List;
import java.util.Optional;

public interface TodoV1Repository {

  Todo create(TodoRequest request);
  Optional<Todo> read(String id);
  List<Todo> readAll();
  Optional<Todo> update(Todo todo);
  Boolean delete(String id);

}
