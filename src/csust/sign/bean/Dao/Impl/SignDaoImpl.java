package csust.sign.bean.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import csust.sign.bean.SignInfo;
import csust.sign.bean.SignNameInfo;
import csust.sign.bean.SignReportInfo;
import csust.sign.bean.StudentSignDetail;
import csust.sign.bean.StudentSignRate;
import csust.sign.bean.Dao.SignDao;
import csust.sign.utils.ConnectFactory;

/**
 * 
 * @author anLA7856
 *
 */
public class SignDaoImpl implements SignDao {

	@Override
	public List<SignInfo> getNotSignInfoByStuId(String student_id,
			String start, String count) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<SignInfo> list = new ArrayList<SignInfo>();
		// 先暂时用来测试
		String sql = "SELECT allow_sign.`alow_sign_id`,teacher.`teacher_wifimac`,allow_sign.`sign_time`,course.`course_name`,course.`course_id`,teacher.`teacher_name` FROM allow_sign,course,teacher,student_course WHERE allow_sign.`course_id`=course.`course_id` AND course.`teacher_id`=teacher.`teacher_id` AND student_course.`course_id`=course.`course_id` AND student_course.`student_id`="
				+ student_id
				+ " AND allow_sign.`target`=1 LIMIT "
				+ start
				+ "," + count + ";";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while (rs.next()) {
				SignInfo c = new SignInfo();
				c.setSign_courseName(rs.getString("course_name"));
				c.setSign_courseNum(rs.getString("course_id"));
				String inDate = rs.getString("sign_time");
				String putDate = inDate.substring(0, inDate.length() - 2);
				c.setSign_date(putDate);
				c.setSign_teacherName(rs.getString("teacher_name"));
				c.setTeacher_wifimac(rs.getString("teacher_wifimac"));
				c.setAlow_sign_id(rs.getString("alow_sign_id"));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
	}

	@Override
	public int addStuentSignInfo(String sign_time, String sign_state,
			String student_id, String course_id, String allow_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		// 先暂时用来测试
		String sql = "INSERT INTO signname(sign_time,sign_state,course_id,student_id,allow_sign_id) VALUES('"
				+ sign_time
				+ "','"
				+ sign_state
				+ "',"
				+ course_id
				+ ","
				+ student_id + "," + allow_id + ");";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			result = pstam.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return result;
	}

