package init.JDBC.Model;

import java.io.Serializable;

/*	
Store 欄位說明:
    storeId      	  : 店家編號			INT				主鍵, AUTO_INCREMENT
    storeName         : 店家名稱			VARCHAR(40)		NOT NULL
    storeAddress      : 店家地址			VARCHAR(100)	NOT NULL
    storePhone     	  : 店家電話			VARCHAR(15)		格式-->02-1234-1234
    serviceHours 	  : 營業時間			VARCHAR(40)		格式-->00:00~00:00 & 00:00~00:00 或是-->00:00~00:00 (公休時間)
    storePicture  	  : 店家圖片(圖片名稱)	VARCHAR(700)	NOT NULL StoreId(000~999)+Picture(00~99) 格式-->000_00			
    sortNumber   	  : 分類編號       		TINYINT			NOT NULL, (0~9)
    storeRecomCount   : 推薦數			INT				預設 0
    storeCommentCount : 評論數			INT				預設 0
    latitude		  : 緯度				DOUBLE
    longitude		  : 經度				DOUBLE
*/

public class Store implements Serializable  {
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String storeName;
	private String storeAddress;
	private String storePhone;
	private String serviceHours;
	private String storePicture;
	private Integer sortNumber;
	private Integer storeRecomCount;
	private Integer storeCommentCount;
	private Double latitude;
	private Double longitude;

	public Store(Integer storeId, String storeName, String storeAddress, String storePhone, String serviceHours,
			String storePicture, Integer sortNumber, Integer storeRecomCount, Integer storeCommentCount) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.storePhone = storePhone;
		this.serviceHours = serviceHours;
		this.storePicture = storePicture;
		this.sortNumber = sortNumber;
		this.storeRecomCount = storeRecomCount;
		this.storeCommentCount = storeCommentCount;
	}

	public Store() {
		super();
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getServiceHours() {
		return serviceHours;
	}

	public void setServiceHours(String serviceHours) {
		this.serviceHours = serviceHours;
	}

	public String getStorePicture() {
		return storePicture;
	}

	public void setStorePicture(String storePicture) {
		this.storePicture = storePicture;
	}

	public Integer getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	public Integer getStoreRecomCount() {
		return storeRecomCount;
	}
				
	public void setStoreRecomCount(Integer storeRecomCount) {
		this.storeRecomCount = storeRecomCount;
	}

	public Integer getStoreCommentCount() {
		return storeCommentCount;
	}
	
	public void setStoreCommentCount(Integer storeCommentCount) {
		this.storeCommentCount = storeCommentCount;
	}
	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	
	
}
