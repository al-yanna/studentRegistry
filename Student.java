import java.util.ArrayList;

// Name: Alyanna Santos
// ID: 500962963

public class Student implements Comparable<Student>
{
    private String name;
    private String id;
    public  ArrayList<CreditCourse> courses;
    private double grade;

    // Student(): Constructor
    public Student(String name, String id)
    {
        this.name = name;
        this.id   = id;
        courses   = new ArrayList<CreditCourse>();
        this.grade = 0;
    }

    // getId(): Gets ID string
    public String getId()
    {
        return id;
    }

    // getName(): Gets name string
    public String getName()
    {
        return name;
    }

    // getGrade(): Get grade in a credit course
    public double getGrade(String courseCode)
    {
        for (CreditCourse cc : courses) {
            if (cc.getCode().equalsIgnoreCase(courseCode)) {
                return cc.grade;
            }
        }
        return 0;
    }

    // addCourse() Method: Add a credit course to list of courses for this student
    public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
    {
        CreditCourse course = new CreditCourse(courseName, courseCode,  // create a CreditCourse object
                descr, format, sem, grade);
        course.setActive();                                             // set course active
        courses.add(course);                                            // add to courses array list
    }

    // printTranscript() Method: Prints a student transcript (completed courses for student)
    public void printTranscript()
    {
        for (CreditCourse course: courses) {
            if (!course.getActive()) {
                System.out.println(course.displayGrade() + "\n");
            }
        }
    }

    // printActiveCourses() Method: Prints all active courses this student is enrolled in
    public void printActiveCourses()
    {
        for (CreditCourse course: courses) {
            if (course.getActive()) {
                System.out.println(course.getInfo() + "\n");
            }
        }
    }

    // removeActiveCourse(): Drop a course
    public void removeActiveCourse(String courseCode)
    {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCode().equals(courseCode) && courses.get(i).active) {
                courses.remove(i);
                return;
            }
        }
    }


    // toString(): Returns string containing student ID and name
    public String toString()
    {
        return "Student ID: " + id + " Name: " + name;
    }

    // compareTo(): Make students comparable; used in equals() method
    public int compareTo(Student other)
    {
        return this.compareTo(other);
    }

    // equals() Method: Determines if student ID and name are the same
    @Override
    public boolean equals(Object other)
    {
        Student otherStudent = (Student) other;
        return this.name.equals(otherStudent.name) && this.id.equals(otherStudent.id);
    }
}
