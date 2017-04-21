package csust.sign.bean.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import csust.sign.bean.Course;
import csust.sign.bean.CourseInfo;
import csust.sign.bean.SearchCourseInfo;
import csust.sign.bean.Dao.CourseDao;
import csust.sign.utils.ConnectFactory;

public class CourseDaoImpl implements CourseDao{


	@Override
	public List<Course> getCoursesByTeacherNum(String teacher_id,String startCount,int target,String count) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<Course> list = new ArrayList<Course>();
		//先暂时用来测试
		String sql = null;
		if(target == 1){
			//用于教师查看自己课程
			//直接一次性全部显示出来吧
			sql="SELECT * FROM course WHERE teacher_id="+teacher_id+" LIMIT "+startCount+","+count+";";
//			sql="SELECT * FROM course WHERE teacher_id="+teacher_id+";";

		}else if(target == 0){
			//用于在spinner中展示
			sql="SELECT * FROM course WHERE teacher_id="+teacher_id+";";
		}

		
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while(rs.next()){
				Course c = new Course();
				c.setCourse_id(rs.getInt("course_id"));
				c.setCourse_name(rs.getString("course_name"));
				c.setTeacher_username(rs.getInt("teacher_id")+"");
				
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
	}

	@Override
	public List<CourseInfo> getCoursesByStudentNum(String student_username,String startCount,String count) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<CourseInfo> list = new ArrayList<CourseInfo>();
		//先暂时用来测试
		String sql="SELECT course.`course_id`,teacher.`teacher_username`,teacher.`teacher_name`,course.`course_name`FROM teacher,course,student_course,student WHERE teacher.`teacher_id`=course.`teacher_id` AND student_course.`student_id`=student.`student_id` AND student.`student_username`='"+student_username+"' AND student_course.`course_id`=course.`course_id` ORDER BY course.`course_id` ASC LIMIT "+startCount+","+count+";";
		try {
			System.out.println(sql);
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(1));
				CourseInfo c = new CourseInfo();
				c.setCourse_id(rs.getInt("course_id")+"");
				c.setCourseName(rs.getString("course_name"));
				c.setTeacherName(rs.getString("teacher_name"));
				c.setTeacherNum(rs.getString("teacher_username"));
				list.add(c);
			}
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally{
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
	}

	@Override
	public int addCourse(String teacher_id, String course_name,
			String description) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		//先暂时用来测试
		String sql="INSERT INTO course (course_name,teacher_id,course_description) VALUES('"+course_name+"',"+teacher_id+",'"+description+"');";
		try {
			System.out.println(sql);
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			result = pstam.executeUpdate();
			
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally{
			ConnectFactory.close(pstam, rs, conn);
		}
		return result;
	}

	@Override
	public int teaDeleteCourse(String course_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		//先暂时用来测试
		String sql="DELETE FROM course WHERE course_id = "+course_id+";";
		try {
			System.out.println(sql);
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			result = pstam.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ConnectFactory.close(pstam, rs, conn);
		}
		return result;
	}

	@Override
	public List<SearchCourseInfo> getSearchCourseInfoByTeacherId(
			String teacher_id,String student_id,String start,String count) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<SearchCourseInfo> list = new ArrayList<SearchCourseInfo>();
		//先暂时用来测试
		String sql="SELECT course.`course_id`,course.`course_name`,course.`teacher_id`,teacher.`teacher_name` FROM course,teacher WHERE course.`teacher_id` = teacher.`teacher_id` AND teacher.`teacher_id`='"+teacher_id+"' AND course.`course_id` NOT IN (SELECT course_id FROM student_course WHERE student_id="+student_id+") LIMIT "+start+","+count+";";
		try {
			System.out.println(sql);
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while(rs.next()){
				SearchCourseInfo c = new SearchCourseInfo();
				
				c.setCourse_id(rs.getInt("course_id"));
				c.setCourse_name(rs.getString("course_name"));
				c.setTeacher_id(rs.getInt("teacher_id"));
				c.setTeacher_name(rs.getString("teacher_name"));
				
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
	}

	@Override
	public List<SearchCourseInfo> getSearchCourseInfoByCourseId(String course_id,String student_id,String start,String count) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<SearchCourseInfo> list = new ArrayList<SearchCourseInfo>();
		//先暂时用来测试
		String sql="SELECT course.`course_id`,course.`course_name`,course.`teacher_id`,teacher.`teacher_name` FROM course,teacher WHERE course.`teacher_id` = teacher.`teacher_id` AND course.`course_id` LIKE '%"+course_id+"%' AND course.`course_id` NOT IN (SELECT course_id FROM student_course WHERE student_id="+student_id+") LIMIT "+start+","+count+"";
		try {
			System.out.println(sql);
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while(rs.next()){
				SearchCourseInfo c = new SearchCourseInfo();
				
				c.setCourse_id(rs.getInt("course_id"));
				c.setCourse_name(rs.getString("course_name"));
				c.setTeacher_id(rs.getInt("teacher_id"));
				c.setTeacher_name(rs.getString("teacher_name"));
				
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
	}
	
	

}
