package csust.sign.bean.Dao;

public interface StudentCourseDao {

	/**
	 * 用于获得某一次签到的课程内的全部学生
	 * @param allow_sign_id
	 * @return
	 */
	public int getOneCourseCount(String allow_sign_id);
	
	
	/**
	 * 用于获得某一门课所有的学生数目
	 * @param course_id
	 * @return
	 */
	public int getAllStudentsByCourseId(String course_id);
	
	/**
	 * 用于学生端来添加一门课程。
	 * @param course_id
	 * @param student_id
	 * @return
	 */
	public int studentAddCourse(String course_id,String student_id);
}
