package com.computas.gcloud.todo.model;

import com.google.cloud.datastore.*;

import java.util.Collection;
import java.util.UUID;

import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * Todo entity with Datastore integration.
 */
public class Todo {
    private static final String DATASTORE_KIND = "todo";
    private static final String TEXT_PROPERTY = "text";
    private static final String ID_PROPERTY = "_id";
    public String text;
    public String _id;

    public Todo() {
        _id = UUID.randomUUID().toString();
    }

    public Todo(String _id, String text) {
        this._id = _id;
        this.text = text;
    }

    public Entity addToStore(Datastore datastore, String namespace) {
        validateNamespace(namespace);

        Key key = getBaseKey(datastore, namespace).newKey(_id);
        Entity todoEntity = toEntity(key, _id, text);
        return datastore.add(todoEntity);
    }

    public static Collection<Todo> getAll(Datastore datastore, String namespace) {
        EntityQuery query = Query.newEntityQueryBuilder()
                .setNamespace(namespace)
                .setKind(DATASTORE_KIND)
                .build();

        return stream(spliteratorUnknownSize(datastore.run(query), ORDERED), false)
            .map(Todo::fromEntity)
            .collect(toList());
    }

    public static void delete(Datastore datastore, String namespace, String id) {
        validateNamespace(namespace);
        if (isEmpty(id)) {
            throw new IllegalArgumentException("id cannot be null");
        }

        datastore.delete(getBaseKey(datastore, namespace).newKey(id));
    }

    public static Todo fromEntity(Entity entity) {
        return new Todo(entity.getString(ID_PROPERTY), entity.getString(TEXT_PROPERTY));
    }

    public static Entity toEntity(Key key, String _id, String text) {
        return Entity.newBuilder(key)
                .set(ID_PROPERTY, _id)
                .set(TEXT_PROPERTY, text)
                .build();
    }

    private static void validateNamespace(String namespace) {
        if (isEmpty(namespace)) {
            throw new IllegalArgumentException("namespace cannot be empty");
        }
    }

    private static KeyFactory getBaseKey(Datastore datastore, String namespace) {
        return datastore.newKeyFactory().setNamespace(namespace).setKind(DATASTORE_KIND);
    }
}
