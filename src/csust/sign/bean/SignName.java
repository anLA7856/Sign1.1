package csust.sign.bean;

import java.io.Serializable;
import java.sql.Date;
/**
 * 签到表，一个表一个记录
 * @author U-anLA
 *
 */
public class SignName implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sign_id;
	private Date sign_time;
	private String sign_state;
	private int course_id;
	private int student_id;

	public SignName() {

	}

	public int getSign_id() {
		return sign_id;
	}

	public void setSign_id(int sign_id) {
		this.sign_id = sign_id;
	}

	public Date getSign_time() {
		return sign_time;
	}

	public void setSign_time(Date sign_time) {
		this.sign_time = sign_time;
	}

	public String getSign_state() {
		return sign_state;
	}

	public void setSign_state(String sign_state) {
		this.sign_state = sign_state;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public SignName(int sign_id, Date sign_time, String sign_state,
			int course_id, int student_id) {
		super();
		this.sign_id = sign_id;
		this.sign_time = sign_time;
		this.sign_state = sign_state;
		this.course_id = course_id;
		this.student_id = student_id;
	}

	@Override
	public String toString() {
		return "SignName [sign_id=" + sign_id + ", sign_time=" + sign_time
				+ ", sign_state=" + sign_state + ", course_id=" + course_id
				+ ", student_id=" + student_id + "]";
	}

}
