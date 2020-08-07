package FinalProject_Template;

/**
 * @Author lzh
 * @Date 2020/5/22 16:33
 */
public class StudentInfo {
    private String studentID;

    @Override
    public String toString() {
        return "StudentInfo{" +
                "studentID='" + studentID + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }

    private String studentName;

    public StudentInfo(String id, String name) {
        this.studentID = id;
        this.studentName = name;
    }


    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String id) {
        this.studentID = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String name) {
        this.studentName =name;
    }
}
