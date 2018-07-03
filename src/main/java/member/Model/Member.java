package member.Model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private String nickname;
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

	public Member(String userAccount) {
		super();
		this.userAccount = userAccount;
	}

	public Member(String userAccount, String userPassword) {
		super();
		this.userAccount = userAccount;
		this.userPassword = userPassword;
	}

	public Member(Integer memberId, String userPassword, String nickname, String birthday, Integer gender,
			Integer userRank, String preference, String collection, String userGift, String userFriends) {
		super();
		this.userPassword = userPassword;
		this.nickname = nickname;
		this.birthday = Date.valueOf(birthday);
		this.gender = gender;
		this.memberId = memberId;
		this.userRank = userRank;
		this.preference = preference;
		this.collection = collection;
		this.userGift = userGift;
		this.userFriends = userFriends;
	}

	public Member(String userAccount, String userPassword, String nickname, String birthday, int gender, int memberId,
			int userRank, Blob portrait, String preference, String collection, String userGift, String userFriends) {
		super();
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.nickname = nickname;
		this.birthday = Date.valueOf(birthday);
		this.gender = gender;
		this.memberId = memberId;
		this.userRank = userRank;
		this.portrait = portrait;
		this.preference = preference;
		this.collection = collection;
		this.userGift = userGift;
		this.userFriends = userFriends;
	}

	// ---------------getter--setter-------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@Column(columnDefinition = "VARCHAR(40) NOT NULL UNIQUE")
	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	@Column(columnDefinition = "VARCHAR(32) NOT NULL")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(columnDefinition = "VARCHAR(20) NOT NULL")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(columnDefinition = "DATE NOT NULL")
	public String getBirthday() {
		return birthday.toString();
	}

	public void setBirthday(String birthday) {
		this.birthday = Date.valueOf(birthday);
	}

	@Column(columnDefinition = "TINYINT NOT NULL")
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Column(columnDefinition = "TINYINT DEFAULT '1'")
	public Integer getUserRank() {
		return userRank;
	}

	public void setUserRank(Integer userRank) {
		this.userRank = userRank;
	}

	@Column(columnDefinition = "MEDIUMBLOB")
	public Blob getPortrait() {
		return portrait;
	}

	public void setPortrait(Blob portrait) {
		this.portrait = portrait;
	}

	@Column(columnDefinition = "VARCHAR(20)")
	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	@Column(columnDefinition = "VARCHAR(1000)")
	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	@Column(columnDefinition = "VARCHAR(1000)")
	public String getUserGift() {
		return userGift;
	}

	public void setUserGift(String userGift) {
		this.userGift = userGift;
	}

	@Column(columnDefinition = "VARCHAR(1000)")
	public String getUserFriends() {
		return userFriends;
	}

	public void setUserFriends(String userFriends) {
		this.userFriends = userFriends;
	}
}
