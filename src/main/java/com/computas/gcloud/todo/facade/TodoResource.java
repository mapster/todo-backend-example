package com.computas.gcloud.todo.facade;

import com.computas.gcloud.todo.model.Todo;
import com.google.cloud.datastore.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Resource endpoint for Todo-notes.
 */
@RestController
@RequestMapping("/api/todos")
public class TodoResource {

    private static final String DATASTORE_NAMESPACE = "ahr";

    @Autowired
    private Datastore datastore;

    @CrossOrigin
    @RequestMapping( method = RequestMethod.GET)
    public Collection<Todo> getTodos() {
        return Todo.getAll(datastore, DATASTORE_NAMESPACE);
    }

    @CrossOrigin
    @RequestMapping( method = RequestMethod.POST)
    public Collection<Todo> createTodo(@RequestBody Todo todo) {
        todo.addToStore(datastore, DATASTORE_NAMESPACE);
        return Todo.getAll(datastore, DATASTORE_NAMESPACE);
    }

    @CrossOrigin
    @RequestMapping( value = "{id}", method = RequestMethod.DELETE)
    public Collection<Todo> deleteTodo(@PathVariable String id) {
        Todo.delete(datastore, DATASTORE_NAMESPACE, id);
        return Todo.getAll(datastore, DATASTORE_NAMESPACE);
    }
}
