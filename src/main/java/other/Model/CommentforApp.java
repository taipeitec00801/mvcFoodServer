package other.Model;

import java.io.Serializable;

public class CommentforApp implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userNickName;
	private String Comment;
	private String commentRecomCount;
	private String MsgCid; //留言評論編號
	
	

	public CommentforApp(String userNickName, String comment, String commentRecomCount, String msgCid) {
		super();
		this.userNickName = userNickName;
		Comment = comment;
		this.commentRecomCount = commentRecomCount;
		MsgCid = msgCid;
	}

	public CommentforApp() {
		super();
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public String getCommentRecomCount() {
		return commentRecomCount;
	}

	public void setCommentRecomCount(String commentRecomCount) {
		this.commentRecomCount = commentRecomCount;
	}

	public String getMsgCid() {
		return MsgCid;
	}

	public void setMsgCid(String msgCid) {
		MsgCid = msgCid;
	}
	
}


