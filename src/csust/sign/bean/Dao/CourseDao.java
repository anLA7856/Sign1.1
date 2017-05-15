package csust.sign.bean.Dao;

import java.util.List;

import csust.sign.bean.Course;
import csust.sign.bean.CourseInfo;
import csust.sign.bean.SearchCourseInfo;

/**
 * 
 * @author anLA7856
 *
 */
public interface CourseDao {

	/**
	 * 通过教师的唯一登录名来获得所有与之相关的课程
	 * 
	 * @param teacher_username
	 * @return
	 */
	public List<Course> getCoursesByTeacherNum(String teacher_id,
			String startCount, int target, String count);

	/**
	 * 通过学生唯一登录名，来获得所有与之相关的课程
	 * 
	 * @param student_username
	 * @return
	 */
	public List<CourseInfo> getCoursesByStudentNum(String student_username,
			String startCount, String count);

	/**
	 * 用于教师向数据库中添加一门课程
	 * 
	 * @param teacher_id
	 * @param course_name
	 * @param description
	 * @return
	 */
	public int addCourse(String teacher_id, String course_name,
			String description);

	/**
	 * 用于教师向数据库来删除一门课程，是删除
	 * 
	 * @param course_id
	 * @return
	 */
	public int teaDeleteCourse(String course_id);

	/**
	 * 通过教师号来进行精确匹配搜索，并且过滤掉该学生已经添加了的课程
	 * 
	 * @param teacher_id
	 * @return
	 */
	public List<SearchCourseInfo> getSearchCourseInfoByTeacherId(
			String teacher_id, String student_id, String start, String count);

	/**
	 * 通过课程号来进行模糊匹配查询。并且过滤掉该学生已经添加了的课程
	 * 
	 * @param course_id
	 * @return
	 */
	public List<SearchCourseInfo> getSearchCourseInfoByCourseId(
			String course_id, String student_id, String start, String count);

}
