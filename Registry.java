import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

// Alyanna Santos

public class Registry
{
    private TreeMap<String, Student> students = new TreeMap<>();
    private TreeMap<String, ActiveCourse> courses  = new TreeMap<>();

    public Registry() throws FileNotFoundException, NoSuchElementException{
        try {
            Scanner scanner = new Scanner(new File("src/students.txt"));
            while (scanner.hasNextLine()) {
                String studentName = scanner.next();
                String studentId = scanner.next();
                Student student = new Student(studentName, studentId);
                students.put(studentId, student);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException();
        }

        // CPS209
        ArrayList<Student> list = new ArrayList<>();
        String courseName = "Computer Science II";
        String courseCode = "CPS209";
        String descr = "Learn how to write complex programs!";
        String format = "3Lec 2Lab";
        courses.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format,"W2020", list));

        // CPS511
        list.clear();
        courseName = "Computer Graphics";
        courseCode = "CPS511";
        descr = "Learn how to write cool graphics programs";
        format = "3Lec";
        courses.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format,"W2020", list));

        // CPS643
        list.clear();
        courseName = "Virtual Reality";
        courseCode = "CPS643";
        descr = "Learn how to write extremely cool virtual reality programs";
        format = "3Lec 2Lab";
        courses.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format,"W2020", list));

        // CPS706
        list.clear();
        courseName = "Computer Networks";
        courseCode = "CPS706";
        descr = "Learn about Computer Networking";
        format = "3Lec 1Lab";
        courses.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format,"W2020", list));

        // CPS616
        list.clear();
        courseName = "Algorithms";
        courseCode = "CPS616";
        descr = "Learn about Algorithms";
        format = "3Lec 1Lab";
        courses.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format,"W2020", list));
    }

    // addNewStudent(): Add new student to the registry
    public boolean addNewStudent(String studentName, String studentId) {
        Student newStudent = new Student(studentName, studentId);
        if (students.containsKey(studentId)) {
            return false;
        }
        students.put(studentId, newStudent);
        return true;
    }

    // removeStudent(): Remove student from registry
    public boolean removeStudent(String studentId) {
        if (students.containsKey(studentId)) {
            students.remove(studentId);
            return true;
        }
        return false;
    }

    // printAllStudents(): Print all registered students
    public void printAllStudents() {
        for (String student : students.keySet()) {
            System.out.println(students.get(student));
        }
    }

    // addCourse(): Add student to the active course
    public void addCourse(String studentId, String courseCode) {
        boolean takenBefore = false;
        courseCode = courseCode.toUpperCase();

        // If student has taken the course before
        if (students.containsKey(studentId)) {
            for (CreditCourse cc : students.get(studentId).courses) {
                if (cc.getCode().equalsIgnoreCase(courseCode)) {
                    if (!cc.getActive()) {
                        System.out.println("Student already completed this course");
                        takenBefore = true;
                    }
                }
            }
        }

        // If student has not taken the course before
        if (!takenBefore) {
            if (courses.containsKey(courseCode)) {                      // Find the active course
                ActiveCourse ac = courses.get(courseCode);
                if (students.containsKey(studentId)) {                  // Find student
                    Student student = students.get(studentId);
                    if (ac.studentInCourse(studentId)) {                // Find student in course's class list
                        System.out.println("Student is already enrolled in this course");
                    }
                    else {
                        ac.addStudent(student);                         // Add student to the active course
                        student.addCourse(ac.getName(), ac.getCode(),   // Add course to student credit courses list
                                ac.getDescription(), ac.getFormat(), ac.getSemester(), 0);
                        System.out.println(student.getName() + " successfully registered in " + courseCode);
                    }
                }
            }
        }
    }

    // dropCourse(): Drop student from the active course
    public void dropCourse(String studentId, String courseCode) {
        courseCode = courseCode.toUpperCase();
        if (courses.containsKey(courseCode)) {                      // Find the active course
            ActiveCourse ac = courses.get(courseCode);
            if (students.containsKey(studentId)) {                  // Find student in class list
                Student student = students.get(studentId);
                if (ac.studentInCourse(studentId)) {                // If student found:
                    ac.dropStudent(student);                        // Remove student from the active course
                    student.removeActiveCourse(courseCode);         // Remove course from the student's credit course list
                    System.out.println(student.getName() + " successfully removed in " + courseCode);
                }
            }
        }
    }

    // printActiveCourses(): Print all active courses
    public void printActiveCourses() {
        for (String course : courses.keySet()) {
            System.out.println(courses.get(course).getInfo() + "\n");
        }
    }

    // printClassList(): Print the list of students in an active course
    public void printClassList(String courseCode) {
        courseCode = courseCode.toUpperCase();
        if (courses.containsKey(courseCode)) {
            courses.get(courseCode).printClassList();
        }
    }

    // sortCourseByName(): Find course and sort class list by student name
    public void sortCourseByName(String courseCode) {
        courseCode = courseCode.toUpperCase();
        if (courses.containsKey(courseCode)) {
            courses.get(courseCode).sortByName();
            courses.get(courseCode).printClassList();
        }
    }

    // sortCourseById(): Find course and sort class list by student name
    public void sortCourseById(String courseCode) {
        courseCode = courseCode.toUpperCase();
        if (courses.containsKey(courseCode)) {
            courses.get(courseCode).sortById();
            courses.get(courseCode).printClassList();
        }
    }

    // printGrades(): Find course and print student names and grades
    public void printGrades(String courseCode) {
        courseCode = courseCode.toUpperCase();
        if (courses.containsKey(courseCode)) {
            courses.get(courseCode).printGrades();
        }
    }

    // printStudentCourses(): Print all active courses of student
    public void printStudentCourses(String studentId) {
        if (students.containsKey(studentId)) {
            students.get(studentId).printActiveCourses();
        }
    }

    //  printStudentTranscript(): Print all completed courses and grades of student
    public void printStudentTranscript(String studentId) {
        if (students.containsKey(studentId)) {
            students.get(studentId).printTranscript();
        }
    }

    // setFinalGrade(): Set the final grade of the student
    public void setFinalGrade(String courseCode, String studentId, double grade) {
        courseCode = courseCode.toUpperCase();
        if (courses.containsKey(courseCode)) {                                // Find active course
            if (students.containsKey(studentId)) {
                if (courses.get(courseCode).studentInCourse(studentId)) {     // Find student in class list
                    for (CreditCourse cc : students.get(studentId).courses) { // Find course in student credit course list
                        if (cc.getCode().equals(courseCode)) {
                            cc.grade = grade;                                 // Set the grade in credit course
                            cc.setInactive();                                 // Set course inactive
                            System.out.println(students.get(studentId).getName() + " completed " + courseCode
                            + " with a final grade of " + grade);
                        }
                    }
                }
            }
        }
    }

    public TreeMap<String, ActiveCourse> getCourses() {
        return courses;
    }
}
