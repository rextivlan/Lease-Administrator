
# LeaseAdmin Full-Stack Application

LeaseAdmin is a full-stack application for managing lease contracts. The backend is built with Spring Boot, secured with Spring Security (using JWT), and uses PostgreSQL for data persistence. The frontend is built using React with Vite for a fast development experience. The application supports user registration, login, and full CRUD operations for lease contracts.

---

## Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Backend Setup](#backend-setup)
  - [PostgreSQL Configuration](#postgresql-configuration)
  - [Running the Backend](#running-the-backend)
  - [Data Seeding](#data-seeding)
- [Frontend Setup](#frontend-setup)
  - [Creating the Frontend using Vite](#creating-the-frontend-using-vite)
  - [Running the Frontend](#running-the-frontend)
- [Troubleshooting](#troubleshooting)

---

## Overview

- **Backend:**  
  - **Framework:** Spring Boot  
  - **Security:** Spring Security with JWT  
  - **Database:** PostgreSQL  
  - **Features:** Authentication (register/login), CRUD operations for leases, data seeding (runs only once)

- **Frontend:**  
  - **Framework:** React (using Vite)  
  - **Features:** User registration, login, and lease management (create, read, update, delete leases)  
  - **Routing:** React Router  
  - **Styling:** Custom CSS for a clean UI

---

## Prerequisites

- **Java 17+**
- **Maven**
- **PostgreSQL**
- **Node.js (14+) and npm**

---

## Backend Setup

### PostgreSQL Configuration

1. **Install and run PostgreSQL.**
2. **Create a new database** (e.g., `db_name`).
3. **Update `application.properties`:**  
   In `backend/src/main/resources/application.properties`, update the following properties:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/db_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

   jwt.secret=your_jwt_secret
   jwt.expirationMs=time_in_milli_seconds

### Running-The-Backend
1. Navigate to the backend directory:

   ```bash
   cd backend


2. Run the application using Maven:

   ```bash
   mvn spring-boot:run
   
3. Alternatively, build the jar file and run:
   
    ```bash
    mvn clean package
    java -jar target/LeaseAdmin-0.0.1-SNAPSHOT.jar

4. The backend will be available at [http://localhost:8080](http://localhost:8080).

### Data-Seeding
The backend includes a data seeder that checks if any leases exist before seeding data. Place your seed data file (seeder.json) in src/main/resources/. The seeder will run only once if the lease count is zero.

### Frontend-Setup
#### creating-the-frontend-using-vite
1. Ensure you are at the repository root (which contains your backend folder).
2. Create the React app in the frontend folder using Vite:
   
```bash
 npm create vite@latest frontend -- --template react
```
3. Navigate to the frontend directory:
```bash
   cd frontend
```
4. Install dependencies:
```bash
   npm install
```

### Running-The-Frontend
1. From the frontend folder, run:
```bash
 npm run dev
```
2. The frontend will be available at [http://localhost:5173](http://localhost:5173)

### Troubleshooting
CORS Configuration
The backend is configured to allow cross-origin requests from the frontend URL (http://localhost:5173).

