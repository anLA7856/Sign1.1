package csust.sign.bean;

/**
 * 签到的报表信息bean，与Android端信息相对应
 * 
 * @author anLA7856
 *
 */
public class SignReportInfo {

	private int student_id;
	private String student_name;
	private String student_username;
	private int allow_sign_id;
	private String sign_state;

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

	public int getAllow_sign_id() {
		return allow_sign_id;
	}

	public void setAllow_sign_id(int allow_sign_id) {
		this.allow_sign_id = allow_sign_id;
	}

	public String getSign_state() {
		return sign_state;
	}

	public void setSign_state(String sign_state) {
		this.sign_state = sign_state;
	}

	@Override
	public String toString() {
		return "SignReportInfo [student_id=" + student_id + ", student_name="
				+ student_name + ", student_username=" + student_username
				+ ", allow_sign_id=" + allow_sign_id + ", sign_state="
				+ sign_state + "]";
	}

}
