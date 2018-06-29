package init.JDBC.Utils;

public class DBService {
	public static final String host = "127.0.0.1";
	public static final String DB_MYSQL = "MYSQL";
	public static final String DB_TYPE = DB_MYSQL;

	private static final String DBURL_MySQL = "jdbc:mysql://" + host
			+ "/food?useUnicode=yes&characterEncoding=utf8&useSSL=true";
	public static final String USERID_MySQL = "root";
	public static final String PSWD_MySQL = "password";
	
//	DROP TABLE TableName
	private static final String DROP_Member_MySQL = "DROP Table IF EXISTS Member ";
	private static final String DROP_Store_MySQL = "DROP Table IF EXISTS Store ";
	private static final String DROP_StoreComment_MySQL = "DROP TABLE IF EXISTS StoreComment";
	private static final String DROP_Message_MySQL = "DROP Table IF EXISTS Message ";
	private static final String DROP_Gift_MySQL = "DROP TABLE IF EXISTS Gift";
	
//	CREATE TABLE TableName
	private static final String CREATE_Member_MySQL = " CREATE TABLE Member ( " 
			+ " MemberId 		INT AUTO_INCREMENT PRIMARY KEY, "
			+ " UserAccount		VARCHAR(40) NOT NULL UNIQUE, " 
			+ " UserPassword    VARCHAR(128) NOT NULL, "
			+ " Nickname		VARCHAR(20), " 
			+ " Birthday 		DATE NOT NULL, "
			+ " Gender 			TINYINT NOT NULL, " 
			+ " UserRank		TINYINT DEFAULT '1', " 
			+ " Portrait  		MEDIUMBLOB, "
			+ " Preference    	VARCHAR(20), "
			+ " Collection     	VARCHAR(1000), " 
			+ " UserGift  		VARCHAR(1000), "
			+ " UserFriends     VARCHAR(1000) "
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci";

	private static final String CREATE_Store_MySQL = " CREATE TABLE Store ( " 
			+ " StoreId				INT AUTO_INCREMENT PRIMARY KEY, "
			+ " StoreName			VARCHAR(40) NOT NULL, " 
			+ " StoreAddress    	VARCHAR(100) NOT NULL, "
			+ " StorePhone			VARCHAR(15), " 
			+ " ServiceHours 		VARCHAR(40), "
			+ " StorePicture 		VARCHAR(700), " 
			+ " SortNumber			TINYINT NOT NULL, " 
			+ " StoreRecomCount  	INT DEFAULT '0', "
			+ " StoreCommentCount	INT DEFAULT '0', "
			+ " Latitude			DOUBLE, "
			+ " Longitude			DOUBLE "
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci";

	private static final String CREATE_StoreComment_MySQL = "CREATE TABLE StoreComment ( " 
			+ " CommentId 			INT AUTO_INCREMENT PRIMARY KEY, "
			+ " CommentMId			INT, " 
			+ " CommentSId    		INT, "
			+ " CommentPicture		LONGBLOB, " 
			+ " CommentContent 		TEXT NOT NULL, "
			+ " CommentAlterCount 	INT DEFAULT '0', " 
			+ " CommentRecomCount	INT DEFAULT '0', " 
			+ " CommentDate  		DATETIME, "
			+ " FOREIGN KEY(CommentMId) REFERENCES Member(MemberId), "
			+ " FOREIGN KEY(CommentSId) REFERENCES Store(StoreId) "
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci";

	private static final String CREATE_Message_MySQL = "CREATE TABLE Message ( "
			+ " MessageId 		INT AUTO_INCREMENT PRIMARY KEY, "
			+ " MessageDate		DATETIME, " 
			+ " MsgContent    	VARCHAR(40), "
			+ " MsgCId			INT, " 
			+ " MsgMId 			INT, "
			+ " MsgUickName		VARCHAR(20), " 
			+ " FOREIGN KEY(MsgCId) REFERENCES StoreComment(CommentId), "
			+ " FOREIGN KEY(MsgMId) REFERENCES Member(MemberId) "
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci";
	
	private static final String CREATE_Gift_MySQL = " CREATE TABLE Gift ( "
			+ " GiftId 			INT AUTO_INCREMENT PRIMARY KEY, "
			+ " GiftName		VARCHAR(40) NOT NULL, " 
			+ " GiftPicture    	MEDIUMBLOB NOT NULL, " 
			+ " GiftContent  	VARCHAR(40) NOT NULL, "
			+ " GiftDeadline  	DATE NOT NULL "
			+ " ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci";

//=================Drop=========================================================================
	public static String getDropMember() {
		String drop = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			drop = DROP_Member_MySQL;
		}
		return drop;
	}
	
	public static String getDropStore() {
		String drop = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			drop = DROP_Store_MySQL;
		}
		return drop;
	}
	
	public static String getDropStoreComment() {
		String drop = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			drop = DROP_StoreComment_MySQL;
		}
		return drop;
	}
	
	public static String getDropMessage() {
		String drop = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			drop = DROP_Message_MySQL;
		}
		return drop;
	}

	public static String getDropGift() {
		String drop = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			drop = DROP_Gift_MySQL;
		}
		return drop;
	}
	
//=================Create=======================================================================
	public static String getCreateMember() {
		String create = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			create = CREATE_Member_MySQL;
		}
		return create;
	}
	
	public static String getCreateStore() {
		String create = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			create = CREATE_Store_MySQL;
		}
		return create;
	}

	public static String getCreateStoreComment() {
		String create = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			create = CREATE_StoreComment_MySQL;
		}
		return create;
	}	

	public static String getCreateMessage() {
		String create = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			create = CREATE_Message_MySQL;
		}
		return create;
	}

	public static String getCreateGift() {
		String create = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			create = CREATE_Gift_MySQL;
		}
		return create;
	}
//==============================================================================================
	public static String getDbUrl() {
		String url = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			url = DBURL_MySQL;
		}
		return url;
	}

	public static String getMySQLUser() {
		String user = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			user = USERID_MySQL;
		}
		return user;
	}

	public static String getMySQLPassword() {
		String pswd = null;
		if (DB_TYPE.equalsIgnoreCase(DB_MYSQL)) {
			pswd = PSWD_MySQL;
		}
		return pswd;
	}

}
