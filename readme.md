# 🛒 Ecommerce API - Spring Boot Project

## Project Overview
This project is a RESTful API for an e-commerce product management system built using Spring Boot. It demonstrates HTTP fundamentals, REST principles, and CRUD operations using in-memory storage (List<Product>) instead of a database.

The system allows users to create, retrieve, update, delete, and filter products by category, name, and price.

---

## ⚙️ Setup Instructions

Requirements:
- Java 17+
- Spring Boot
- Maven or Gradle
- Postman (for testing)

Run the application:

./mvnw spring-boot:run

or

./gradlew bootRun

Base URL:

http://localhost:8080/api/v1/products

---

## 📡 API ENDPOINTS

GET /api/v1/products  
Description: Retrieve all products  
Response: 200 OK  

GET /api/v1/products/{id}  
Description: Retrieve product by ID  
Response: 200 OK / 404 Not Found  

POST /api/v1/products  
Description: Create a new product  
Response: 201 Created  

Sample Request:
{
  "name": "Laptop",
  "description": "Gaming laptop",
  "price": 55000,
  "category": "Electronics",
  "stockQuantity": 10,
  "imageUrl": ""
}

PUT /api/v1/products/{id}  
Description: Update entire product  
Response: 200 OK / 404 Not Found  

PATCH /api/v1/products/{id}  
Description: Partially update product  
Response: 200 OK / 404 Not Found  

DELETE /api/v1/products/{id}  
Description: Delete product  
Response: 204 No Content / 404 Not Found  

GET /api/v1/products/filter?filterType={type}&filterValue={value}  
Description: Filter products by category, name, or price range  

Example:
/api/v1/products/filter?filterType=category&filterValue=Electronics

---

## ⚠️ KNOWN LIMITATIONS

- Uses in-memory storage (List<Product>)
- Data resets when application stops
- No database integration
- No authentication/security system
- Basic error handling only

---

## 🚀 PROJECT SUMMARY

This project demonstrates:
- REST API development using Spring Boot
- HTTP methods (GET, POST, PUT, PATCH, DELETE)
- Proper HTTP status codes
- Input validation using annotations
- MVC architecture (Controller, Service, Model)
- In-memory data handling
