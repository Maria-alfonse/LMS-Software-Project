# Learning Management System (LMS)
## Overview
This is a web-based Learning Management System (LMS) built with **Java** and **Spring Boot**. It is designed to facilitate online course management, including course creation, student enrollment, content delivery, assessments, attendance tracking, and performance monitoring. The application supports three main user roles: Admin, Instructor, and Student.

## Features
### 1. User Management
- **Role-Based Access**: Admin, Instructor, Student
- **Authentication & Authorization**: User login and registration via Spring Security
- **Profile Management**: View and update personal information
### 2. Course Management
- Instructors can create and manage courses
- Upload course materials (PDFs, videos, etc.)
- Students can browse and enroll in courses
- Attendance managed via OTP per lesson
### 3. Assessment & Grading
- **Quiz Types**: MCQ, True/False, Short Answer
- **Question Bank**: Instructors can create and store questions per course
- Randomized quiz generation for each student
- Assignment upload and manual grading
- Automated feedback for quizzes, manual feedback for assignments
### 4. Performance Tracking
- Instructors can monitor student progress
- Track quiz results, assignments, and attendance records
### 5. Notifications
- In-app notifications for enrollment, grading, and course updates
- Unread/all notification views
- Email notifications for important updates
### 6. Bonus Features
- Spring Security-based Role Access Control
- Generate performance analytics and Excel reports
- Visual dashboards with progress and grading charts

## Tech Stack
### Backend
- Java 17+
- Spring Boot
- Spring Security
- RESTful APIs
### Database
- PostgreSQL
### Testing & DevOps
- JUnit for unit testing
- Git & GitHub for version control and collaboration

## Getting Started
### Prerequisites
- Java 17+
- Maven or Gradle
- MySQL or compatible DB

### Steps
1. Clone the repository:
  ```bash
  git clone https://github.com/Maria-alfonse/LMS-Software-Project.git
  cd LMS-Software-Project
  ```
2. Configure the database:
  - Create a database (e.g., lms_db)
  - Set your DB credentials in application.properties
3. Build and Run:
  ```bash
  ./mvnw spring-boot:run
  ```
4. Access the app in your browser:
   HTTP://localhost:8080
