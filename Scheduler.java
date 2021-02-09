import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;

public class Scheduler
{
    TreeMap<String,ActiveCourse> courses;
    private TreeMap<String,ScheduledCourse> scheduledCourses = new TreeMap<>();

    public Scheduler(TreeMap<String, ActiveCourse> courses) {
        this.courses = courses;
    }

    // setDayAndTime(): Set lectureDay, startTime, and lectureDuration of the corresponding active course object
    public void setDayAndTime(String courseCode, String lectureDay, int startTime, int lectureDuration) throws RuntimeException {
        int endTime = (lectureDuration * 100);
        if (!courses.containsKey(courseCode.toUpperCase())) {
            throw new UnknownCourseException("UnknownCourseException: courseCode cannot be found");
        }
        ActiveCourse course = courses.get(courseCode);

        if (!Arrays.asList("MON", "TUE", "WED", "THUR", "FRI").contains(lectureDay)){
            throw new InvalidDayException("InvalidDayException: lectureDay should be one of the following: MON, TUE, WED, THUR, FRI");
        }
        course.setLectureDay(lectureDay);

        if (startTime < 800 || (startTime + endTime - 100 > 1700)) {
            throw new InvalidTimeException("InvalidTimeException: startTime should not be less than 0800 and endTime should not be greater than 1700");
        }
        course.setStartTime(startTime);

        if (lectureDuration < 0 || lectureDuration > 3) {
            throw new InvalidDurationException("InvalidDurationException: lectureDuration should be 1, 2, or 3 hours");
        }

        for (String sCourse : scheduledCourses.keySet()) {
            ScheduledCourse scheduledCourse = scheduledCourses.get(sCourse);
            if (!sCourse.equals(courseCode)) {
                if (scheduledCourse.getLectureDay().equals(lectureDay)) {
                    for (int duration = 1; duration <= scheduledCourse.getLectureDuration(); duration++) {
                        if (scheduledCourse.getStartTime() == startTime ||
                                (scheduledCourse.getStartTime() + (duration * 100) - 100 == (lectureDuration * 100) + startTime - 100 )) {
                            throw new LectureTimeCollisionException("LectureTimeCollisionException: Overlaps with another scheduled course");
                        }
                    }
                }
            }
        }

        scheduledCourses.put(courseCode, new ScheduledCourse(lectureDay, startTime, lectureDuration));
    }

    // clearSchedule(): Reset scheduler variables of the corresponding active course
    public void clearSchedule(String courseCode) {
        if (!courses.containsKey(courseCode.toUpperCase())) {
            throw new UnknownCourseException("UnknownCourseException: courseCode cannot be found");
        }
        ActiveCourse course = courses.get(courseCode);
        course.setLectureDay("");
        course.setStartTime(0);
        course.setLectureDuration(0);
        scheduledCourses.remove(courseCode);
    }

    //  printSchedule(): Prints 2D schedule
    public void printSchedule() {
        // 2D Array
        String[][] schedule2D = new String[11][6];
        for (String[] row: schedule2D)
            Arrays.fill(row, "    ");

        schedule2D[0][1] = "MON"; schedule2D[0][2] = "TUE"; schedule2D[0][3] = "WED"; schedule2D[0][4] = "THUR"; schedule2D[0][5] = "FRI";
        schedule2D[1][0] = "0800"; schedule2D[2][0] = "0900"; schedule2D[3][0] = "1000"; schedule2D[4][0] = "1100"; schedule2D[5][0] = "1200";
        schedule2D[6][0] = "1300"; schedule2D[7][0] = "1400"; schedule2D[8][0] = "1500"; schedule2D[9][0] = "1600"; schedule2D[10][0] = "1700";

        // Going through all the scheduled courses
        String[] days = {"MON", "TUE", "WED", "THUR", "FRI"};
        int[] hours = {800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700};
        int lectureDay = 0; int startTime = 0;
        for (String course : scheduledCourses.keySet()) {
            ScheduledCourse sc = scheduledCourses.get(course);

            // Assigning days as column indexes
            for (int i = 0; i < 5; i++) {
                if (sc.getLectureDay().equalsIgnoreCase(days[i])) {
                    lectureDay = i + 1;
                }
            }

            // Assigning hours as row indexes
            for (int i = 0; i < 9; i++) {
                if (sc.getStartTime() == hours[i]) {
                    startTime = i + 1;
                }
            }

            // Inserting the courses
            for (int duration = 0 ; duration < sc.getLectureDuration() ; duration++) {
                schedule2D[startTime++][lectureDay] = course;
            }
        }

        System.out.println(Arrays.deepToString(schedule2D)
                .replace("],", "\n").replace(",", "\t")
                .replaceAll("[\\[\\]]", " "));

    }
}

// Custom exceptions for class scheduler

class UnknownCourseException extends RuntimeException{
    public UnknownCourseException(String message){
        super(message);
    }
}

class InvalidDayException extends RuntimeException{
    public InvalidDayException(String message){
        super(message);
    }
}

class InvalidTimeException extends RuntimeException{
    public InvalidTimeException(String message){
        super(message);
    }
}

class InvalidDurationException extends RuntimeException{
    public InvalidDurationException(String message){
        super(message);
    }
}

class LectureTimeCollisionException extends RuntimeException{
    public LectureTimeCollisionException(String message){
        super(message);
    }
}

