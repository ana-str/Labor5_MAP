package uni.view;

import uni.controller.RegistrationSystem;
import uni.entities.Course;
import uni.entities.Student;
import uni.entities.Teacher;
import uni.exceptions.NonExistingDataException;

import java.sql.SQLException;
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

    public void runConsole() throws SQLException {
        printMenu();
        while(true) {
            Scanner myInput = new Scanner(System.in);
            int option = myInput.nextInt();

            switch (option) {
                case 0:
                    printMenu();
                    break;
                case 1:
                    Student student = createStudent();
                    control.getStudentController().add(student);
                    break;
                case 2:
                    Teacher teacher = createTeacher();
                    control.getTeacherController().add(teacher);
                    break;
                case 3:
                    Course course = createCourse();
                    control.addCourse(course);
                    break;
                case 4:
                    myInput.nextLine();
                    System.out.println("Give student ID: ");
                    long id = myInput.nextInt();
                    control.getStudentController().deleteByID(id);
                    break;
                case 5:
                    myInput.nextLine();
                    System.out.println("Give teacher ID: ");
                    int idTeacher = myInput.nextInt();
                    control.getTeacherController().deleteByID(idTeacher);
                    break;
                case 6:
                    myInput.nextLine();
                    System.out.println("Give name: ");
                    String name = myInput.nextLine();
                    control.deleteCourse(name);
                    break;
                case 7:
                    control.getStudentController().update(createStudent());
                    break;
                case 8:
                    control.getTeacherController().update(createTeacher());
                    break;
                case 9:
                    control.getCourseController().update(createCourse());
                    break;
                case 10:
                    myInput.nextLine();
                    System.out.println("Give course name: ");
                    String courseName = myInput.nextLine();
                    System.out.println("Give student ID: ");
                    int studentID = myInput.nextInt();
                    boolean result = false;
                    try {
                        result=control.register(courseName, studentID);
                    } catch (NonExistingDataException exception) {
                        System.out.println(exception.getMessage());
                    }
                    if (result) {
                        System.out.println("Student enrolled successfully");
                    }
                    else {
                        System.out.println("Error, student wasn't enrolled");
                    }
                    break;
                case 11:
                    printCourses(control.retrieveCoursesWithFreePlaces());
                    break;
                case 12:
                    myInput.nextLine();
                    System.out.println("Give course name: ");
                    courseName = myInput.nextLine();
                    printStudents(control.retrieveStudentsEnrolledForACourse(courseName));
                    break;
                case 13:
                    printCourses(control.getAllCourses());
                    break;
                case 14:
                    control.getStudentController().sortByName();
                    printStudents(control.getStudentController().getAll());
                    break;
                case 15:
                    control.getStudentController().sortByCreditsDescending();
                    printStudents(control.getStudentController().getAll());
                    break;
                case 16:
                    myInput.nextLine();
                    System.out.println("Give number of total credits: ");
                    int totalCredits = myInput.nextInt();
                    printStudents(control.getStudentController().filterByTotalCredits(totalCredits));
                    break;
                case 17:
                    myInput.nextLine();
                    System.out.println("Give number of credits: ");
                    int credits = myInput.nextInt();
                    printCourses(control.getCourseController().filterByCredits(credits));
                    break;
                case 18:
                    control.getCourseController().sortByCredits();
                    printCourses(control.getCourseController().getAll());
                    break;
                case 19:
                    control.getCourseController().sortByName();
                    printCourses(control.getCourseController().getAll());
                    break;
                case 20:
                    printStudents(control.getStudentController().getAll());
                    break;
                case 21:
                    return;
                default:
                    System.out.println("Option not valid");
                    runConsole();
            }
        }
    }

    public Teacher createTeacher() {
        Scanner in = new Scanner(System.in);
        System.out.println("Teacher first name: ");
        String firstName = in.nextLine();
        System.out.println("Teacher last name");
        String lastName = in.nextLine();
        System.out.println("Teacher ID: ");
        int teacherID = in.nextInt();
        return new Teacher(firstName, lastName, teacherID);
    }
    /**
     * creates a course with the attributes given by user
     * @return a new course with the given parameter
     */
    public Object createCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Give course title: ");
        String name = in.nextLine();
        System.out.println("Give teacher ID: ");
        int teacherID = in.nextInt();
        System.out.println("Give max number of enrolled students: ");
        int maxEnrollment = in.nextInt();
        System.out.println("Give number of credits: ");
        int credits = in.nextInt();
        return new Course(name, teacherID, maxEnrollment, credits);
    }

    /**
     * creates a student with the attributes given by user
     * @return a new student with the given parameter
     */
     public Student createStudent() {
         Scanner in = new Scanner(System.in);
         System.out.println("Give first name: ");
         String firstName = in.nextLine();
         System.out.println("Give last name: ");
         String lastName = in.nextLine();
         System.out.println("Give ID: ");
         int studentID = in.nextInt();
         return new Student(firstName, lastName, studentID);

     }
     /**
     * shows all the courses in the list given as parameter
     * @param courses list of courses to be shown
     */
    public void printCourses(List<Course> courses) {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    /**
     * shows all the students in the list given as parameter
     * @param students list of students to be shown
     */
    public void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }



    }


}
