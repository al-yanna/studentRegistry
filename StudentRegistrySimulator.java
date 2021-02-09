import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Alyanna Santos

public class StudentRegistrySimulator
{
    public static void main(String[] args) throws RuntimeException {
        Registry registry = null;
        try {
            registry = new Registry();
        } catch (FileNotFoundException e) {
            System.out.println("students.txt file not found.");
        } catch (NoSuchElementException e) {
            System.out.println("Bad file format in students.txt");
        }

        Scheduler scheduler = new Scheduler(registry.getCourses());
        Scanner scanner = new Scanner(System.in);
        System.out.print(">");

        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();
            if (inputLine == null || inputLine.equals("")) continue;

            Scanner commandLine = new Scanner(inputLine);
            String command = commandLine.next();
            if (command == null || command.equals("")) continue;

            else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST")) {
                registry.printAllStudents();
            }

            else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
                return;

            // REG Command: Register a new student in registry
            else if (command.equalsIgnoreCase("REG")) {
                String studentName = null;
                if (commandLine.hasNext()) {
                    studentName = commandLine.next();
                    if (!isStringOnlyAlphabet(studentName)) {
                        System.out.println("Invalid characters in student name: " + studentName);
                        continue;
                    }
                }

                String studentId;
                if (commandLine.hasNext()) {
                    studentId = commandLine.next();
                    if (!isNumeric(studentId)) {
                        System.out.println("Invalid characters in student ID: " + studentId);
                        continue;
                    }
                    if (registry.addNewStudent(studentName, studentId)) {
                        registry.addNewStudent(studentName, studentId);
                        System.out.println(studentName + " successfully registered");
                    }
                    else {
                        System.out.println(studentName + " is already registered");
                    }
                }
            }

            // DEL Command: Delete a student from registry
            else if (command.equalsIgnoreCase("DEL")) {
                String studentId;
                if (commandLine.hasNext()) {
                    studentId = commandLine.next();
                    if (!isNumeric(studentId)) {
                        System.out.println("Invalid characters in student ID: " + studentId);
                        continue;
                    }
                    if (registry.removeStudent(studentId)) {
                        registry.removeStudent(studentId);
                        System.out.println("Student successfully removed");
                    }
                    else {
                        System.out.println("Student not registered");
                    }
                }
            }

            // ADDC Command: Add a student to an active course
            else if (command.equalsIgnoreCase("ADDC")) {
                if (commandLine.hasNext()) {
                    try {
                        String studentId = commandLine.next();
                        if (!isNumeric(studentId)) {
                            System.out.println("Invalid characters in student ID: " + studentId);
                            continue;
                        }
                        String courseCode = commandLine.next();
                        registry.addCourse(studentId, courseCode);
                    } catch (NoSuchElementException e) {
                        System.out.println("Missing parameter in commandLine");
                    }
                }
            }

            // DROPC Command: Drop student from course
            else if (command.equalsIgnoreCase("DROPC")) {
                String studentId = null;
                if (commandLine.hasNext()) {
                    studentId = commandLine.next();
                    if (!isNumeric(studentId)) {
                        System.out.println("Invalid characters in student ID: " + studentId);
                        continue;
                    }
                }

                String courseCode;
                if (commandLine.hasNext()) {
                    courseCode = commandLine.next();
                    registry.dropCourse(studentId, courseCode);
                }
            }


            // PAC Command: Print all active courses
            else if (command.equalsIgnoreCase("PAC")) {
                registry.printActiveCourses();
            }

            // PCL Command: Print class list from course
            else if (command.equalsIgnoreCase("PCL")) {
                String courseCode;
                if (commandLine.hasNext()) {
                    courseCode = commandLine.next();
                    registry.printClassList(courseCode);
                }
            }

            // PGR Command: Print name, id, and grade of class list in active course
            else if (command.equalsIgnoreCase("PGR")) {
                String courseCode;
                if (commandLine.hasNext()) {
                    courseCode = commandLine.next();
                    registry.printGrades(courseCode);
                }
            }

