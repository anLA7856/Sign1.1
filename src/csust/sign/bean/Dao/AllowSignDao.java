package csust.sign.bean.Dao;

import java.util.List;

import csust.sign.bean.Score;

public interface AllowSignDao {
	
	/**
	 * 用于添加签到信息
	 * @param courseId
	 * @param date
	 * @return
	 */
	public int addAllowSignInfo(String courseId,String date);
	
	/**
	 * 用于关闭签到状态
	 * @param allow_sign_id
	 * @return
	 */
	public int closeSignInfo(String allow_sign_id);
	
	/**
	 * 获得某一门课所有签到的数目
	 * @param course_id
	 * @return
	 */
	public int getAllSignCountByCourseId(String course_id);
	
	
	/**
	 * 用于获得某一门课程的所有签到，并且返回一个list数组，
	 * @param course_id
	 * @return 返回包括allowsignid和已签到数目。
	 */
	public List<Score> getAllSignListByCourseId(String course_id);
	
	
}
