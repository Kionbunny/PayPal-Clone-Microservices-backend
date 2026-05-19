PayPal Clone – Microservices Backend
# PayPal Clone – Microservices Backend

A distributed payment backend system built using Spring Boot Microservices, Apache Kafka, Spring Cloud API Gateway, Docker, and JWT authentication.

The project demonstrates event-driven architecture, asynchronous communication between services, centralized API routing, and containerized deployment workflows.

---

# 🚀 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Cloud API Gateway
- Apache Kafka
- JWT Authentication
- Spring Data JPA
- H2 Database
- Docker & Docker Compose
- Maven
- Postman

---

# 🏗️ Microservices Architecture

The system consists of the following services:

| Service | Port | Responsibility |
|---|---|---|
| API Gateway | 8080 | Centralized routing |
| User Service | 8082 | Authentication & user management |
| Transaction Service | 8081 | Handles payment transactions |
| Reward Service | 8084 | Processes cashback rewards |
| Notification Service | 8083 | Generates notifications |

---

# 🔥 Key Features

- Microservices-based architecture
- JWT-based authentication
- Centralized API routing using Spring Cloud Gateway
- Event-driven communication using Apache Kafka
- Asynchronous Reward & Notification processing
- RESTful APIs using Spring Boot
- Database persistence using Spring Data JPA
- Dockerized deployment using Docker Compose
- API testing using Postman

---

# ⚡ Event-Driven Workflow

Transaction processing follows an asynchronous architecture:

Transaction Service
        ↓
   Kafka Topic
        ↓
Reward Service Consumer
        ↓
Notification Service Consumer

- Transaction events are published to Kafka
- Reward service consumes transaction events and generates cashback rewards
- Notification service consumes transaction events and generates notifications

---

# 🔐 Authentication Flow

- User Signup
- User Login
- JWT Token Generation
- Token validation using Spring Security filters

---

# 🌐 API Gateway Routing

All requests are routed through:

http://localhost:8080

Example Routes:

| Endpoint | Routed To |
|---|---|
| /auth/** | user-service |
| /api/transactions/** | transaction-service |
| /api/rewards/** | reward-service |
| /api/notifications/** | notification-service |

---

# 🐳 Docker Setup

## Build Containers

```bash
docker-compose build
Start All Services
docker-compose up
Stop Containers
docker-compose down
▶️ Running Services Locally

Run services in the following order:

Zookeeper
Kafka
User Service
Transaction Service
Reward Service
Notification Service
API Gateway
🧪 API Testing

APIs were tested using Postman.

Example:

Signup

POST
http://localhost:8080/auth/signup

{
  "name": "Ajay",
  "email": "ajay@gmail.com",
  "password": "password123"
}
Create Transaction

POST
http://localhost:8080/api/transactions/create

{
  "senderId": 1,
  "receiverId": 2,
  "amount": 5000
}
Fetch Rewards

GET
http://localhost:8080/api/rewards/all-rewards

📦 Future Enhancements
Rate Limiting using API Gateway
Prometheus & Grafana Monitoring
Redis Caching
Kubernetes Deployment
Circuit Breaker using Resilience4j
Service Discovery using Eureka
📚 Learning Outcomes

This project helped in understanding:

Microservices Architecture
Event-Driven Systems
Apache Kafka Messaging
API Gateway Routing
JWT Authentication
Dockerized Deployments
Distributed Backend Design
Asynchronous Communication Patterns
👨‍💻 Author

Ajay Kumar
