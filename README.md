# Spring Boot 3 based project structure for Microservices

Developing a production-grade Spring-based microservices ecosystem requires careful consideration of various factors, including coding standards, architectural components, and essential frameworks and libraries. 

-----

### Requirements
* Maven
* JDK 17
* Spring boot 3.1.0
* Spring Cloud Gateway Server
* Spring Cloud Config Server
* Spring Cloud Eureka Server
* Spring Data JPA
* Lombok
* QueryDsl
* MapStruct
* Specification-Arg-Resolver
* Spring security will be added soon

-----

### Framework and Libraries

* **Maven:** Uses Maven Wrapper


* **Java 17:** includes Open JDK 17


* **Spring Boot 3:** provides an opinionated approach to building microservices. It simplifies the configuration and deployment process, autoconfigures various components, and includes built-in support for creating RESTful APIs. Ensure that you use the latest version of Spring Boot and keep track of any updates or new features introduced.

-----

* **Gateway Server:** Use a gateway, such as Spring Cloud Gateway or Netflix Zuul, to handle API routing, load balancing, and security.


* **Config Server:**  Employ a centralized configuration server, like Spring Cloud Config, to manage configurations for microservices.


* **Eureka Server:**  Service Registry: Utilize a service registry, such as Netflix Eureka to enable service discovery and registration.

-----

* **Spring Data JPA:**  Spring Data JPA


* **Lombok:**  Lombok is a library that eliminates boilerplate code by providing annotations to generate getter/setter methods, constructors, equals/hashCode methods, and more. It helps reduce code verbosity and improves developer productivity. Incorporate Lombok into project to simplify the codebase.


* **QueryDsl:**  QueryDsl is a Java library that provides a type-safe and fluent API for constructing database queries. It helps avoid writing raw SQL queries and provides compile-time validation of queries. Integrate QueryDsl into project to enhance query construction and reduce potential errors.


* **MapStruct:**  MapStruct is a code generation library for mapping Java bean properties between different classes. It eliminates the need for writing repetitive mapping code manually. MapStruct generates efficient, type-safe mapping code based on annotations and conventions. Using MapStruct to simplify object mapping in microservices.


* **Specification-Arg-Resolver:**  Specification-Arg-Resolver is a library that simplifies the creation of dynamic queries in Spring Data JPA using Specification objects. It allows constructing complex queries based on provided criteria. By using Specification-Arg-Resolver to build dynamic and flexible queries within microservices ecosystem.
