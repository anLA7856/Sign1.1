package csust.sign.bean.Dao;

import java.util.List;

import csust.sign.bean.SignInfo;
import csust.sign.bean.SignNameInfo;
import csust.sign.bean.StudentSignDetail;
import csust.sign.bean.StudentSignRate;


/**
 * 就是signname的dao
 * @author U-anLA
 *
 */
public interface SignDao {
	/**
	 * 用于获取学生未签到的信息。
	 * @return
	 */
	public List<SignInfo> getNotSignInfoByStuId(String student_id);
	
	/**
	 * 用于向数据库中插入一条记录
	 * @param student_id
	 * @param course_id
	 * @return
	 */
	public int addStuentSignInfo(String sign_time,String sign_state,String student_id,String course_id,String allow_id);

	/**
	 * 用来检测是否本次签到自己已经签了还是未签到
	 * @param allow_sign_id
	 * @param student_id
	 * @return
	 */
	public int vertifyIfCanSign(String allow_sign_id,String student_id);
	
	/**
	 * 用于获得某一门课程的实时签到记录
	 * @param allow_sign_id
	 * @return
	 */
	public List<SignNameInfo> getRealTimeSignNameInfo(String allow_sign_id);
	
	
	
	/**
	 * 用于获得某一次签到已经签到的学生数目,实时签到记录
	 * @param allow_sign_id
	 * @return
	 */
	public int getRealCountInOneSign(String allow_sign_id);
	
	/**
	 * 用于修改某一位学生某一次的签到状态。
	 * @param allow_sign_id
	 * @param student_id
	 * @param result
	 * @return
	 */
	public int UpdateOneSignStateInfo(String allow_sign_id,String student_id,String result);



	/**
	 * 获得某一门课所有的已签到的记录
	 * @param course_id
	 * @return
	 */
	public int getAllSignNameCountByCourseId(String course_id);
	
	
	/**
	 * 用于获得一门课的所有学生的签到记录。以及签到率。
	 * @param course_id
	 * @return
	 */
	public List<StudentSignRate> getAllStudentSignRate(String course_id);
	
	
	/**
	 * 获得某一位同学的特定课程的已签到数目
	 * @param student_id
	 * @param course_id
	 * @return
	 */
	public int getCountOfSignNameByStudentIdCourseId(String student_id,String course_id);

	/**
	 * 用于获得某一门课的自己所有签到详细列表。
	 * @param student_id
	 * @param course_id
	 * @return
	 */
	public List<StudentSignDetail> getSignListByCourseIdAndTeacherId(String student_id,String course_id);
}





