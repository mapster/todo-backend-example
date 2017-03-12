package com.computas.gcloud.todo.facade;

import com.computas.gcloud.todo.model.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Resource endpoint for Todo-notes.
 */
@RestController
@RequestMapping("/api/todos")
public class TodoResource {

    private static Map<String, Todo> todos = new HashMap<>();

    @CrossOrigin
    @RequestMapping( method = RequestMethod.GET)
    public Collection<Todo> getTodos() {
        return todos.values();
    }

    @CrossOrigin
    @RequestMapping( method = RequestMethod.POST)
    public Collection<Todo> createTodo(@RequestBody Todo todo) {
        todos.put(todo._id, todo);
        return todos.values();
    }

    @CrossOrigin
    @RequestMapping( value = "{id}", method = RequestMethod.DELETE)
    public Collection<Todo> deleteTodo(@PathVariable String id) {
        todos.remove(id);
        return todos.values();
    }
}
