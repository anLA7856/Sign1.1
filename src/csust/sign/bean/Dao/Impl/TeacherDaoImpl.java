package csust.sign.bean.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import csust.sign.bean.SignInfo;
import csust.sign.bean.StudentInfo;
import csust.sign.bean.Teacher;
import csust.sign.bean.TeacherListInfo;
import csust.sign.bean.Dao.TeacherDao;
import csust.sign.utils.ConnectFactory;

public class TeacherDaoImpl implements TeacherDao{


	
	@Override
	public List<Teacher> getPasswordByTeacherNum(String teacher_username) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<Teacher> list = new ArrayList<Teacher>();
		//先暂时用来测试
		String sql="SELECT * FROM teacher WHERE teacher_username='"+teacher_username+"';";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while(rs.next()){
				Teacher t = new Teacher();
				t.setTeacher_id(rs.getInt("teacher_id"));
				t.setTeacher_name(rs.getString("teacher_name"));
				t.setTeacher_username(rs.getString("teacher_username"));
				t.setTeacher_password(rs.getString("teacher_password"));
				t.setTeacher_wifimac(rs.getString("teacher_wifimac"));
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
		
	}

	@Override
	public List<SignInfo> getTeacherSignInfoByTeacherID(String teacherID,String startCount,String count) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		
		List<SignInfo> list = new ArrayList<SignInfo>();
		//先暂时用来测试
		String sql="SELECT allow_sign.`alow_sign_id`,course.`course_name`,allow_sign.`sign_time`,course.`course_id`,teacher.`teacher_name`,allow_sign.`target` FROM course,teacher,allow_sign WHERE course.`course_id`=allow_sign.`course_id` AND teacher.`teacher_id`="+teacherID+" AND course.`teacher_id`=teacher.`teacher_id` AND allow_sign.`target`=1 LIMIT "+startCount+","+count+";";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while(rs.next()){
				SignInfo t = new SignInfo();
				t.setSign_courseName(rs.getString("course_name"));
				t.setSign_courseNum(rs.getString("course_id"));
				//修改date
				String inDate = rs.getString("sign_time");
				String putDate = inDate.substring(0, inDate.length()-2);
				t.setSign_date(putDate);
				t.setSign_teacherName(rs.getString("teacher_name"));
				t.setAlow_sign_id(rs.getString("alow_sign_id"));
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
	}

	@Override
	public int addTeacherUserInfo(String name, String username,
			String password, String wifiMac) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		//先暂时用来测试
		String sql="INSERT INTO teacher(teacher_name,teacher_username,teacher_password,teacher_wifimac) VALUES('"+name+"','"+username+"','"+password+"','"+wifiMac+"');";
		try {
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
	public int validateNewUserInfo(String username) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		//先暂时用来测试
		String sql="SELECT COUNT(*) AS COUNT FROM teacher WHERE teacher_username='"+username+"';";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while(rs.next()){
				result = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ConnectFactory.close(pstam, rs, conn);
		}
		return result;
	}

	@Override
	public int updateWifiMacAddress(String teacher_username, String wifiMac) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		//先暂时用来测试
		String sql="UPDATE teacher SET teacher.`teacher_wifimac`='"+wifiMac+"' WHERE teacher.`teacher_username`='"+teacher_username+"';";
		try {
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
	public List<TeacherListInfo> getTeaListByStudentId(String student_id,String start,String count) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		
		List<TeacherListInfo> list = new ArrayList<TeacherListInfo>();
		//先暂时用来测试
		String sql="SELECT DISTINCT teacher.`teacher_id`,teacher.`teacher_username`,teacher.`teacher_name`FROM teacher,course,student_course WHERE teacher.`teacher_id`=course.`teacher_id` AND course.`course_id`=student_course.`course_id` AND student_course.`student_id`="+student_id+" LIMIT "+start+","+count+";";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while(rs.next()){
				TeacherListInfo t = new TeacherListInfo();
				t.setTeacher_id(rs.getInt("teacher_id"));
				t.setTeacher_name(rs.getString("teacher_name"));
				t.setTeacher_username(rs.getString("teacher_username"));
				
				
				

				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
	}

	@Override
	public int modifyTeacherPassword(String teacher_id, String mynew) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		//先暂时用来测试
		String sql="UPDATE teacher SET teacher_password='"+mynew+"' WHERE teacher_id="+teacher_id+";";
		try {
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
	
}
