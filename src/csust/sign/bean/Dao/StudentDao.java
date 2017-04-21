package csust.sign.bean.Dao;

import java.util.List;

import csust.sign.bean.StudentInfo;

public interface StudentDao {
	/**
	 * 通过用户名来获得密码
	 * @param username
	 * @return
	 */
	public List<StudentInfo> getStuPassWordByStuUsername(String username);
	
	
	/**
	 * 用来验证用户名是否存在
	 * @param username
	 * @return
	 */
	public int validataUsername(String username);
	
	/**
	 * 用于注册学生账号
	 * @param studentInfo
	 * @return
	 */
	public int addStudent(StudentInfo studentInfo);
	
	/**
	 * 用于学生端更改自己的用户密码
	 * @param student_id
	 * @param newPassword
	 * @return
	 */
	public int modifyStudentPassword(String student_id,String newPassword);
	
	/**
	 * 用于获得某一次签到中未签到的学生list
	 * @param allow_sign_id
	 * @return
	 */
	public List<StudentInfo> getUnsignedStudentsByAllowSignId(String allow_sign_id);
	
	
	/**
	 * 由学生用户名获得学生id
	 * @param student_username
	 * @return
	 */
	public String getStudentIdByStudentUsername(String student_username);
	
}
