# Spring Boot E-Commerce System

A full-stack e-commerce application built using **Spring Boot** that provides secure and scalable backend services for managing products, users, carts, and orders. The application follows a clean layered architecture and is containerized using Docker for consistent environments.

---

## 🚀 Features

- User and Admin role-based authentication and authorization
- Product catalog management
- Shopping cart and order management
- Secure RESTful APIs
- Centralized exception handling and validation
- Database persistence with transactional integrity
- Containerized setup using Docker and Docker Compose

---

## 🛠️ Tech Stack

**Backend**
- Java
- Spring Boot
- Spring Security
- JPA / Hibernate
- RESTful APIs

**Database**
- MySQL

**DevOps & Tools**
- Docker
- Docker Compose
- Git & GitHub
- Maven

---

## 🧩 Architecture

The project follows a **layered architecture**:

- **Controller Layer** – Handles HTTP requests and responses
- **Service Layer** – Contains business logic
- **Repository Layer** – Manages database operations using JPA/Hibernate

This separation ensures scalability, maintainability, and clean code structure.

---

## 🔐 Security

- Implemented authentication and authorization using **Spring Security**
- Role-based access control for users and administrators
- Secure handling of API requests and sensitive operations

---

## 🐳 Docker Setup

The application is containerized using **Docker** and orchestrated with **Docker Compose** to ensure:
- Consistent development environments
- Easy service isolation
- Simplified application startup

Docker services include:
- Spring Boot application
- MySQL database

---

## ⚙️ How to Run the Project

### Prerequisites
- Java 17 or later
- Maven
- Docker & Docker Compose

### Steps
1. Clone the repository
   ```bash
   git clone https://github.com/your-username/your-repo-name.git
````

2. Navigate to the project directory

   ```bash
   cd spring-boot-ecommerce
   ```
3. Start the application using Docker Compose

   ```bash
   docker-compose up --build
   ```
4. Access the application

   ```
   http://localhost:8080
   ```

---

## 📌 Future Enhancements

* Payment gateway integration
* Pagination and filtering
* API documentation with Swagger/OpenAPI
* CI/CD pipeline integration
* Cloud deployment


## 👤 Author

**Moshek Shaju Jones J**
Java Developer
LinkedIn: [https://www.linkedin.com/in/msjones07](https://www.linkedin.com/in/msjones07)

