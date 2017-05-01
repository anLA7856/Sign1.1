package csust.sign.bean.Dao;

import java.util.List;

import csust.sign.bean.StudentInfo;
import csust.sign.bean.StudentReportInfo;

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
	
	/**
	 * 用于学生端删除一门课程
	 * @param student_id
	 * @param course_id
	 * @return
	 */
	public int studentDeleteOneCourse(String student_id,String course_id);
	
	/**
	 * 用于生成报表时候，获得的所有学生信息。
	 * @param course_id
	 * @return
	 */
	public List<StudentReportInfo> getStudentListForReport(String course_id);
	
	/**
	 * 获得某一门课程下面的所有学生列表。
	 * @param courseId
	 * @return
	 */
	public List<StudentInfo> getAllStudentsListByCourseId(String courseId);
}
