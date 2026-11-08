# Task Manager API

A RESTful API for task management built with Spring Boot and PostgreSQL.

## Tech Stack

- **Java 25**
- **Spring Boot 3.4.4**
- **Spring Data JPA / Hibernate**
- **PostgreSQL**
- **Flyway** (database migrations)
- **Docker / Docker Compose**
- **Maven**

## Features

- Create, read, update and delete tasks (CRUD)
- Task status management: `TODO` → `IN_PROGRESS` → `DONE`
- Input validation with meaningful error messages
- Global exception handling
- Layered architecture: Controller → Service → Repository

## Project Structure

```
src/main/java/com/taskmanager/
├── controller/        # REST endpoints
├── dto/               # Request and response objects
├── entity/            # JPA entities
├── exception/         # Custom exceptions and global handler
├── mapper/            # DTO ↔ Entity conversion
├── repository/        # Spring Data JPA repositories
└── service/           # Business logic
```

## Getting Started

### Option 1 — Docker (recommended)

The only requirement is [Docker](https://docs.docker.com/get-docker/). One command starts PostgreSQL and the application:

```bash
docker compose up --build
```

The API will be available at `http://localhost:8080`. Stop everything with `docker compose down` (add `-v` to also delete the database volume).

To change the database credentials, copy `.env.example` to `.env` and edit it.

### Option 2 — Run locally

Prerequisites: Java 25+, Maven 3.9+, PostgreSQL. Start only the database in Docker and the app from your IDE or Maven:

```bash
docker compose up -d db
mvn spring-boot:run
```

### Configuration

All settings come from environment variables, with defaults for local development:

| Variable | Default | Description |
|----------|---------|-------------|
| `DB_URL` | `jdbc:postgresql://localhost:5432/taskmanager` | JDBC URL |
| `DB_USERNAME` | `postgres` | Database user |
| `DB_PASSWORD` | `postgres` | Database password |
| `PORT` | `8080` | HTTP port |
| `SHOW_SQL` | `false` | Log SQL statements |

### Database migrations

The schema is managed by [Flyway](https://flywaydb.org/). Migrations live in `src/main/resources/db/migration` and run automatically on startup; Hibernate only validates that entities match the schema (`ddl-auto=validate`).

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/tasks` | Create a new task |
| `GET` | `/api/v1/tasks` | Get all tasks |
| `GET` | `/api/v1/tasks/{id}` | Get task by ID |
| `PUT` | `/api/v1/tasks/{id}` | Update a task |
| `DELETE` | `/api/v1/tasks/{id}` | Delete a task |

## Usage Examples

### Create a Task

```bash
curl -X POST http://localhost:8080/api/v1/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Buy groceries","description":"Milk, eggs, bread","status":"TODO"}'
```

**Response `201 Created`:**
```json
{
  "id": 1,
  "title": "Buy groceries",
  "description": "Milk, eggs, bread",
  "status": "TODO",
  "createdAt": "2026-04-13T12:00:00",
  "updatedAt": "2026-04-13T12:00:00"
}
```

### Get All Tasks

```bash
curl http://localhost:8080/api/v1/tasks
```

### Update a Task

```bash
curl -X PUT http://localhost:8080/api/v1/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Buy groceries","description":"Milk, eggs, bread","status":"DONE"}'
```

### Delete a Task

```bash
curl -X DELETE http://localhost:8080/api/v1/tasks/1
```

## Error Handling

The API returns consistent error responses:

```json
{
  "message": "Task not found with id: 99"
}
```

| Status Code | Description |
|-------------|-------------|
| `200 OK` | Success |
| `201 Created` | Resource created |
| `204 No Content` | Resource deleted |
| `400 Bad Request` | Validation error |
| `404 Not Found` | Resource not found |

## Task Model

| Field | Type | Constraints |
|-------|------|-------------|
| `id` | Long | Auto-generated |
| `title` | String | Required, max 100 chars |
| `description` | String | Optional, max 1000 chars |
| `status` | Enum | `TODO`, `IN_PROGRESS`, `DONE` |
| `createdAt` | LocalDateTime | Auto-set on create |
| `updatedAt` | LocalDateTime | Auto-set on update |
