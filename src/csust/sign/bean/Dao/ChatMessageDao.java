package csust.sign.bean.Dao;

import java.util.List;

import csust.sign.bean.ChatMessage;

/**
 * 
 * @author anLA7856
 *
 */
public interface ChatMessageDao {
	/**
	 * 用于向数据库中插入一条聊天信息 仅用于文字信息。
	 * 
	 * @param message
	 * @return
	 */
	public int addNewMessage(ChatMessage message);

	/**
	 * 头一次登录，通过接收id，来获得所有与该学生 聊过天的数据库中已有的聊天记录。
	 * 
	 * @param studentId
	 * @return
	 */
	public List<ChatMessage> getAllMessages(int receiveId);

	/**
	 * 来获得未读的信息。
	 * 
	 * @param studentId
	 * @return
	 */
	public List<ChatMessage> getNotReadMessages(int receiveId);

	/**
	 * 来修改消息的状态，将原来的未读状态，更改为已读状态。
	 * 
	 * @param id
	 * @return
	 */
	public int modifyMessageState(int... id);
}
