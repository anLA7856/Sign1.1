package csust.sign.bean;

import java.io.Serializable;
import java.sql.Date;
/**
 * 允许签到的sign表
 * @author anLA7856
 *
 */
public class Allow_sign implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int allow_sign_id;
	private int teacher_id;
	private Date sign_time;
	private int target;

	public Allow_sign() {

	}

	public Allow_sign(int allow_sign_id, int teacher_id, Date sign_time,
			int target) {
		super();
		this.allow_sign_id = allow_sign_id;
		this.teacher_id = teacher_id;
		this.sign_time = sign_time;
		this.target = target;
	}

	public int getAllow_sign_id() {
		return allow_sign_id;
	}

	public void setAllow_sign_id(int allow_sign_id) {
		this.allow_sign_id = allow_sign_id;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public Date getSign_time() {
		return sign_time;
	}

	public void setSign_time(Date sign_time) {
		this.sign_time = sign_time;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "Allow_sign [allow_sign_id=" + allow_sign_id + ", teacher_id="
				+ teacher_id + ", sign_time=" + sign_time + ", target="
				+ target + "]";
	}
	
	

}
