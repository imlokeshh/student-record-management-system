# 🎓 Student Record Management System

A console-based student management application built with Core Java, JDBC, and MySQL.
Supports real-time database operations with full CRUD functionality and custom exception handling.

## 🛠️ Tech Stack
- **Language:** Java
- **Database:** MySQL
- **Connectivity:** JDBC (Java Database Connectivity)
- **Concepts:** OOP, Exception Handling, Collections Framework, PreparedStatement

## ✨ Features
- ✅ Add new student records
- ✅ Display all students from database
- ✅ Search student by ID
- ✅ Update student details
- ✅ Delete student records
- ✅ Interactive menu-driven interface
- ✅ Custom exception: `DuplicateStudentException`
- ✅ Data persists in MySQL database across sessions

## 🗄️ Database Setup
```sql
CREATE DATABASE studentdb;
USE studentdb;

CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    branch VARCHAR(100),
    cgpa DOUBLE
);
```

## ⚙️ How to Run
1. Clone this repository
```
git clone https://github.com/imlokeshh/student-record-management-system.git
```
2. Open in Eclipse or VS Code
3. Add `mysql-connector-j-9.7.0.jar` to your build path
4. Update database credentials in `StudentManager.java`:
```java
static final String URL = "jdbc:mysql://localhost:3306/studentdb";
static final String USER = "root";
static final String PASSWORD = "your_password";
```
5. Run `StudentManager.java`

## 📸 Sample Output
```
===== STUDENT RECORD MANAGEMENT SYSTEM =====
1. Add Student
2. Display All Students
3. Search Student by ID
4. Update Student
5. Delete Student
6. Exit
Enter your choice: 1
Enter ID: 101
Enter Name: Lokesh
Enter Branch: CSE
Enter CGPA: 8.5
Student added: Lokesh

Error: Student with ID 101 already exists!
```

## 👨‍💻 Author
**Lokesh Kurumula**
- GitHub: [@imlokeshh](https://github.com/imlokeshh)
- LinkedIn: [lokesh-kurumula](https://www.linkedin.com/in/lokesh-kurumula)
