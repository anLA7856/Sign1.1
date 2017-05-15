package csust.sign.bean;

/**
 * 允许签到的实体类 与Android端对应
 * 
 * @author anLA7856
 *
 */
public class AllowSignInfo {
	private int allow_sign_id;
	private int course_id;
	private String sign_time;

	public int getAllow_sign_id() {
		return allow_sign_id;
	}

	public void setAllow_sign_id(int allow_sign_id) {
		this.allow_sign_id = allow_sign_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public String getSign_time() {
		return sign_time;
	}

	public void setSign_time(String sign_time) {
		this.sign_time = sign_time;
	}

	@Override
	public String toString() {
		return "AllowSignInfo [allow_sign_id=" + allow_sign_id + ", course_id="
				+ course_id + ", sign_time=" + sign_time + "]";
	}

}
