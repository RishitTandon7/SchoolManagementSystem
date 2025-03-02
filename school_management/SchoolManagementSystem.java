import java.sql.*;
import java.util.Scanner;

public class SchoolManagementSystem {
    static final String DB_URL = "jdbc:mysql://localhost:3306/school_db";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("1. Add Student\n2. View Students\n3. Add Teacher\n4. View Teachers\n5. Add Course\n6. View Courses\n7. Enroll Student\n8. View Enrollments\n9. Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: addStudent(conn, sc); break;
                    case 2: viewStudents(conn); break;
                    case 3: addTeacher(conn, sc); break;
                    case 4: viewTeachers(conn); break;
                    case 5: addCourse(conn, sc); break;
                    case 6: viewCourses(conn); break;
                    case 7: enrollStudent(conn, sc); break;
                    case 8: viewEnrollments(conn); break;
                    case 9: return;
                    default: System.out.println("Invalid choice!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Student Name: ");
        String name = sc.next();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
            System.out.println("Student added successfully!");
        }
    }

    static void viewStudents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM students";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Age: " + rs.getInt("age"));
            }
        }
    }

    static void addTeacher(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Teacher Name: ");
        String name = sc.next();
        System.out.print("Enter Subject: ");
        String subject = sc.next();
        String sql = "INSERT INTO teachers (name, subject) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, subject);
            pstmt.executeUpdate();
            System.out.println("Teacher added successfully!");
        }
    }

    static void viewTeachers(Connection conn) throws SQLException {
        String sql = "SELECT * FROM teachers";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Subject: " + rs.getString("subject"));
            }
        }
    }

    static void addCourse(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Course Name: ");
        String name = sc.next();
        System.out.print("Enter Teacher ID: ");
        int teacherId = sc.nextInt();
        String sql = "INSERT INTO courses (name, teacher_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, teacherId);
            pstmt.executeUpdate();
            System.out.println("Course added successfully!");
        }
    }

    static void viewCourses(Connection conn) throws SQLException {
        String sql = "SELECT courses.id, courses.name, teachers.name AS teacher FROM courses JOIN teachers ON courses.teacher_id = teachers.id";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Course ID: " + rs.getInt("id") + ", Course Name: " + rs.getString("name") + ", Teacher: " + rs.getString("teacher"));
            }
        }
    }

    static void enrollStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        System.out.print("Enter Course ID: ");
        int courseId = sc.nextInt();
        String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            pstmt.executeUpdate();
            System.out.println("Student enrolled successfully!");
        }
    }

    static void viewEnrollments(Connection conn) throws SQLException {
        String sql = "SELECT students.name AS student, courses.name AS course FROM enrollments JOIN students ON enrollments.student_id = students.id JOIN courses ON enrollments.course_id = courses.id";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Student: " + rs.getString("student") + ", Course: " + rs.getString("course"));
            }
        }
    }
}