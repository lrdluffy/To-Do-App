# To‑Do App

A Spring Boot-based RESTful To‑Do list application with PostgreSQL and Docker support.

## 🚀 Features

- Full CRUD operations on tasks
- PostgreSQL integration
- Docker & Docker Compose support
- RESTful API following best practices
- Input validation and error handling
- Swagger UI for easy API testing

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Docker & Docker Compose
- Swagger (Springdoc OpenAPI)

## 💻 Getting Started

### Prerequisites

- Java 21+
- Docker & Docker Compose
- Git

---

## 🐳 Run with Docker (Recommended)

> Ensure Docker is installed and running.

### 1. Clone the repository

```bash
git clone https://github.com/lrdluffy/To-Do-App.git
cd To-Do-App
```

### 2. Run with Docker Compose

```bash
docker-compose up --build
```

This will:
- Build the Spring Boot application
- Start a PostgreSQL container
- Run the app and expose it on `http://localhost:8080`

### 3. Access the App
- API: `http://localhost:8080/api/tasks`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

## 🧰 Manual Setup (Without Docker)

### Configure PostgreSQL

- Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/todo_db
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

## 🗂️ API Endpoints

- `GET /api/tasks` – List all tasks
- `GET /api/tasks/{id}` – Get task by ID
- `POST /api/tasks` – Create a new task

### Example

```json
{
  "title": "Read Spring Docs",
  "description": "Complete the tutorial section",
  "dueDate": "2025-07-10",
  "status": "PENDING"
}
```
- `PUT /api/tasks/{id}` – Update a task
- `DELETE /api/tasks/{id}` – Delete a task

## 🔍 Swagger API Docs

Visit:

```bash
http://localhost:8080/swagger-ui.html
```

## 🧪 Run Tests

```bash
mvn test
```

## 📦 Package as JAR
```bash
mvn clean package
java -jar target/to-do-app-0.0.1-SNAPSHOT.jar
```

## 👤 Author

- lrdluffy – [GitHub](https://github.com/lrdluffy)

## 📄 License

This project is licensed under the [MIT License](./LICENSE).

## ✅ TODO / Future Improvements

- JWT-based user authentication
- Task filtering
- CI/CD with GitHub Actions
- Production-ready Docker image
- Deployment to Heroku / AWS
