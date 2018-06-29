package init.JDBC.Model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;

/*
StoreComment 欄位說明:
	commentId    	  : 評論編號			INT				主鍵, AUTO_INCREMENT
	commentMId        : 會員編號			INT				外來鍵
    commentSId     	  : 店家編號			INT				外來鍵
    commentPicture    : 評論圖片			LONGBLOB        
    commentContent    : 評論內容			TEXT			NOT NULL
    commentAlterCount : 修改次數			INT				預設 0
    commentRecomCount : 推薦數			INT				預設 0
    commentDate       : 評論時間			DATETIME   		NOT NULL, NOW() 紀錄評論上傳的時間
    
*/

public class StoreComment implements Serializable  {
	private static final long serialVersionUID = 1L;
	private Integer commentId;
	private Integer commentMId;
	private Integer commentSId;
	private Blob commentPicture;
	private Clob commentContent;
	private Integer commentAlterCount;
	private Integer commentRecomCount;
	private Timestamp commentDate;
	
	public StoreComment() {
		super();
	}
	

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	
	public Integer getCommentMId() {
		return commentMId;
	}

	public void setCommentMId(Integer commentMId) {
		this.commentMId = commentMId;
	}
	
	public Integer getCommentSId() {
		return commentSId;
	}

	public void setCommentSId(Integer commentSId) {
		this.commentSId = commentSId;
	}
	
	public Blob getCommentPicture() {
		return commentPicture;
	}

	public void setCommentPicture(Blob commentPicture) {
		this.commentPicture = commentPicture;
	}

	public Clob getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(Clob commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getCommentAlterCount() {
		return commentAlterCount;
	}

	public void setCommentAlterCount(Integer commentAlterCount) {
		this.commentAlterCount = commentAlterCount;
	}

	public Integer getCommentRecomCount() {
		return commentRecomCount;
	}

	public void setCommentRecomCount(Integer commentRecomCount) {
		this.commentRecomCount = commentRecomCount;
	}

	public Timestamp getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}
	
	
	
	
}
