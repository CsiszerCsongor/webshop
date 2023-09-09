# Config server settings
* Using the `@EnableConfigServer` annotation

# Check configuration server.
* Get default configurations
```http request
GET http://localhost:8081/user-service/default
```
* Get environment specific configuration
```http request
GET http://localhost:8081/user-service/dev
```
