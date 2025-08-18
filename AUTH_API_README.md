# Authentication API Documentation

This document describes the authentication endpoints available in the Spring Boot application.

## Endpoints

### 1. Login
**POST** `/api/auth/login`

Authenticates a user and returns a JWT token.

**Request Body:**
```json
{
  "username": "your_username",
  "password": "your_password"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "userId": "user123",
  "username": "your_username",
  "email": "user@example.com",
  "userType": "USER"
}
```

**Status Codes:**
- `200 OK`: Login successful
- `400 Bad Request`: Invalid credentials or other error

### 2. Token Validation
**POST** `/api/auth/validate`

Validates a JWT token (placeholder endpoint for future implementation).

**Headers:**
```
Authorization: Bearer <your_jwt_token>
```

**Response:**
```json
true
```

## Usage Examples

### Using cURL

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'
```

**Using the token in subsequent requests:**
```bash
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer <your_jwt_token>"
```

### Using JavaScript/Fetch

**Login:**
```javascript
const loginData = {
  username: 'testuser',
  password: 'password123'
};

fetch('/api/auth/login', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(loginData)
})
.then(response => response.json())
.then(data => {
  console.log('Token:', data.token);
  // Store token in localStorage or sessionStorage
  localStorage.setItem('jwt_token', data.token);
});
```

**Using the token:**
```javascript
const token = localStorage.getItem('jwt_token');

fetch('/api/users', {
  headers: {
    'Authorization': `Bearer ${token}`
  }
})
.then(response => response.json())
.then(data => console.log(data));
```

## Security Notes

- The JWT token expires after 10 hours
- Store tokens securely (localStorage, sessionStorage, or HTTP-only cookies)
- Always use HTTPS in production
- The secret key should be changed in production and stored in environment variables

## Error Handling

The API returns appropriate HTTP status codes:
- `400 Bad Request`: Invalid request data or authentication failure
- `401 Unauthorized`: Missing or invalid token
- `500 Internal Server Error`: Server-side errors

## Dependencies

Make sure you have the following dependencies in your `build.gradle`:
- `spring-boot-starter-security`
- `spring-boot-starter-web`
- `jjwt` (for JWT handling)
- `lombok` 