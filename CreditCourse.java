
// Name: Alyanna Santos
// ID: 500962963

public class CreditCourse extends Course
{
    private String semester;
    public boolean active;
    public double grade;

    // CreditCourse(): Constructor
    public CreditCourse(String name, String code, String descr, String fmt,String semester, double grade)
    {
        super(name, code, descr, fmt);
        this.semester= semester;
        this.grade = grade;
        this.active = true;
    }

    // getActive(): Returns active status
    public boolean getActive()
    {
        return active;
    }

    // setActive(): Sets active as true
    public void setActive()
    {
        active = true;
    }

    // setActive(): Sets active as false
    public void setInactive()
    {
        active = false;
    }

    // getGrade(): Returns the grade double
    public double getGrade()
    {
        return grade;
    }

    // displayGrade(): Returns string that contains the information, semester, and (letter) grade achieved in this course
    public String displayGrade()
    {
        return super.getInfo() + "\n" + "Semester: " + semester + " Grade: " + convertNumericGrade(grade);
    }

}