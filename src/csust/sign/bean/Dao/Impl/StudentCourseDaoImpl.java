package csust.sign.bean.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import csust.sign.bean.Course;
import csust.sign.bean.CourseStudentListInfo;
import csust.sign.bean.StudentInfo;
import csust.sign.bean.StudentReportInfo;
import csust.sign.bean.Dao.StudentCourseDao;
import csust.sign.utils.ConnectFactory;

/**
 * 
 * @author anLA7856
 *
 */
public class StudentCourseDaoImpl implements StudentCourseDao {

	@Override
	public int getOneCourseCount(String allow_sign_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		// 先暂时用来测试
		String sql = "SELECT COUNT(*) AS mycount FROM student_course WHERE course_id = (SELECT allow_sign.`course_id` FROM allow_sign WHERE allow_sign.`alow_sign_id` = "
				+ allow_sign_id + ");";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while (rs.next()) {
				result = rs.getInt("mycount");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return result;
	}

	@Override
	public int getAllStudentsByCourseId(String course_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		// 先暂时用来测试
		String sql = "SELECT COUNT(*) AS mycount FROM student_course WHERE course_id="
				+ course_id + ";";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while (rs.next()) {
				result = rs.getInt("mycount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return result;
	}

	@Override
	public int studentAddCourse(String course_id, String student_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		// 先暂时用来测试
		String sql = "INSERT INTO student_course(student_id,course_id) VALUES("
				+ student_id + "," + course_id + ");";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			result = pstam.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return result;
	}

	@Override
	public int studentDeleteOneCourse(String student_id, String course_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		// 先暂时用来测试
		String sql = "DELETE FROM student_course WHERE student_id="
				+ student_id + " AND course_id=" + course_id + ";";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			result = pstam.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return result;
	}

	@Override
	public List<StudentReportInfo> getStudentListForReport(String course_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<StudentReportInfo> list = new ArrayList<StudentReportInfo>();
		// 先暂时用来测试
		String sql = "SELECT student.`student_id`,student.`student_name`,student.`student_username` FROM student_course,student WHERE student_course.`student_id`=student.`student_id` AND course_id="
				+ course_id + " ORDER BY student.`student_id` ASC;";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while (rs.next()) {
				StudentReportInfo student = new StudentReportInfo();
				student.setStudent_id(rs.getInt("student_id"));
				student.setStudent_name(rs.getString("student_name"));
				student.setStudent_username(rs.getString("student_username"));
				list.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
	}

	@Override
	public List<CourseStudentListInfo> getAllStudentsListByTeacherId(
			String teacherId) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = "SELECT student.`student_id`,student.`student_name`,student.`student_num`,student.`student_sex`,student.`student_username` "
				+ "FROM student,student_course,course "
				+ "WHERE student.`student_id`=student_course.`student_id`"
				+ " AND student_course.`course_id`=course.`course_id`"
				+ " AND course.`course_id`=?;";
		List<CourseStudentListInfo> list = new ArrayList<CourseStudentListInfo>();
		List<Course> listCourse = new CourseDaoImpl().getCoursesByTeacherNum(
				teacherId, 1 + "", 0, 1 + "");
		List<StudentInfo> listStudent = null;
		// 先暂时用来测试,这段代码即将写的很烂。
		conn = ConnectFactory.getConnection();

		for (int i = 0; i < listCourse.size(); i++) {
			CourseStudentListInfo csl = new CourseStudentListInfo();
			csl.setCourse(listCourse.get(i));
			try {
				pstam = conn.prepareStatement(sql);
				pstam.setInt(1, listCourse.get(i).getCourse_id());
				rs = pstam.executeQuery();
				listStudent = new ArrayList<StudentInfo>();
				while (rs.next()) {
					StudentInfo student = new StudentInfo();
					student.setStudent_id(rs.getInt("student_id"));
					student.setStudent_name(rs.getString("student_name"));
					student.setStudent_num(rs.getString("student_num"));
					student.setStudent_sex(rs.getString("student_sex"));
					student.setStudent_username(rs
							.getString("student_username"));
					listStudent.add(student);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//
			}
			csl.setList(listStudent);
			list.add(csl);
		}

		ConnectFactory.close(pstam, rs, conn);
		return list;
	}

}
