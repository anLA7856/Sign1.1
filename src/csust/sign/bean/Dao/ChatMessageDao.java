package csust.sign.bean.Dao;

import csust.sign.bean.ChatMessage;

public interface ChatMessageDao {
	/**
	 * 用于向数据库中插入一条聊天信息
	 * 仅用于文字信息。
	 * @param message
	 * @return
	 */
	public int addNewMessage(ChatMessage message);
}
