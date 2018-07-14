package init.JDBC.Model;

import java.io.Serializable;
import java.sql.Timestamp;

/*	
Message 欄位說明:
    messageId      : 留言編號			INT				主鍵, AUTO_INCREMENT
    messageDate    : 留言發佈時間		DATETIME		NOT NULL
    msgContent     : 留言內容			VARCHAR(40)		限制字數 40 NOT NULL
    msgCId     	   : 評論編號			INT				外來鍵
    msgMId 	       : 會員編號			INT				外來鍵
*/

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer messageId;
	private Timestamp messageDate;
	private String msgContent;
	private Integer msgCId;
	private Integer msgMId;

	public Message() {
		super();
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Timestamp getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Timestamp messageDate) {
		this.messageDate = messageDate;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Integer getMsgCId() {
		return msgCId;
	}

	public void setMsgCId(Integer msgCId) {
		this.msgCId = msgCId;
	}

	public Integer getMsgMId() {
		return msgMId;
	}

	public void setMsgMId(Integer msgMId) {
		this.msgMId = msgMId;
	}

}
