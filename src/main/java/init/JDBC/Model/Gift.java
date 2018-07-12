package init.JDBC.Model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

/*	
Gift 欄位說明:
    giftId      	 : 折價劵編號			INT				主鍵, AUTO_INCREMENT
    giftName         : 折價劵名稱			VARCHAR(40)		NOT NULL
    giftPicture      : 折價劵圖片			MEDIUMBLOB		NOT NULL
    giftContent      : 折價劵內容			VARCHAR(40)		NOT NULL
    giftPrice		 : 折價劵價格			DECIMAL(10,2)	NOT NULL
    giftDeadline 	 : 營業時間			DATE			NOT NULL
*/


public class Gift implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer giftId;
	private String giftName;
	private Blob giftPicture;
	private String giftContent;
	private Double giftPrice;
	private Date giftDeadline;

	public Gift() {
		super();
	}

	public Integer getGiftId() {
		return giftId;
	}

	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public Blob getGiftPicture() {
		return giftPicture;
	}

	public void setGiftPicture(Blob giftPicture) {
		this.giftPicture = giftPicture;
	}

	public String getGiftContent() {
		return giftContent;
	}

	public void setGiftContent(String giftContent) {
		this.giftContent = giftContent;
	}

	public Date getGiftDeadline() {
		return giftDeadline;
	}

	public void setGiftDeadline(Date giftDeadline) {
		this.giftDeadline = giftDeadline;
	}

	public Double getGiftPrice() {
		return giftPrice;
	}

	public void setGiftPrice(Double giftPrice) {
		this.giftPrice = giftPrice;
	}

}
