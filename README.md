# School Management System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

A simple School Management System built using Java and MySQL to manage students, teachers, courses, and enrollments efficiently.

## Features
- Add and view students
- Add and view teachers
- Add and view courses
- Enroll students in courses
- View all enrollments

## Installation
### Prerequisites
- Java (JDK 11+)
- MySQL Server
- MySQL Connector for Java

### Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/school-mgmt-system.git
   cd school-mgmt-system
   ```
2. Import the database using the provided SQL file:
   ```sh
   mysql -u root -p < school_db.sql
   ```
3. Run the program using `run.bat`:
   ```sh
   run.bat
   ```

## Usage
- Follow the menu-driven interface to add students, teachers, and courses.
- Enroll students in courses.
- View student and course details.

## Future Enhancements
- GUI for better user experience
- Role-based authentication (Admin, Teacher, Student)
- Attendance and grading system

## Contributing
Contributions are welcome! Feel free to fork the repository and submit a pull request.

## License
This project is licensed under the MIT License.

