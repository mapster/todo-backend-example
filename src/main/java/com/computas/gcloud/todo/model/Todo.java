package com.computas.gcloud.todo.model;

import java.util.UUID;

/**
 * Created by ahr on 11.03.17.
 */
public class Todo {
    public String text;
    public String _id;

    public Todo() {
        _id = UUID.randomUUID().toString();
    }
}
