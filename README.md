# studentRegistry
An object-oriented University Student Registry Simulator that functions to register/delete students, add/drop students from class lists, update student transcripts (courses and grades), sort class lists by ID or name, and print active course schedule. More specific commands and features are listed below.

## How to Run
* Populate student info on students.txt file.
* On the command line, execute the following commands:
1. [**L**] : list students in the registry. 
2. [**Q**] : quit program. 
3. [**REG** *(NAME) (5 DIGIT ID)]* : register a student.
4. [**DEL** *(STUDENT ID)]* : deletes a student from the registry.
5. [**ADDC** *(STUDENT ID) (COURSECODE)]* : adds a student to an active course.
6. [**DROPC** *(STUDENT ID) (COURSECODE)]* : drops a student from an active course.
7. [**PAC**]: prints all active course.
8. [**PCL** *(COURSECODE)]* : prints class list for an active course.
9. [**PGR** *(COURSECODE)]* : prints student id and grade for all students in an active course.
10. [**PSC** *(STUDENTID)]* : prints all credit courses for a student.
11. [**SFG** *(STUDENTID) (GRADE) (COURSECODE)]*: set final grade of a student in a course.
12. [**SCN** *(COURSECODE)]* : sort list of students in a course by student name.
13. [**SCI** *(COURSECODE)]* : sort list of students in a course by student id.
13. [**SCH** *(COURSECODE) (DAY) (STARTTIME) (DURATION)]* : schedules a course
14. [**CSCH** *(COURSECODE)]*. clears given course off the schedule
15. [**PSCH**] prints the entire schedule.

## Acknowledgements
* Project assigned by Dr. McInerney at Ryerson University.
