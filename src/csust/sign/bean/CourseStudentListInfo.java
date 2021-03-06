package csust.sign.bean;

import java.util.List;

/**
 * 课程学生封装类，与Android教师端对应
 * 
 * @author anLA7856
 *
 */
public class CourseStudentListInfo {
	private Course course;
	private List<StudentInfo> list;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<StudentInfo> getList() {
		return list;
	}

	public void setList(List<StudentInfo> list) {
		this.list = list;
	}

	public CourseStudentListInfo(Course course, List<StudentInfo> list) {
		super();
		this.course = course;
		this.list = list;
	}

	public CourseStudentListInfo() {
		super();
	}

}
