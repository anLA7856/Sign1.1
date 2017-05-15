package csust.sign.bean.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import csust.sign.bean.ChatMessage;
import csust.sign.bean.Dao.ChatMessageDao;
import csust.sign.utils.ConnectFactory;

/**
 * 
 * @author anLA7856
 *
 */
public class ChatMessageDaoImpl implements ChatMessageDao {

	@Override
	public int addNewMessage(ChatMessage message) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		// 先暂时用来测试
		String sql = "INSERT INTO chat_message(sender_id,receiver_id,chat_time,message) VALUES(?,?,NOW(),?);";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			pstam.setInt(1, message.getSenderId());
			pstam.setInt(2, message.getReceiveId());
			pstam.setString(3, message.getMessage());
			result = pstam.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			// conn.rollback();
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return result;
	}

	@Override
	public List<ChatMessage> getAllMessages(int receiveId) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<ChatMessage> list = new ArrayList<ChatMessage>();
		// 先暂时用来测试
		String sql = "SELECT * FROM chat_message WHERE sender_id=? OR receiver_id =?;";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			pstam.setInt(1, receiveId);
			pstam.setInt(2, receiveId);

			rs = pstam.executeQuery();
			while (rs.next()) {
				ChatMessage c = new ChatMessage();
				c.setChatTime(rs.getDate("chat_time").getTime() + "");
				c.setId(rs.getInt("id"));
				c.setMessage(rs.getString("message"));
				c.setNotRead(rs.getInt("not_read"));
				c.setReceiveId(rs.getInt("receiver_id"));
				c.setSenderId(rs.getInt("sender_id"));
				if (rs.getInt("receiver_id") == receiveId) {
					c.setIsCome(1 + "");
				} else {
					c.setIsCome(0 + "");
				}
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
	public List<ChatMessage> getNotReadMessages(int receiveId) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		List<ChatMessage> list = new ArrayList<ChatMessage>();
		// 先暂时用来测试
		String sql = "SELECT * FROM chat_message WHERE receiver_id = ? AND not_read = 1;";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			pstam.setInt(1, receiveId);
			rs = pstam.executeQuery();
			while (rs.next()) {
				ChatMessage c = new ChatMessage();
				c.setChatTime(rs.getTimestamp("chat_time").getTime() + "");

				c.setId(rs.getInt("id"));
				c.setMessage(rs.getString("message"));
				c.setNotRead(rs.getInt("not_read"));
				c.setReceiveId(rs.getInt("receiver_id"));
				c.setSenderId(rs.getInt("sender_id"));
				if (rs.getInt("receiver_id") == receiveId) {
					c.setIsCome(1 + "");
				} else {
					c.setIsCome(0 + "");
				}
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
	public int modifyMessageState(int... id) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		// 先暂时用来测试
		StringBuffer sql = new StringBuffer(
				"UPDATE chat_message SET not_read=0 WHERE id = " + id[0]);
		for (int i = 1; i < id.length; i++) {
			sql.append(" OR id=" + id[i]);
		}
		sql.append(";");
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql.toString());
			result = pstam.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// conn.rollback();
		} finally {
			ConnectFactory.close(pstam, rs, conn);
		}
		return result;
	}

}
