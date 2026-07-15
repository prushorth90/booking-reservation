# ReserveNow

ReserveNow is a full-stack hotel booking and reservation platform built with React, TypeScript, Material UI, Java Spring Boot, PostgreSQL, JWT authentication, Docker Compose, GitHub Actions CI, and observability tooling.

The project demonstrates a production-style full-stack architecture with customer booking flows, admin management, role-based access control, user-owned bookings, metrics, logging, and containerized local development.

---

## Features

### Customer Features

- Register and log in with JWT authentication
- Search hotels by location, date range, and guest count
- View available rooms based on real booking-overlap logic
- Create a booking
- View only your own bookings
- Cancel your own bookings

### Admin Features

- Admin-only dashboard
- View all hotels
- Create hotels
- Create rooms for hotels
- View all customer bookings
- Cancel any booking as an admin

### Backend Features

- Spring Boot REST API
- PostgreSQL persistence with Spring Data JPA
- JWT authentication
- Role-based authorization with `CUSTOMER` and `ADMIN`
- Ownership-based authorization for bookings
- Validation and global error handling
- Real room availability calculation
- Actuator health and Prometheus metrics
- Custom business metrics
- Structured application logs
- Unit, controller, and security tests

### DevOps / Observability

- Docker Compose local setup
- GitHub Actions CI
- Prometheus metrics scraping
- Grafana dashboard
- Backend Docker image
- Frontend production build served through Nginx

---

## Tech Stack

### Frontend

- React
- TypeScript
- Vite
- Material UI
- React Router

### Backend

- Java 17
- Spring Boot
- Spring Web MVC
- Spring Security
- JWT
- Spring Data JPA
- PostgreSQL
- Maven
- Micrometer
- Spring Boot Actuator

### Infrastructure / Tooling

- Docker
- Docker Compose
- GitHub Actions
- Prometheus
- Grafana
- Nginx

---

## Architecture

```text
React + TypeScript frontend
        |
        | REST API + JWT
        v
Spring Boot backend
        |
        | JPA
        v
PostgreSQL 