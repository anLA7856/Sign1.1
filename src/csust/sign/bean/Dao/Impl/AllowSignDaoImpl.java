package csust.sign.bean.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import csust.sign.bean.AllowSignInfo;
import csust.sign.bean.Score;
import csust.sign.bean.Dao.AllowSignDao;
import csust.sign.utils.ConnectFactory;

/**
 * 这部分代码其实写的很烂
 * @author anLA7856
 *
 */
public class AllowSignDaoImpl implements AllowSignDao {

	@Override
	public int addAllowSignInfo(String courseId, String date) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		// 先暂时用来测试
		String sql = "INSERT INTO allow_sign(course_id,sign_time,target) VALUES('"
				+ courseId + "','" + date + "',1);";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			result = pstam.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return result;
	}

	@Override
	public int closeSignInfo(String allow_sign_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		// 先暂时用来测试
		String sql = "UPDATE allow_sign SET target = 0 WHERE allow_sign.`alow_sign_id` = "
				+ allow_sign_id + ";";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			result = pstam.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return result;
	}

	@Override
	public int getAllSignCountByCourseId(String course_id) {

		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		// 先暂时用来测试
		String sql = "SELECT COUNT(*) AS mycount FROM allow_sign WHERE course_id ="
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
	public List<Score> getAllSignListByCourseId(String course_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<Score> list = new ArrayList<Score>();
		// 先暂时用来测试
		String sql = "SELECT COUNT(signname.`sign_id`) AS mycount,allow_sign_id FROM signname WHERE EXISTS (SELECT alow_sign_id FROM allow_sign WHERE allow_sign.`course_id`="
				+ course_id
				+ " AND allow_sign.`course_id`=signname.`course_id`) GROUP BY allow_sign_id;";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while (rs.next()) {
				Score score = new Score();
				score.setAllow_sign_id(rs.getInt("allow_sign_id") + "");
				score.setScore(rs.getInt("mycount"));
				list.add(score);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
	}

	@Override
	public List<AllowSignInfo> getCourseSignInfoByCourseId(String course_id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<AllowSignInfo> list = new ArrayList<AllowSignInfo>();
		// 先暂时用来测试
		String sql = "SELECT allow_sign.`alow_sign_id`,allow_sign.`course_id`,allow_sign.`sign_time` FROM allow_sign WHERE course_id = "
				+ course_id + " ORDER BY allow_sign.`alow_sign_id` ASC;";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			rs = pstam.executeQuery();
			while (rs.next()) {
				AllowSignInfo sign = new AllowSignInfo();
				sign.setAllow_sign_id(rs.getInt("alow_sign_id"));
				sign.setCourse_id(rs.getInt("course_id"));
				String str = rs.getString("sign_time");
				String myStr = str.substring(0, str.length() - 2);
				sign.setSign_time(myStr);
				list.add(sign);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return list;
	}

}
