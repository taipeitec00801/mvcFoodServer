package other.Model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import member.Model.Member;

/*	
Message 欄位說明:
    messageId      : 留言編號			INT				主鍵, AUTO_INCREMENT
    messageDate    : 留言發佈時間		DATETIME		NOT NULL
    msgContent     : 留言內容			VARCHAR(40)		限制字數 40 NOT NULL
    msgCId     	   : 評論編號			INT				外來鍵
    msgMId 	       : 會員編號			INT				外來鍵
*/

@Entity
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer messageId;
	private Timestamp messageDate;
	private String msgContent;
	private StoreComment msgCId;
	private Member msgMId;

	public Message() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	@Column(columnDefinition = "DATETIME NOT NULL")
	public Timestamp getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Timestamp messageDate) {
		this.messageDate = messageDate;
	}

	@Column(columnDefinition = "VARCHAR(40)")
	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "msgCId")
	public StoreComment getMsgCId() {
		return msgCId;
	}

	public void setMsgCId(StoreComment msgCId) {
		this.msgCId = msgCId;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "msgMId")
	public Member getMsgMId() {
		return msgMId;
	}

	public void setMsgMId(Member msgMId) {
		this.msgMId = msgMId;
	}

}
