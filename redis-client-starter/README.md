# Redis Client Starter Library

This starter library contains the implementation for quick integration for publishing and subscribing to redis.

## Usage

1. Import the dependency to your Spring Boot project

   ```bash
   implementation("com.example.notification:redis-client-starter:1.0.0")
   ```
   
2. Add the following properties to your **application.yml**
   
   ```yaml
   redis-client:
    outbound-topics: 
       - "TOAST_MESSAGE"
    inbound-topics: 
       - "GREETINGS"
   
   spring:
     redis:
       host: localhost   # Redis Server Host
       port: 6379        # Redis Server Port
       ssl: false        # Enable SSL support
       database: 0       # database index
   ```

3. Create Redis Service

   ```kotlin
   @Service
   class RedisService(
       reactiveRedisTemplate: ReactiveRedisTemplate<String, String>,
       redisClientProperties: RedisClientProperties
   ): AbstractRedisService(reactiveRedisTemplate, redisClientProperties) {
       override fun processReceivedMessage(topic: String, message: String) {
           logger.info("Processing redis event - topic: $topic, message: $message")
       }
   }
   ```

