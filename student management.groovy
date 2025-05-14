import java.io.*;
import java.util.*;

class Student {
    int rollNo;
    String name;
    String grade;

    public Student(int rollNo, String name, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return rollNo + "," + name + "," + grade;
    }

    public static Student fromString(String line) {
        String[] parts = line.split(",");
        return new Student(Integer.parseInt(parts[0]), parts[1], parts[2]);
    }
}

public class StudentManagementSystem {

    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Student Record Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by Roll No");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent(scanner);
                case 2 -> viewStudents();
                case 3 -> searchStudent(scanner);
                case 4 -> deleteStudent(scanner);
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);
    }

    static void addStudent(Scanner scanner) {
        try {
            System.out.print("Enter Roll No: ");
            int rollNo = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Grade: ");
            String grade = scanner.nextLine();

            Student student = new Student(rollNo, name, grade);

            FileWriter fw = new FileWriter(FILE_NAME, true);
            fw.write(student.toString() + "\n");
            fw.close();

            System.out.println("Student added successfully!");
        } catch (IOException e) {
            System.out.println("Error while adding student: " + e.getMessage());
        }
    }

    static void viewStudents() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("No student records found.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            System.out.println("\nRoll No | Name | Grade");
            System.out.println("---------------------------");

            while ((line = br.readLine()) != null) {
                Student student = Student.fromString(line);
                System.out.println(student.rollNo + " | " + student.name + " | " + student.grade);
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error while reading students: " + e.getMessage());
        }
    }

    static void searchStudent(Scanner scanner) {
        System.out.print("Enter Roll No to search: ");
        int rollNo = scanner.nextInt();
        boolean found = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while ((line = br.readLine()) != null) {
                Student student = Student.fromString(line);
                if (student.rollNo == rollNo) {
                    System.out.println("Found: " + student.name + " - Grade: " + student.grade);
                    found = true;
                    break;
                }
            }

            br.close();

            if (!found) {
                System.out.println("Student not found.");
            }

        } catch (IOException e) {
            System.out.println("Error while searching: " + e.getMessage());
        }
    }

    static void deleteStudent(Scanner scanner) {
        System.out.print("Enter Roll No to delete: ");
        int rollNo = scanner.nextInt();
        List<Student> students = new ArrayList<>();
        boolean found = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while ((line = br.readLine()) != null) {
                Student student = Student.fromString(line);
                if (student.rollNo != rollNo) {
                    students.add(student);
                } else {
                    found = true;
                }
            }

            br.close();

            FileWriter fw = new FileWriter(FILE_NAME);
            for (Student s : students) {
                fw.write(s.toString() + "\n");
            }
            fw.close();

            if (found) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }

        } catch (IOException e) {
            System.out.println("Error while deleting: " + e.getMessage());
        }
    }
}
