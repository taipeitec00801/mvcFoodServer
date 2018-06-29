package init.JDBC.Model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Entity;

/*
Member 欄位說明:
    memberId      	: 會員編號			INT				主鍵, AUTO_INCREMENT
    userAccount		: 帳號			VARCHAR(40)		NOT NULL, 信箱
    userPassword	: 密碼			VARCHAR(12)		NOT NULL
    nickname		: 暱稱			VARCHAR(20)		預設   UserAccount "@"前字串
    birthday		: 生日			DATE			NOT NULL
    gender		 	: 性別			TINYINT			NOT NULL, (0~1), 0=女, 1=男
    userRank		: 會員級別			TINYINT			(1~9) 預設 1, root=0
    portrait		: 頭像			MEDIUMBLOB
    preference		: 喜好種類			VARCHAR(20)		紀錄 SortNumber
    collection		: 收藏			VARCHAR(1000)	紀錄 StoreId
    userGift		: 禮物			VARCHAR(1000)	紀錄 GiftId
    userFriends		: 追蹤			VARCHAR(1000)	紀錄 MemberId
*/

@Entity
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer memberId;
	private String userAccount;
	private String userPassword;
	private String nickName;
	private Date birthday;
	private Integer gender;
	private Integer userRank;
	private Blob portrait;
	private String preference;
	private String collection;
	private String userGift;
	private String userFriends;

	public Member() {
		super();
	}

	// ---------------getter--setter-------------------------------------
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getNickname() {
		return nickName;
	}

	public void setNickname(String nickName) {
		this.nickName = nickName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getUserRank() {
		return userRank;
	}

	public void setUserRank(Integer userRank) {
		this.userRank = userRank;
	}

	public Blob getPortrait() {
		return portrait;
	}

	public void setPortrait(Blob portrait) {
		this.portrait = portrait;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getUserGift() {
		return userGift;
	}

	public void setUserGift(String userGift) {
		this.userGift = userGift;
	}

	public String getUserFriends() {
		return userFriends;
	}

	public void setUserFriends(String userFriends) {
		this.userFriends = userFriends;
	}
}