	@Override
	public int vertifyIfCanSign(String allow_sign_id, String student_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		// 应为rs.next一定会有
		int result = 0;
		// 先暂时用来测试
		String sql = "SELECT COUNT(*) AS COUNT FROM signname WHERE allow_sign_id = "
				+ allow_sign_id + " AND student_id=" + student_id + ";";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while (rs.next()) {

				result = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return result;
	}

	@Override
	public List<SignNameInfo> getRealTimeSignNameInfo(String allow_sign_id,
			String start, String count) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<SignNameInfo> list = new ArrayList<SignNameInfo>();
		// 先暂时用来测试
		String sql = "SELECT student.`student_id`,signname.`sign_state`,student.`student_name`,signname.`sign_time` FROM signname,student WHERE allow_sign_id = "
				+ allow_sign_id
				+ " AND student.`student_id`=signname.`student_id` LIMIT "
				+ start + "," + count + ";";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while (rs.next()) {
				SignNameInfo c = new SignNameInfo();
				String t = rs.getString("sign_time");
				// 拆减date
				String putDate = t.substring(0, t.length() - 2);

				c.setSign_time(putDate);
				c.setStudentName(rs.getString("student_name"));
				c.setSign_state(rs.getString("sign_state"));
				c.setStudent_id(rs.getString("student_id"));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
	}

	@Override
	public int getRealCountInOneSign(String allow_sign_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		// 先暂时用来测试
		String sql = "SELECT COUNT(course_id) AS mycount FROM signname WHERE allow_sign_id = "
				+ allow_sign_id + " AND sign_state<>'他人代签';";
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
	public int UpdateOneSignStateInfo(String allow_sign_id, String student_id,
			String result) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int myresult = 0;
		// 先暂时用来测试
		String sql = "UPDATE signname SET sign_state = '" + result
				+ "' WHERE allow_sign_id = " + allow_sign_id
				+ " AND student_id = " + student_id + ";";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			myresult = pstam.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return myresult;
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return myresult;
	}

	@Override
	public int getAllSignNameCountByCourseId(String course_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		// 先暂时用来测试
		String sql = "SELECT COUNT(*) AS mycount FROM signname WHERE course_id = "
				+ course_id + " AND sign_state='已签到';";
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
	public List<StudentSignRate> getAllStudentSignRate(String course_id,
			String start, String count) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<StudentSignRate> list = new ArrayList<StudentSignRate>();
		// 先暂时用来测试
		String sql = "SELECT COUNT(signname.`sign_id`) AS mycount ,signname.`student_id`,student.`student_name`,signname.`course_id`,student.`student_username` FROM signname,student WHERE signname.`student_id`=student.`student_id` AND signname.`course_id`="
				+ course_id
				+ " AND (signname.`sign_state`='已签到' OR signname.`sign_state`='请假') GROUP BY signname.`student_id` LIMIT "
				+ start + "," + count + ";";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while (rs.next()) {
				StudentSignRate c = new StudentSignRate();

				c.setCourse_id(rs.getInt("course_id") + "");
				c.setHave_sign(rs.getInt("mycount"));
				c.setStudent_id(rs.getInt("student_id") + "");
				c.setStudent_name(rs.getString("student_name"));
				c.setStudent_username(rs.getString("student_username"));

				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
	}

	@Override
	public int getCountOfSignNameByStudentIdCourseId(String student_id,
			String course_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		// 先暂时用来测试
		String sql = "SELECT COUNT(*) AS mycount FROM signname WHERE student_id="
				+ student_id
				+ " AND course_id="
				+ course_id
				+ " AND sign_state='已签到';";
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
	public List<StudentSignDetail> getSignListByCourseIdAndTeacherId(
			String student_id, String course_id, String start, String count) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<StudentSignDetail> list = new ArrayList<StudentSignDetail>();
		// 先暂时用来测试
		String sql = "SELECT signname.`sign_time`,signname.`sign_state` FROM signname WHERE signname.`course_id`="
				+ course_id
				+ " AND signname.`student_id`="
				+ student_id
				+ " LIMIT " + start + "," + count + ";";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while (rs.next()) {
				StudentSignDetail c = new StudentSignDetail();

				c.setSign_date(rs.getString("sign_time"));
				c.setSign_state(rs.getString("sign_state"));

				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
	}

	@Override
	public int addNewSignInfo(String sign_state, String course_id,
			String student_id, String allow_sign_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		// 先暂时用来测试
		String sql = "insert into signname(sign_time,sign_state,course_id,student_id,allow_sign_id) values(now(),'"
				+ sign_state
				+ "',"
				+ course_id
				+ ","
				+ student_id
				+ ","
				+ allow_sign_id + ");";
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
	public List<SignReportInfo> getSignReportInfos(String course_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<SignReportInfo> list = new ArrayList<SignReportInfo>();
		// 先暂时用来测试
		String sql = "SELECT student.`student_id`,student.`student_name`,student.`student_username`,signname.`allow_sign_id`,signname.`sign_state` FROM student,signname WHERE student.`student_id`=signname.`student_id` AND student.`student_id` IN (SELECT student.`student_id` FROM student_course,student WHERE student_course.`student_id`=student.`student_id` AND course_id="
				+ course_id + ");";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while (rs.next()) {
				SignReportInfo c = new SignReportInfo();

				c.setAllow_sign_id(rs.getInt("allow_sign_id"));
				c.setSign_state(rs.getString("sign_state"));
				c.setStudent_id(rs.getInt("student_id"));
				c.setStudent_name(rs.getString("student_name"));
				c.setStudent_username(rs.getString("student_username"));

				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
	}

}
