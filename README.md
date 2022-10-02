# Example Account Service

## Description

This is an implementation of a Example Account Service which should serve as a sample project
for a backend implementation following the principles of OOP and
Clean Architecture.

The server is implemented with Spring Boot and H2 in-memory database.

## Project Structure

The project has the following package structure:

```text
src/main/java
└── com
    └── example
        └── accountservice
            ├── controller
            │   ├── exception
            │   ├── mapping
            │   └── models
            │       ├── account
            │       ├── error
            │       └── session
            ├── data
            │   ├── entities
            │   ├── mapping
            │   └── respository
            ├── domain
            │   ├── exception
            │   ├── mapping
            │   ├── models
            │   └── util
            └── service
```

## APIs

Postman collection with sample requests is provided here:

- [Postman collection](docs/Example%20Account%20Service.postman_collection.json)

## Run Unit Tests

Unit tests can be started with the following command:

```properties
./gradlew test
```

## Start Server

In order to start the server execute:

```properties
./gradlew bootRun
```

The server will be listening on port `8080`.