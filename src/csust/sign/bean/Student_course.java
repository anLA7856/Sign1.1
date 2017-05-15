package csust.sign.bean;

import java.io.Serializable;

/**
 * 学生课程表，注意此处为了简单方便，所以就没有利用组合的方式 传递引用表示一对多，而是直接用一个int的id，此处有缺陷，应该用integer
 * 
 * @author anLA7856
 *
 */
public class Student_course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int student_course_id;
	private int student_id;
	private int course_id;

	public Student_course() {

	}

	public Student_course(int student_course_id, int student_id, int course_id) {
		super();
		this.student_course_id = student_course_id;
		this.student_id = student_id;
		this.course_id = course_id;
	}

	public int getStudent_course_id() {
		return student_course_id;
	}

	public void setStudent_course_id(int student_course_id) {
		this.student_course_id = student_course_id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	@Override
	public String toString() {
		return "Student_course [student_course_id=" + student_course_id
				+ ", student_id=" + student_id + ", course_id=" + course_id
				+ "]";
	}

}