            // PSC Command: Print all credit courses of student
            else if (command.equalsIgnoreCase("PSC")) {
                String studentId;
                if (commandLine.hasNext()) {
                    studentId = commandLine.next();
                    if (!isNumeric(studentId)) {
                        System.out.println("Invalid characters in student ID: " + studentId);
                        continue;
                    }
                    registry.printStudentCourses(studentId);
                }
            }

            // PST Command: Print student transcript
            else if (command.equalsIgnoreCase("PST")) {
                String studentId;
                if (commandLine.hasNext()) {
                    studentId = commandLine.next();
                    if (!isNumeric(studentId)) {
                        System.out.println("Invalid characters in student ID: " + studentId);
                    }
                    registry.printStudentTranscript(studentId);
                }
            }

            // SFG Command: Set final grade of student
            else if (command.equalsIgnoreCase("SFG")) {
                String courseCode = null;
                if (commandLine.hasNext()) {
                    courseCode = commandLine.next();
                }

                String studentId = null;
                if (commandLine.hasNext()) {
                    studentId = commandLine.next();
                    if (!isNumeric(studentId)) {
                        System.out.println("Invalid characters in student ID: " + studentId);
                        continue;
                    }
                }

                double grade;
                if (commandLine.hasNext()) {
                    grade = commandLine.nextDouble();
                    registry.setFinalGrade(courseCode, studentId, grade);
                }
            }

            // SCN Command: Sort list of students in course by name
            else if (command.equalsIgnoreCase("SCN")) {
                String courseCode;
                if (commandLine.hasNext()) {
                    courseCode = commandLine.next();
                    registry.sortCourseByName(courseCode);
                }
            }

            // SCI Command: Sort list of students in course by student id
            else if (command.equalsIgnoreCase("SCI")) {
                String courseCode;
                if (commandLine.hasNext()) {
                    courseCode = commandLine.next();
                    registry.sortCourseById(courseCode);
                }
            }

            // SCH Command: Prints the course code, day, and start duration
            else if (command.equalsIgnoreCase("SCH")) {
                if (commandLine.hasNext()) {
                    try {
                        String courseCode = commandLine.next().toUpperCase();
                        String day = commandLine.next().toUpperCase();
                        int start = commandLine.nextInt();
                        int duration = commandLine.nextInt();
                        scheduler.setDayAndTime(courseCode, day, start, duration);
                        System.out.println(courseCode + " schedule successfully set");
                    } catch (UnknownCourseException | InvalidDayException | InvalidTimeException |
                            InvalidDurationException | LectureTimeCollisionException e) {
                        System.out.println(e.getMessage());
                    } catch (NoSuchElementException e) {
                        System.out.println("Missing parameter in commandLine");
                    }
                }
            }

            // CSCH Command: Clears the schedule of the given course
            else if (command.equalsIgnoreCase("CSCH")) {
                if (commandLine.hasNext()) {
                    try {
                        String courseCode = commandLine.next().toUpperCase();
                        scheduler.clearSchedule(courseCode);
                        System.out.println(courseCode + " schedule successfully cleared");
                    } catch (UnknownCourseException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            // PSCH Command: Prints the entire schedule
            else if (command.equalsIgnoreCase("PSCH")) {
                scheduler.printSchedule();
            }

            System.out.print("\n>");
        }
    }

    // isStringOnlyAlphabet(): Check if string str contains only alphabetic characters
    public static boolean isStringOnlyAlphabet(String str) {
        boolean valid = true;
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            valid = Character.isAlphabetic(ch);
        }
        return valid;
    }

    // isNumeric(): Check if string str contains only numeric characters (and 5 in length)
    public static boolean isNumeric(String str) {
        boolean valid = true;
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            valid = Character.isDigit(ch) && str.length() == 5;
        }
        return valid;
    }
}
