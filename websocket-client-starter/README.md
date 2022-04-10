# Websocket Stomp Client Starter Library

This starter library contains the implementation of spring boot websocket stomp client. 

## Usage

1. Import the dependency to your Spring Boot project

   ```bash
   implementation("com.example.notification:websocket-client-starter:1.0.0")
   ```
   
2. Add the following properties to your **application.yml**
   
   ```yaml
   websocket-client:
       broker-url: ws://localhost:8080/stomp
       inbound-topics: "/topic/greetings"
   ```