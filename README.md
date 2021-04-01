# studentRegistry
An object-oriented University Student Registry Simulator that functions to register/delete students, add/drop students from class lists, update student transcripts (courses and grades), sort class lists by ID or name, and print active course schedule. More specific commands and features are listed below.

## How to Run
* Populate student info on students.txt file.
* On the command line, execute the following commands:

| Command | Description                                                | Example Code                               |
| :------ |:---------------------------------------------------------- | :----------------------------------------- |
| L       | list students in the registry                              |> **L**                                     |
| Q       | quit program                                               |> **Q**                                     |
| REG     | register a student                                         |> **REG** studentName studentID             |
| DEL     | delete a student                                           |> **DEL** studentID                         |
| ADDC    | adds a student to an active course.                        |> **ADDC** studentID courseCode             |
| DROPC   | drops a student from an active course.                     |> **DROPC** studentID courseCode            |
| PAC     | prints all active courses                                  |> **PAC**                                   |
| PCL     | prints class list for an active course                     |> **PCL** courseCode                        |
| PGR     | prints class list with IDs and grades for an active course |> **PGR** courseCode                        |
| PSC     | prints all credit courses for a student                    |> **PSC** studentID                         |
| SFG     | set final course grade of a student                        |> **SFG** studentID grade courseCode        |
| SCN     |  sort list of students in a course by student name         |> **SCN** courseCode                        |
| SCI     | sort list of students in a course by student id.           |> **SCI** coureCode                         |
| SCH     | schedules a course                                         |> **SCH** courseCode day startTime duration |
| CSCH    | clears given course off the schedule                       |> **CSCH** courseCode                       |
| PSCH     | prints the schedule table                                 |> **PSCH**                                  |

## Acknowledgements
* Project assigned by Dr. McInerney at Ryerson University.
