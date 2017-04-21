package csust.sign.bean;

/**
 * 用于生成报表时候，学生的信息
 * 
 * @author U-ANLA
 *
 */
public class StudentReportInfo {

	private int student_id;
	private String student_name;
	private String student_username;

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getStudent_username() {
		return student_username;
	}

	public void setStudent_username(String student_username) {
		this.student_username = student_username;
	}

	@Override
	public String toString() {
		return "StudentReportInfo [student_id=" + student_id
				+ ", student_name=" + student_name + ", student_username="
				+ student_username + "]";
	}

}
