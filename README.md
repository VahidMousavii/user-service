# Spring Boot - REST & SOAP Microservices Project

This repository contains one of two Spring Boot microservices designed to showcase internal communication using SOAP and external access through RESTful APIs.

## 🧩 Services Overview

### 🔹 User Service
- Register new users (name, email)
- Retrieve a user by ID
- List all users
- Exposes a **SOAP endpoint** for inter-service user lookup

🔗 GitHub: [user-service](https://github.com/VahidMousavii/user-service)

---

## ⚙️ Technologies Used

- Java 21
- Spring Boot 3
- Spring Web (REST)
- Spring-WS (SOAP)
- ModelMapper
- Lombok
- PostgreSQL
- Maven
- Swagger/OpenAPI
- SpringBoot Test
- Clean Architecture principles
- SoC

---

## 🚀 Running the Services

I have deployed both services on a VPS and successfully launched the JAR files on the server.
PostgreSQL has also been installed and properly configured for database persistence.

You can access the Swagger UI of the project here:
🔗 http://37.32.26.21:9090/swagger-ui/index.html

Additionally, the WSDL file for the SOAP API is available at:
🔗 http://37.32.26.21:9090/ws/userService.wsdl
(Note: The base URL is configurable.)

As expected, the implemented SOAP API can be tested directly using tools like SoapUI with the URL above.

Although I initially planned to dockerize the services for production, I wasn’t able to complete it due to time constraints.
However, if you'd like to run the project locally, it can easily be done via IntelliJ IDEA or the terminal using the following Maven command:  "mvn spring-boot:run"

Also, for convenience, I’ve configured the PostgreSQL to accept all incoming connections — so you shouldn’t encounter any database access issues.

Thank you for your consideration.

### 📦 Prerequisites
- Java 21+ : https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html
- Maven 3.8+  : https://maven.apache.org/download.cgi
- PostgreSQL  : https://www.postgresql.org/download/
- -Soap-UI for SOAP Test: https://www.soapui.org/downloads/soapui/

---
