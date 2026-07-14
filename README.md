# ReserveNow

A full-stack booking and reservation app built with React, TypeScript, Material UI, Java Spring Boot, PostgreSQL, JWT authentication, and Docker.

## Features

- Search hotels by location, dates, and guest count
- View available rooms based on booking overlap logic
- Register and login with JWT authentication
- Create bookings as a customer
- View only your own bookings
- Cancel your own bookings
- Admin dashboard for managing hotels and rooms
- Admin can view and cancel all bookings
- Role-based access control with CUSTOMER and ADMIN roles
- PostgreSQL persistence
- Docker Compose local setup
- GitHub Actions CI for backend tests and frontend build

## Tech Stack

### Frontend
- React
- TypeScript
- Material UI
- Vite
- React Router

### Backend
- Java 17
- Spring Boot
- Spring Web MVC
- Spring Security
- JWT
- Spring Data JPA
- PostgreSQL

### DevOps
- Docker
- Docker Compose
- GitHub Actions

## Local Development

### Run with Docker Compose

```bash
docker compose up --build