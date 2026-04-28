GET http://localhost:8080/login
POST http://localhost:8080/api/v1/auth/register
{
  "username": "adminuser",
  "password": "12345678",
  "role": "ADMIN"
}
POST http://localhost:8080/login
username=adminuser
password=12345678
_csrf=YOUR_CSRF_TOKEN
JSESSIONID=abc123...
POST http://localhost:8080/api/v1/orders
POST http://localhost:8080/logout
POST http://localhost:8080/api/v1/orders
