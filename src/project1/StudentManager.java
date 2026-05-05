package project1;
import java.util.ArrayList;

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

    ArrayList<Student> studentList = new ArrayList<>();

    public void addStudent(int id, String name, String branch, double cgpa) {
        Student s = new Student(id, name, branch, cgpa);
        studentList.add(s);
        System.out.println("Student added: " + name);
    }

    public void displayAll() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("===== All Students =====");
        for (Student s : studentList) {
            s.displayInfo();
        }
    }

    public void deleteStudent(int id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).id == id) {
                System.out.println("Deleted: " + studentList.get(i).name);
                studentList.remove(i);
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
        
    }
    public void searchStudent(int id) {
    for (Student s : studentList) {
        if (s.id == id) {
            System.out.println("Student found:");
            s.displayInfo();
            return;
        }
    }
    System.out.println("Student with ID " + id + " not found.");
}

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        manager.addStudent(1, "Lokesh", "CSE", 8.5);
        manager.addStudent(2, "Rahul", "ECE", 7.9);
        manager.addStudent(3, "Priya", "IT", 9.1);
        manager.searchStudent(3);
        manager.searchStudent(99);
        manager.displayAll();
        manager.deleteStudent(2);
        manager.displayAll();
    }
}