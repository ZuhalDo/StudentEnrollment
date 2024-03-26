**Student Enrollment System**

**Description**

The Student Enrollment System is a web application designed to manage courses, students, and instructors. It allows administrators to add, edit, and delete courses, students, and instructors. Students can enroll in courses, and instructors can view and manage the courses they are teaching.

**Prerequisites**

Before running the application, ensure you have the following installed:

Java Development Kit (JDK) version 8 or higher
Maven
MySQL database server

**Database Configuration**

Before running the application for the first time, follow these steps to set up the database:

1.Create a MySQL database named studentdb.

2.Update the application.properties file located in the src/main/resources directory with your database connection details, including the username and password.

spring.datasource.url=jdbc:mysql://localhost:3306/studentdb
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update

**Running the Application**

To run the application:

1.Open a terminal window and navigate to the project directory.

2.Run the following Maven command to build the project:

mvn clean package

Once the build is successful, run the following command to start the application:

java -jar target/student-enrollment-system.jar

Open a web browser and navigate to http://localhost:8080/login to access the application.

**Usage**

As an administrator, you can log in to the system using your credentials and manage courses, students, and instructors.

As a student, you can log in to enroll in courses and view your enrolled courses.

As an instructor, you can log in to view and manage the courses you are teaching.

**Technologies Used**

Java

Spring Boot

Spring Security

Spring Data JPA

Thymeleaf

MySQL

SLF4J (Simple Logging Facade for Java)

**Contributor**

Zuhal 
