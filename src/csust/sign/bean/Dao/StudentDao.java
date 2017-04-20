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
	
}
