# learn-rxjava2
Notes created for learning RxJava2

## What is reactive programming?
* Asynchronous non-blocking approach.
* The thread making the request does not wait for the response.
* When the response is ready, the response is being served by the Entity B
via a callback (another thread)

## JSR 340 - Servlet 3.1 Spec
- Servlet 2.x - Http request managed by one thread blocked until response is sent
(blocking thread, synchronous imperative approach)

- Servlet 3.0 - Http request can be serviced asynchronously, but the other thread
must wait for the data to be fetched.

- Servlet 3.1 - Http request managed by one thread, passed to other threads with
callbacks (No thread must wait, when data is ready, it calls back the requester)

## Embedded Web Server

Typical Tomcat 9.x as of now uses single thread per model (non-reactive). So, for reactive apps,
look for reactive web server so we can leverage the real power of reactive.

- Jetty 9.x
- Undertow (internally uses Netty)


## Advantages of reactive paradigm

- Less CPU : <br>
In blocking micro services, 200 threads will wait for 200 process. Hence more CPU is used.
In non-blocking queue, 200 threads will process 200 simultaneous requests are immediately serviced
and does not wait. Hence, less CPU is used.
- Less Memory <br/>
Reactive programming is better for cloud models (Azure, GCP, AWS).
In blocking queue, threads are not released until response is sent. So, more heap usage.
- Less time
- Less instances
- Long term cost savings

## ReactiveJ

ReactiveJ has both servlet implementation with and without RxJava2. Depending on what we add,
the final implementation will be blocking or non-blocking.

* V1 : Blocking synchronous 
* V2: Non-Blocking asynchronous approach


## Mini - Project : TodoEndpoint

* Demonstrates basic reactive knowledge using observable operations 
and ability to display call back output to the user.

- Simple [Todo Model](reactive-example/src/main/java/com/cvidhyac/reactive/entities/Todo.java) with 
3 fields title, description as required fields and rest optional.
- Use RxJava2, with reactiveJ.
- Two POJO classes, one for request - [TodoRequest](reactive-example/src/main/java/com/cvidhyac/reactive/entities/TodoRequest.java), 
and one for response - [TodoResponse](reactive-example/src/main/java/com/cvidhyac/reactive/entities/TodoResponse.java)

* Blocking (Java 8) implementation - [TodoV1Endpoint](reactive-example/src/main/java/com/cvidhyac/reactive/TodoV1Endpoints.java)
* Non-blocking (Reactive) implementation - [TodoV2Endpoint](reactive-example/src/main/java/com/cvidhyac/reactive/TodoV2Endpoints.java)




