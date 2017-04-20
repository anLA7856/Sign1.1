package csust.sign.bean.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import csust.sign.bean.CourseInfo;
import csust.sign.bean.StudentInfo;
import csust.sign.bean.Dao.StudentDao;
import csust.sign.utils.ConnectFactory;

public class StudentDaoImpl implements StudentDao{


	
	@Override
	public List<StudentInfo> getStuPassWordByStuUsername(String username) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<StudentInfo> list = new ArrayList<StudentInfo>();
		//先暂时用来测试
		String sql="SELECT student.`student_id`,student.`student_name`,student.`student_sex`,student.`student_num`,student.`student_username`,student.`student_password` FROM student WHERE student.`student_username`='"+username+"';";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while(rs.next()){
				StudentInfo c = new StudentInfo();
				c.setStudent_id(rs.getInt("student_id"));
				c.setStudent_name(rs.getString("student_name"));
				c.setStudent_sex(rs.getString("student_sex"));
				c.setStudent_num(rs.getString("student_num"));
				c.setStudent_password(rs.getString("student_password"));
				c.setStudent_username(rs.getString("student_username"));
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
	public int validataUsername(String username) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		//先暂时用来测试
		String sql="SELECT COUNT(*) AS COUNT FROM student WHERE student_username='"+username+"';";
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
	public int addStudent(StudentInfo studentInfo) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		//先暂时用来测试
		String sql="INSERT INTO student(student_name,student_sex,student_num,student_username,student_password) VALUES('"+studentInfo.getStudent_name()+"','"+studentInfo.getStudent_sex()+"','"+studentInfo.getStudent_num()+"','"+studentInfo.getStudent_username()+"','"+studentInfo.getStudent_password()+"');";
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
