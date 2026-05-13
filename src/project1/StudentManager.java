package project1;

import java.sql.*;
import java.util.Scanner;

class DuplicateStudentException extends Exception {
    public DuplicateStudentException(String message) {
        super(message);
    }
}

class Student {
    int id;
    String name;
    String branch;
    double cgpa;

    public Student(int id, String name, String branch, double cgpa) {
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.cgpa = cgpa;
    }

    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Branch: " + branch);
        System.out.println("CGPA: " + cgpa);
        System.out.println("-------------------");
    }
}

public class StudentManager {

    static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root";
    static final String PASSWORD = "Admin1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Add student
    public void addStudent(int id, String name, String branch, double cgpa)
            throws DuplicateStudentException {
        String checkQuery = "SELECT id FROM students WHERE id = ?";
        String insertQuery = "INSERT INTO students VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement checkPs = conn.prepareStatement(checkQuery)) {
            checkPs.setInt(1, id);
            ResultSet rs = checkPs.executeQuery();
            if (rs.next()) {
                throw new DuplicateStudentException(
                    "Student with ID " + id + " already exists!");
            }
            try (PreparedStatement insertPs = conn.prepareStatement(insertQuery)) {
                insertPs.setInt(1, id);
                insertPs.setString(2, name);
                insertPs.setString(3, branch);
                insertPs.setDouble(4, cgpa);
                insertPs.executeUpdate();
                System.out.println("Student added: " + name);
            }
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    // Display all students
    public void displayAll() {
        String query = "SELECT * FROM students";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            System.out.println("===== All Students =====");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Branch: " + rs.getString("branch"));
                System.out.println("CGPA: " + rs.getDouble("cgpa"));
                System.out.println("-------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error displaying students: " + e.getMessage());
        }
    }

    // Search student by ID
    public void searchStudent(int id) {
        String query = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Student found:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Branch: " + rs.getString("branch"));
                System.out.println("CGPA: " + rs.getDouble("cgpa"));
                System.out.println("-------------------");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error searching student: " + e.getMessage());
        }
    }

    // Update student
    public void updateStudent(int id, String newName, String newBranch, double newCgpa) {
        String query = "UPDATE students SET name=?, branch=?, cgpa=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, newName);
            ps.setString(2, newBranch);
            ps.setDouble(3, newCgpa);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    // Delete student
    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== STUDENT RECORD MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Branch: ");
                    String branch = scanner.next();
                    System.out.print("Enter CGPA: ");
                    double cgpa = scanner.nextDouble();
                    try {
                        manager.addStudent(id, name, branch, cgpa);
                    } catch (DuplicateStudentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    manager.displayAll();
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    int searchId = scanner.nextInt();
                    manager.searchStudent(searchId);
                    break;

                case 4:
                    System.out.print("Enter ID to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new Name: ");
                    String newName = scanner.next();
                    System.out.print("Enter new Branch: ");
                    String newBranch = scanner.next();
                    System.out.print("Enter new CGPA: ");
                    double newCgpa = scanner.nextDouble();
                    manager.updateStudent(updateId, newName, newBranch, newCgpa);
                    break;

                case 5:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = scanner.nextInt();
                    manager.deleteStudent(deleteId);
                    break;

                case 6:
                    System.out.println("Thank you for using Student Record Management System!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}