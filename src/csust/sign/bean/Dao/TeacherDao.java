package csust.sign.bean.Dao;

import java.util.List;

import csust.sign.bean.SignInfo;
import csust.sign.bean.Teacher;

public interface TeacherDao {
	/**
	 * 通过教师的用户名来获得密码
	 * @param teacher_username
	 * @return
	 */
	public List<Teacher> getPasswordByTeacherNum(String teacher_username);
	
	
	/**
	 * 通过teacherid来获得他的正在签到的课程
	 * @param teacherID
	 * @return
	 */
	public List<SignInfo> getTeacherSignInfoByTeacherID(String teacherID,String startCount);

	
	/**
	 * 用于添加教师的用户名密码
	 * @param name
	 * @param username
	 * @param password
	 * @param wifiMac
	 * @return
	 */
	public int addTeacherUserInfo(String name,String username,String password,String wifiMac);

	/**
	 * 用来鉴定此用户名是否可用。
	 * @param username
	 * @return
	 */
	public int validateNewUserInfo(String username);
	
	
	/**
	 * 用于更新教师的wifimac地址
	 * @param teacher_username
	 * @param wifiMac
	 * @return
	 */
	public int updateWifiMacAddress(String teacher_username,String wifiMac);
}
