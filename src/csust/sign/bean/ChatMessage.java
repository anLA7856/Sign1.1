package csust.sign.bean;

import java.sql.Date;

/**
 * 聊天实体类。
 * @author U-ANLA
 *
 */
public class ChatMessage {
	private Integer id;
	private Integer senderId;
	private Integer receiveId;
	private String message;
	private Date chatTime;
	private Integer notRead;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSenderId() {
		return senderId;
	}
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	public Integer getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(Integer receiveId) {
		this.receiveId = receiveId;
	}
	public Date getChatTime() {
		return chatTime;
	}
	public void setChatTime(Date chatTime) {
		this.chatTime = chatTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Integer getNotRead() {
		return notRead;
	}
	public void setNotRead(Integer notRead) {
		this.notRead = notRead;
	}
	@Override
	public String toString() {
		return "ChatMessage [id=" + id + ", senderId=" + senderId
				+ ", receiveId=" + receiveId + ", message=" + message
				+ ", chatTime=" + chatTime + ", notRead=" + notRead + "]";
	}
	
	
	
	
	
}
