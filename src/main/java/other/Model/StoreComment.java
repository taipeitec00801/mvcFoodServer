package other.Model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import member.Model.Member;

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
@Entity
public class StoreComment implements Serializable  {
	private static final long serialVersionUID = 1L;
	private Integer commentId;
	private Member commentMId;
	private Store commentSId;
	private Blob commentPicture;
	private Clob commentContent;
	private Integer commentAlterCount;
	private Integer commentRecomCount;
	private Timestamp commentDate;
	
	private MultipartFile  scImage;
	private String tempComment;
	
	
	
	public StoreComment(Clob commentContent) {
		super();
		this.commentContent = commentContent;
	}

	@Transient
	public String getTempComment() {
		return tempComment;
	}

	public void setTempComment(String tempComment) {
		this.tempComment = tempComment;
	}

	@Transient
	public MultipartFile getScImage() {
		return scImage;
	}

	public void setScImage(MultipartFile scImage) {
		this.scImage = scImage;
	}
	
	public StoreComment() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="commentMId")
	public Member getCommentMId() {
		return commentMId;
	}

	public void setCommentMId(Member commentMId) {
		this.commentMId = commentMId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="commentSId")
	public Store getCommentSId() {
		return commentSId;
	}

	public void setCommentSId(Store commentSId) {
		this.commentSId = commentSId;
	}
	
	@Column(columnDefinition = "LONGBLOB")
	public Blob getCommentPicture() {
		return commentPicture;
	}

	public void setCommentPicture(Blob commentPicture) {
		this.commentPicture = commentPicture;
	}
	@Column(columnDefinition = "TEXT NOT NULL")
	public Clob getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(Clob commentContent) {
		this.commentContent = commentContent;
	}

	@Column(columnDefinition = "INT DEFAULT '0'")
	public Integer getCommentAlterCount() {
		return commentAlterCount;
	}

	public void setCommentAlterCount(Integer commentAlterCount) {
		this.commentAlterCount = commentAlterCount;
	}
	
	@Column(columnDefinition = "INT DEFAULT '0'")
	public Integer getCommentRecomCount() {
		return commentRecomCount;
	}

	public void setCommentRecomCount(Integer commentRecomCount) {
		this.commentRecomCount = commentRecomCount;
	}
	
	@Column(columnDefinition = "DATETIME NOT NULL")
	public Timestamp getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}
	
	
	
	
}
