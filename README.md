# base project Service

A Spring Boot REST API service for managing products, accounts, and e-commerce operations with JWT authentication.

## Features

- **Authentication & Authorization**: JWT-based security with role-based access control
- **User Management**: Account registration, login, and profile management
- **Product Management**: CRUD operations for products and categories
- **Address Management**: User address management with main address selection
- **Sales Management**: Order and selling operations
- **API Documentation**: Swagger/OpenAPI integration
- **Database**: PostgreSQL with JPA/Hibernate

## Tech Stack

- **Java 21**
- **Spring Boot 3.2.2**
- **Spring Security** (JWT Authentication)
- **Spring Data JPA**
- **PostgreSQL**
- **Redis** (Caching)
- **Gradle** (Build tool)
- **Swagger/OpenAPI** (API Documentation)
- **Lombok** (Code generation)

## Prerequisites

- Java 21 or higher
- PostgreSQL database
- Redis server (optional, for caching)
- Gradle (or use included wrapper)

## Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd base project
   ```

2. **Database Setup**
   - Create PostgreSQL database named `base project-db`
   - Update database credentials in `application.yaml` if needed

3. **Configuration**
   - Update `src/main/resources/application.yaml` with your database and Redis settings
   - Modify JWT secret key for production use

4. **Build and Run**
   ```bash
   ./gradlew bootRun
   ```

## API Documentation

Once the application is running, access the API documentation at:
- Swagger UI: http://localhost:18081/swagger-ui.html
- API Docs: http://localhost:18081/api-doc

## API Endpoints

### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/authenticate` - User login
- `POST /api/auth/refresh-token` - Refresh JWT token

### User Management
- `GET /api/user/profile` - Get user profile
- `PUT /api/user/profile` - Update user profile
- `POST /api/user/change-password` - Change password

### Address Management
- `GET /api/user/addresses` - Get user addresses
- `POST /api/user/addresses` - Add new address
- `PUT /api/user/addresses/{id}/main` - Set main address

## Project Structure

```
src/main/java/com/poliymorf/base project/
├── config/          # Security and application configuration
├── controller/      # REST controllers (admin, user, guest)
├── data/           # DTOs, entities, enums, and constants
├── repository/     # Data access layer
├── service/        # Business logic layer
└── util/           # Utilities and exception handling
```

## Configuration

Key configuration properties in `application.yaml`:

```yaml
server:
  port: 18081

application:
  security:
    jwt:
      secret-key: your-secret-key
      expiration: 86400000  # 1 day
      refresh-token:
        expiration: 604800000  # 7 days

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/base project-db
    username: your-username
    password: your-password
```

## Development

### Running Tests
```bash
./gradlew test
```

### Building for Production
```bash
./gradlew build
```

### Code Style
- Uses Lombok for reducing boilerplate code
- Follows Spring Boot best practices
- Implements proper exception handling

## Security

- JWT-based authentication
- Role-based authorization (USER, ADMIN)
- Password encryption
- CORS configuration
- Request/Response logging and auditing

## License

[Add your license information here]
