# Spring Boot E-Commerce Backend

## Overview
This project is a backend system for an e-commerce platform, built using Spring Boot. It provides APIs for managing customers, products, orders, payments.

## Features
- Product catalog with multiple categories
- Shopping cart and order management
- RESTful API with Swagger documentation
- Auto send new password with user's email
- Database integration with MySQL
- Exception handling and validation

## Tech Stack
- **Framework:** Spring Boot
- **Database:** MySQL (Customer,Product, User)
- **API Documentation:** Swagger / OpenAPI
- **Build Tool:** Maven 

## Getting Started
### Prerequisites
Ensure you have the following installed:
- Java 21
- Maven
- MySQL

### Installation
1. Clone the repository:
   git clone https://github.com/Namhehe237/EcommercialWebBackEnd.git
2. Navigate to the project directory:
   cd EcommercialWebBackEnd

3. Configure the database connections in `application.properties` or `application.yml`:
   properties
   # MySQL Configuration
   Sql Script is save in Script folder(please run the database script first)

   spring.datasource.url=jdbc:mysql://localhost:3306/ecommercial?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&serverTimezone=UTC
   spring.datasource.username=your 
   spring.datasource.password=yourpassword

   # Spring mail Configuration
   spring.mail.host=smtp.gmail.com
   spring.mail.port=587
   spring.mail.username=your-email
   spring.mail.password=your-email-password
   spring.mail.protocol=smtp
   spring.mail.properties.mail.smtp.auth=true
   spring.mail.properties.mail.smtp.starttls.enable=true
   spring.mail.properties.mail.debug=true

4. Build the project:
   mvn clean install
5. Run the application:
   mvn spring-boot:run
   

## Contributing
1. Fork the repository
2. Create a new branch (`feature/your-feature`)
3. Commit your changes
4. Push to the branch and create a Pull Request


Happy Coding! 🚀

