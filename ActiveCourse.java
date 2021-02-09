import java.util.ArrayList;
import java.util.Comparator;

// Name: Alyanna Santos
// ID: 500962963

public class ActiveCourse extends Course
{
    private ArrayList<Student> students;
    private String             semester;
    private int startTime;
    private int lectureDuration;
    private String lectureDay;

    // ActiveCourse(): Constructor
    public ActiveCourse(String name, String code, String descr, String fmt, String semester, ArrayList<Student> students) {
        super(name, code, descr, fmt);
        this.semester = semester;
        this.students = new ArrayList<>(students);
    }

    // getSemester(): Returns semester string
    public String getSemester() { return semester; }

    // Scheduler Setters:
    public void setLectureDay(String lectureDay) { this.lectureDay = lectureDay; }

    public void setStartTime(int startTime) { this.startTime = startTime; }

    public void setLectureDuration(int lectureDuration) { this.lectureDuration = lectureDuration; }

    // Scheduler Getters:
    public String getLectureDay() { return lectureDay; }

    public int getStartTime() { return startTime; }

    public int getLectureDuration() { return lectureDuration; }

    // printClassList(): Prints the students (name and ID) in this course
    public void printClassList() {
        for (Student student : students)
            System.out.println(student);
    }

    // printGrades(): Prints the grade of each student in this course (with name and ID)
    public void printGrades() {
        for (Student student : students)
            System.out.println(student + " Grade: " + student.getGrade(getCode()));
    }

    // getGrade(): Returns the numeric grade in this course for this student; if not in class list, return 0
    public double getGrade(String studentId) {
        for (Student student: students)
            if (student.getId().equals(studentId)) {
                return student.getGrade(getCode());
            }
            return 0;
    }

    // getInfo(): Returns a String containing the course information, semester and the number
    // of students enrolled in the course
    @Override
    public String getInfo() { return super.getInfo() + "\n" + "Semester: " + getSemester() + " Enrollment: " + students.size(); }

    // sortByName(): Sort the students in the course by name using private class NameComparator
    public void sortByName() { students.sort(new NameComparator()); }

    // class NameComparator: Compares two Student objects based on student name
    private class NameComparator implements Comparator<Student> {
        public int compare(Student a, Student b)
        {
            return a.getName().compareTo(b.getName());
        }
    }

    // sortById(): Sort the students in the course by student id using private class IdComparator
    public void sortById() {
        students.sort(new IdComparator());
    }

    // class IdComparator: Compares two Student objects based on student id
    private class IdComparator implements Comparator<Student> {
        public int compare(Student a, Student b)
        {
           return a.getId().compareTo(b.getId());
        }
    }

    // ADDITIONAL METHODS ADDED:

    // addStudent(): Add student in active course
    public void addStudent(Student student) {
        this.students.add(student);
    }

    // dropStudent(): Remove student in active course
    public void dropStudent(Student student) {
        this.students.remove(student);
    }

    // studentInCourse(): Returns true if student is in active course class list; false if not
    public boolean studentInCourse(String studentId) {
        for (Student student: students)
          if (student.getId().equals(studentId)){
            return true;
          }
        return false;
    }
}

// New object ScheduledCourse to make a new tree map of courses that are set in Scheduler
class ScheduledCourse {
    private int startTime;
    private int lectureDuration;
    private String lectureDay;

    public ScheduledCourse(String lectureDay, int startTime, int lectureDuration) {
        this.lectureDay = lectureDay;
        this.lectureDuration = lectureDuration;
        this.startTime = startTime;
    }

    public void setLectureDay(String lectureDay) { this.lectureDay = lectureDay; }
    public void setStartTime(int startTime) { this.startTime = startTime; }
    public void setLectureDuration(int lectureDuration) { this.lectureDuration = lectureDuration; }

    public String getLectureDay() { return lectureDay; }
    public int getStartTime() { return startTime; }
    public int getLectureDuration() { return lectureDuration; }
}