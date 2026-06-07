# Restaurant-Microservices

A microservices-based restaurant management system built with Spring Boot.

## Architecture

The project is composed of three microservices:

| Service | Port | Database | Description |
|---------|------|----------|-------------|
| **auth-service** | 8081 | PostgreSQL (auth_db) | Authentication & user management |
| **menu-service** | 8082 | PostgreSQL (menu_db) | Menu & item management |
| **order-service** | 8083 | PostgreSQL (order_db) | Order processing |

## Prerequisites

- Java 21+
- Docker & Docker Compose

## Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/Restaurant-Microservices.git
   cd Restaurant-Microservices
   ```

2. **Set up environment variables**
   ```bash
   cp env-config.example .env
   # Edit .env with your passwords
   ```

3. **Start the services**
   ```bash
   docker-compose up -d
   ```

4. **Build and run locally** (optional)
   ```bash
   ./gradlew clean build
   ```

## Environment Variables

The application uses environment variables for sensitive configuration.
See `env-config.example` for a complete list of available variables.

## Security

- Passwords and secrets are NOT hardcoded in source files
- Use the `.env` file (gitignored) to set local credentials
- Never commit `.env` files to version control
