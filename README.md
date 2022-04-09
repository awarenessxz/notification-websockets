# Websockets Exploration

## Overview

The project explores development for real time communication between frontend and backend using websockets. **The websocket 
server provides proxy communications between frontend and other backend services**. This is to provide an intermediary layer 
for basic communication to frontend. The benefits include a higher degree of control over websocket connections to frontend 
instead of having individual backend services establishing their own websocket connection to frontend.

![Diagram](doc/websockets1.png)

- Communications between Frontend and Websocket Server is via **websockets**
- Communications between Websocket Server and Backend services is via **Rest APIs** or **websockets**
    - Pub/Sub Model using Message Queue is a good alternative

Below are the use cases for the websocket server:

1. **Sending events/messages from backend services to frontend for real time updates**

    Backend services can send events/messages to the websocket server via Rest APIs. These events/messages will then be 
    send (proxy) to the frontend via the websocket connection.

    ![Diagram](doc/websockets2.png)

2. **Real time chat between frontend and backend services**

    Backend services (Eg. Chat Bot Backend) can communicate with frontend via websocket (proxy).

    ![Diagram](doc/websockets3.png)

## Scaling Websocket Server

![Diagram](doc/websockets4.png)

The set up above works perfectly fine with a single instance of websocket server. However, when we try to scale up the 
number of websocket server instances, it will result in message loss as shown in the diagram below.

![Diagram](doc/websockets5.png)

To solve the scalability issue, we make use of a Pub/Sub Model where the webscoket server instance that received the 
event/message will broadcast the event/message to all websocket server instances which will then propagate the event/message 
to the connected frontend client. In our set up below, we make use of Redis Pub/Sub.

![Diagram](doc/websockets6.png)


## Getting Started

1. Start Redis
   ```bash
   sudo docker run --name redis -p 6379:6379 -d redis:6.2.6
   ```

2. Start Frontend
   ```bash
   cd react-app
   npm install
   npm start
   ```
   
3. Start Backend
   ```bash
   cd spring-microservice
   ./gradlew bootrun
   ```

## References

- [Using Stompjs V5](https://stomp-js.github.io/guide/stompjs/using-stompjs-v5.html)
- [Building Scalable Facebook like Notification with Server Sent Events and Redis](https://medium.com/javarevisited/building-scalable-facebook-like-notification-using-server-sent-event-and-redis-9d0944dee618)
- [Spring Boot Redis Pub/Sub](https://www.vinsguru.com/redis-pubsub-spring-boot/)
- [Pub/Sub Messaging with Spring Boot](https://www.baeldung.com/spring-data-redis-pub-sub)
