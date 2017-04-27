package csust.sign.bean.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import csust.sign.bean.ChatMessage;
import csust.sign.bean.Dao.ChatMessageDao;
import csust.sign.utils.ConnectFactory;

public class ChatMessageDaoImpl implements ChatMessageDao{

	@Override
	public int addNewMessage(ChatMessage message) {
		PreparedStatement pstam = null;
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		//先暂时用来测试
		String sql="INSERT INTO chat_message(sender_id,receiver_id,chat_time,message) VALUES(?,?,NOW(),?);";
		try {
			conn = ConnectFactory.getConnection();
			pstam = conn.prepareStatement(sql);
			pstam.setInt(1, message.getSenderId());
			pstam.setInt(2, message.getReceiveId());
			pstam.setString(3, message.getMessage());
			result = pstam.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ConnectFactory.close(pstam, rs, conn);
		}
		return result;
	}
	
}
