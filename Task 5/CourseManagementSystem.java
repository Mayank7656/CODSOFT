import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Course class definition
class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int enrolledStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents;
    }

    public boolean enrollStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
            return true;
        }
        return false;
    }
}

// Student class definition
class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (!registeredCourses.contains(course) && course.enrollStudent()) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course) && course.dropStudent()) {
            registeredCourses.remove(course);
            return true;
        }
        return false;
    }
}

// CourseDatabase class definition
class CourseDatabase {
    private List<Course> courses;

    public CourseDatabase() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }
}

// StudentDatabase class definition
class StudentDatabase {
    private List<Student> students;

    public StudentDatabase() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student findStudentById(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equalsIgnoreCase(studentID)) {
                return student;
            }
        }
        return null;
    }
}

// Main class definition
public class CourseManagementSystem {
    public static void main(String[] args) {
        CourseDatabase courseDatabase = new CourseDatabase();
        StudentDatabase studentDatabase = new StudentDatabase();

        // Add some initial courses
        courseDatabase.addCourse(new Course("CS101", "Intro to Computer Science", "Basic CS course", 30, "MWF 9-10AM"));
        courseDatabase.addCourse(new Course("MATH101", "Calculus I", "Basic calculus course", 25, "TTh 10-11:30AM"));

        // Add some initial students
        studentDatabase.addStudent(new Student("S123", "Alice"));
        studentDatabase.addStudent(new Student("S124", "Bob"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Display available courses");
            System.out.println("2. Register for a course");
            System.out.println("3. Drop a course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    System.out.println("\nAvailable courses:");
                    for (Course course : courseDatabase.getCourses()) {
                        System.out.println(course.getCode() + ": " + course.getTitle() + " (" + course.getAvailableSlots() + " slots available)");
                    }
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String studentID = scanner.nextLine();
                    Student student = studentDatabase.findStudentById(studentID);
                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.print("Enter course code to register: ");
                    String courseCode = scanner.nextLine();
                    Course courseToRegister = courseDatabase.findCourseByCode(courseCode);
                    if (courseToRegister == null) {
                        System.out.println("Course not found.");
                        break;
                    }

                    if (student.registerCourse(courseToRegister)) {
                        System.out.println("Successfully registered for " + courseToRegister.getTitle());
                    } else {
                        System.out.println("Failed to register. The course might be full or already registered.");
                    }
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextLine();
                    student = studentDatabase.findStudentById(studentID);
                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.print("Enter course code to drop: ");
                    courseCode = scanner.nextLine();
                    Course courseToDrop = courseDatabase.findCourseByCode(courseCode);
                    if (courseToDrop == null) {
                        System.out.println("Course not found.");
                        break;
                    }

                    if (student.dropCourse(courseToDrop)) {
                        System.out.println("Successfully dropped " + courseToDrop.getTitle());
                    } else {
                        System.out.println("Failed to drop. The course might not be registered.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
