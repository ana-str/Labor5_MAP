package uni.view;

import uni.controller.RegistrationSystem;
import uni.entities.Course;
import uni.entities.Student;
import uni.entities.Teacher;
import uni.exceptions.NonExistingDataException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {
    private final RegistrationSystem control;

    public MenuConsole(RegistrationSystem control) {

        this.control = control;
    }


    public void printMenu() {
        System.out.println("""
                    Welcome! The options are as follows:
                    1. Add new Student
                    2. Add new Teacher
                    3. Add new Course
                    4. Delete Student
                    5. Delete Teacher
                    6. Delete Course
                    7. Update Student
                    8. Update Teacher
                    9. Update Course
                    10. Register
                    11. Courses with free places
                    12. Students enrolled for a course
                    13. Show all curses
                    14. Sort Students by Name
                    15. Sort Students by Credits Descending
                    16. Filter Students by Credits
                    17. Filter Courses by Credits
                    18. Sort Courses by Credits
                    19. Sort Courses by Name
                    20. Show all students
                    21. EXIT""");
    }


}
