# Google Cloud App Engine workshop example implementation
This is an example implementation of the backend of the workshop at https://github.com/mapster/node-todo.
You will find both a in-memory and a datastore implementation is this repository. The in-memory implementation is on 
the ```master``` branch, while the datastore implementation is on the ```cloud-datastore``` branch.
 
## In-memory
The in-memory application is a very basic implementation that will keep the todos as long as the app engine service is 
up and runnning. We recommend that you attempt this first. It uses a static HashMap of todo instances.

## Google Cloud Datastore
The datastore implementation use the Google Cloud Datastore library to persist todos in the project Datastore. 
The implementation is very basic and only illustrate how simple it can be and most of the integration with the 
Datastore is implemented in the Todo-class. Keep in mind that this is not a good pattern for a proper implementation where one should 
externalize the datastore specific parts into a Repository-implementation (or similar).

## Setup
* Change the ```service``` property of ```src/appengine/app.yaml``` to your allotted namespace
* Change the ```DATASTORE_NAMESPACE``` constant of the ```com.computas.gcloud.todo.facade.TodoResource``` class
* Add Google Cloud Datastore dependency to ```pom.xml```
    ```		
    <dependency>
        <groupId>com.google.cloud</groupId>
        <artifactId>google-cloud-datastore</artifactId>
        <version>0.9.4-beta</version>
    </dependency>
    ```