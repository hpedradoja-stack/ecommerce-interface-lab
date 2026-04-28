
# 🛒 E-Commerce API System (Spring Boot)

---

# 🔐 Security Architecture (Session-Based Authentication)

This system uses **Session-Based Authentication** with Spring Security.

### How it works:
- User logs in via `/login`
- Server validates credentials
- If valid, a **server-side session** is created
- A `JSESSIONID` cookie is sent to the browser
- Browser automatically stores and sends the cookie for future requests
- Server checks session for every protected endpoint

✔ No JWT used  
✔ Authentication stored in server session  
✔ Cookie acts as session identifier  

---

# 🧾 Validation Rules

Bean Validation is applied to DTOs:

### User Registration
- username → @NotBlank, @Size(min=8, max=20)
- password → @NotBlank, @Size(min=8, max=20)
- role → @NotBlank

### Product Creation
- name → @NotBlank
- price → @Positive
- stock → @Positive
- categoryId → @NotNull

✔ Validation occurs before service layer execution  

---

# 🌐 API Reference

## 🔓 Public Endpoints
- POST /api/v1/auth/register → Register user
- POST /login → Login user

## 🔒 Protected Endpoints
- POST /api/v1/orders → AUTHENTICATED USERS
- DELETE /api/v1/products/{id} → ADMIN ONLY
- GET /api/v1/products → AUTHENTICATED USERS

---

# 🧠 Code Quality Notes

✔ Security configuration is fully commented  
✔ DTOs are used for clean data transfer  
✔ Validation error messages are user-friendly  
✔ Global exception handler returns structured JSON responses  

---

# 🧪 Testing Evidence

## ✔ User Registration & Login
- User successfully registers
- User logs in successfully
- `JSESSIONID` cookie is generated

---

## ✔ Unauthorized Access
Request without session:
- POST /api/v1/orders
Result: 401 Unauthorized

---

## ✔ Authorized Access
Request with session cookie:
- POST /api/v1/orders
Result: Success (200 OK)

---

## ✔ Validation Error Example

Request:
```json
{
  "price": -100,
  "name": ""
}
{
  "status": 400,
  "errors": [
    "Field 'price' must be positive",
    "Field 'name' must not be blank"
  ]
}
