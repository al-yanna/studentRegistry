
// Alyanna Santos

public class Course
{
    private String name;
    private String code;
    private String description;
    private String format;

    // Course(): Constructor
    public Course(String name, String code, String description, String format)
    {
        this.name        = name;
        this.code        = code;
        this.description = description;
        this.format      = format;
    }

    // getName(): Returns course name
    public String getName()
    {
        return name;
    }

    // getCode(): Returns course code
    public String getCode()
    {
        return code;
    }

    // getDescription(): Returns course description
    public String getDescription()
    {
        return description;
    }

    // getFormat(): Returns course format
    public String getFormat()
    {
        return format;
    }

    // getInfo(): Returns course code, name, description, and format
    public String getInfo()
    {
        return getCode() + " - " + getName() + "\n" + getDescription() + "\n" + "Format: " + getFormat();
    }

    // convertNumericGrade() Method: Convert numeric score to letter grade string
    public static String convertNumericGrade(double score)
    {
        if (score >= 100)
            return "A+";
        if (score < 100 && score >= 90)
            return "A+";
        if (score < 90 && score >= 85)
            return "A";
        if (score < 85 && score  >= 80)
            return "A+";
        if (score < 80 && score  >= 77)
            return "B+";
        if (score  < 77 && score >= 73)
            return "B";
        if (score < 73 && score >= 70)
            return "B-";
        if (score < 70 && score >= 67)
            return "C+";
        if (score < 67 && score >= 63)
            return "C";
        if (score < 63 && score  >= 60)
            return "C-";
        if (score  < 60 && score >= 57)
            return "D+";
        if (score < 57 && score  >= 53)
            return "D";
        if (score < 53 && score  >= 50)
            return "D-";
        else
            return "F";
    }
}
