# 🗄️ Learn Spring Data JPA: Database Integration with Spring Boot

A REST API built to demonstrate database integration using **Spring Data JPA** with **Spring Boot**. This project covers entity mapping, repository patterns, pagination, sorting, and filtering — core skills for building robust backend applications.

---

## 🚀 Technologies

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **MySQL** (or any relational database)
- **Maven**

---

## 📐 Architecture Overview

```
controller/       → REST endpoints (UserController)
service/          → Business logic (UserService)
repository/       → Data access layer (UserRepository)
entity/           → JPA entity (UserEntity)
dto/              → Request/response objects
```

---

## ✨ Features

- **CRUD operations** — Create, read, update, and delete users
- **Pagination & sorting** — Page-based listing with configurable order (ASC/DESC)
- **Dynamic filtering** — Filter by `name`, `age`, or both simultaneously
- **Native queries** — Custom SQL with `@Query(nativeQuery = true)` for fine-grained control
- **DTO pattern** — Clean separation between API contracts and internal entities

---

## 📡 Endpoints

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/users` | Create a new user |
| `GET` | `/users` | List users with pagination and optional filters |
| `GET` | `/users/{id}` | Find user by ID |
| `PUT` | `/users/{id}` | Update user by ID |
| `DELETE` | `/users/{id}` | Delete user by ID |

### Query Parameters — `GET /users`

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `page` | Integer | `0` | Page number |
| `pageSize` | Integer | `10` | Items per page |
| `orderBy` | String | `desc` | Sort direction: `asc` or `desc` |
| `name` | String | — | Filter by exact name |
| `age` | Long | — | Filter by minimum age |

---

## 💡 Key Concepts Covered

- Mapping entities with `@Entity`, `@Table`, `@Column`, and `@GeneratedValue`
- Extending `JpaRepository` for out-of-the-box CRUD
- Writing native SQL queries with `@Query(nativeQuery = true)` including `countQuery` for pagination support
- Using `PageRequest` and `Sort` for paginated responses
- Understanding the difference between **field names** (Java) and **column names** (SQL) when using `Sort` with native queries
- Returning structured API responses with custom `ApiResponse` and `PaginationResponse` wrappers

---

## ⚙️ Running Locally

**1. Clone the repository**
```bash
git clone https://github.com/your-username/springboot-learn-spring-data-jpa-database-integration.git
cd springboot-learn-spring-data-jpa-database-integration
```

**2. Configure the database**

Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

**3. Run the application**
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

## 📬 Request Examples

**Create a user**
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name": "John Doe", "age": 28}'
```

**List users with filters**
```bash
curl "http://localhost:8080/users?name=John&age=25&page=0&pageSize=5&orderBy=asc"
```

---

## 📄 License

This project is intended for learning and portfolio purposes.