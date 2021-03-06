package init.Hibernate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import init.Hibernate.Utils.HibernateUtil_MySQL;
import javaClass.GlobalService;
import member.Model.Member;
import other.Model.Message;
import other.Model.Store;
import other.Model.StoreComment;
import shoppingCart.Model.Gift;

public class TableDataReset_Hibernate {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元
		
	public static void main(String[] args) throws FileNotFoundException, IOException, SerialException, SQLException {
		System.out.println("Maven + Hibernate + MySQL 新增多筆資料到Food");
		Session session = HibernateUtil_MySQL.getSessionFactory().openSession();
		Transaction tx = null;
		// -------------Member資料，寫入資料庫-----------------------
		// Member 欄位說明: 
		// memberId : 		會員編號 	INT AUTO_INCREMENT		主鍵  
		// userAccount : 	帳號  		VARCHAR(40) NOT NULL	信箱 
		// userPassword : 	密碼 		VARCHAR(32) NOT NULL 
		// nickname : 		暱稱 		VARCHAR(20) 			預設 UserAccount "@"前字串 
		// birthday : 		生日 		DATE NOT NULL 
		// gender : 		性別 		TINYINT NOT NULL		(0~1), 0=女, 1=男 
		// userRank : 		會員級別 	TINYINT 				(1~9) 預設 1, root=0
		// portrait : 		頭像 		MEDIUMBLOB 
		// preference : 	喜好種類 	VARCHAR(20) 			紀錄 SortNumber
		// collection : 	收藏 		VARCHAR(1000) 			紀錄 StoreId 
		// userGift : 		禮物 		VARCHAR(1000) 			紀錄GiftId 
		// userFriends : 	追蹤 		VARCHAR(1000) 			紀錄 MemberId
		List<Member> listMember = new ArrayList<>();
		try (
				// Member.dat存放要新增的多筆資料
				InputStreamReader isr0 = new InputStreamReader(
						new FileInputStream("data/Member.dat"), "UTF-8");
				BufferedReader br = new BufferedReader(isr0);)
		// 由檔案("data/Member.dat")讀入Member的資料，然後寫入資料庫
		{
			String line = "";
			int count = 0;
			while ((line = br.readLine()) != null) {
				// 去除 UTF8_BOM
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				
				String[] sa = line.split("\\|");
				try {
					tx = session.beginTransaction();
					Member member = new Member();
					member.setUserAccount(sa[0].trim());
					
//					member.setUserPassword(sa[1].trim());
					// 若又進行編碼 需先至 member/Model/Member.java 中修改 欄位型態
					member.setUserPassword(GlobalService.getMD5Endocing(sa[1].trim()));

					member.setNickname(sa[2].trim());
					member.setBirthday(sa[3].trim());
					member.setGender(Integer.parseInt(sa[4].trim()));
					member.setUserRank(Integer.parseInt(sa[5].trim()));
					// --------------處理Blob(圖片)欄位----------------
					// Blob portrait = fileToBlob(sa[6]);
					// fileToBlob為自己編寫的方法,
					// 本程式後來改用 Hibernate 4.3 提供的標準API:
					// Hibernate.getLobCreator(session).createBlob(is, size)
					if (sa[6].trim().length() > 4) {
						File file = new File(sa[6].trim());
						System.out.println(sa[2].trim() + "的 Portrait Path : " + file.getAbsolutePath());
						long size = file.length();
						InputStream is = new FileInputStream(file);
						Blob portrait = Hibernate.getLobCreator(session).createBlob(is, size);
						member.setPortrait(portrait);
					}
					member.setPreference(sa[7].trim());
					member.setCollection(sa[8].trim());
					if (!sa[9].trim().equals(null) && sa[9].trim().length() != 4) {
						member.setUserIntro(sa[9].trim());
					}
					if (!sa[10].trim().equals(null) && sa[10].trim().length() != 4) {
						member.setUserFriends(sa[10].trim());
					}
					listMember.add(member);
					session.save(member);
					session.flush();
					tx.commit();
				} catch (Exception e) {
					e.getMessage();
					if (tx != null) {
						tx.rollback();
					}
				} finally {
					count++;
					System.out.println("新增 Member 資料， 第" + count + "筆記錄 : " + sa[0]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// -------------Store資料，寫入資料庫-----------------------		
		// Store 欄位說明: 
		// storeId 			  : 店家編號 			INT AUTO_INCREMENT 			主鍵 
		// storeName 		  : 店家名稱 			VARCHAR(40) NOT NULL 
		// storeAddress 	  : 店家地址 			VARCHAR(100) NOT NULL 
		// storePhone 		  : 店家電話 			VARCHAR(15) 				格式-->02-1234-1234 
		// serviceHours 	  : 營業時間 			VARCHAR(40)					格式-->00:00~00:00 & 00:00~00:00 或是-->00:00~00:00 (公休時間) 
		// storePicture 	  : 店家圖片(圖片名稱) 	VARCHAR(700) NOT NULL 		StoreId(000~999)+Picture(00~99) 格式-->000_00
		// sortNumber 		  : 分類編號 			TINYINT NOT NULL			(0~9) 
		// storeRecomCount 	  : 推薦數 			INT 						預設 0
		// storeCommentCount  : 評論數 			INT 						預設 0
		// latitude		  	  :	緯度				DOUBLE
		// longitude		  : 經度				DOUBLE
		
		// 定義儲存Store的List物件，當讀入每筆Store資料後，不立即
		// 寫入資料庫而是等到讀入StoreComment時，將對應的Store存入StoreComment，
		// 再寫入StoreComment(當然同時寫入Store)
		List<Store> listStore = new ArrayList<>();
		 try (
			 // Store.dat存放要新增的多筆資料
			 InputStreamReader isr0 = new InputStreamReader(
					 new FileInputStream("data/Store.dat"), "UTF-8");
			 BufferedReader br = new BufferedReader(isr0);)
			 // 由檔案("data/Store.dat")讀入Store的資料，然後寫入資料庫
		 {
			 String line = "";
			 int count = 0;
			 while ((line = br.readLine()) != null) {
				// 去除 UTF8_BOM
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					count++;
				 String[] sa = line.split("\\|");
				 try {
					 tx = session.beginTransaction();
					 Store store = new Store();
					 store.setStoreName(sa[0].trim());
					 store.setStoreAddress(sa[1].trim());
					 store.setStorePhone(sa[2].trim());
					 store.setServiceHours(sa[3].trim());
					 store.setStorePicture(sa[4].trim());
					 store.setSortNumber(Integer.parseInt(sa[5].trim()));
					 store.setStoreRecomCount(Integer.parseInt(sa[6].trim()));
					 store.setStoreCommentCount(Integer.parseInt(sa[7].trim()));
					 store.setLatitude(Double.parseDouble(sa[8].trim()));
					 store.setLongitude(Double.parseDouble(sa[9].trim()));
					 
					 listStore.add(store);
					 session.save(store);
					 session.flush();
					 tx.commit();		 
				 } catch (Exception e) {
					 System.err.println("新增第 " + count + "筆記錄時發生例外: " + e.getMessage());
					 if (tx != null) {
						 tx.rollback();
					 }
				 } finally {
					 System.out.println("新增 Store 資料， 第" + count + "筆記錄 : " + sa[0]);
				 }
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		// -------------StoreComment資料，寫入資料庫-----------------------			 
		// StoreComment 欄位說明:
		// commentId    	  	: 評論編號			INT AUTO_INCREMENT		主鍵
		// commentMId        	: 會員編號			INT						外來鍵
		// commentSId     	  	: 店家編號			INT						外來鍵
		// commentPicture    	: 評論圖片			LONGBLOB        
		// commentContent    	: 評論內容			TEXT NOT NULL
		// commentAlterCount 	: 修改次數			INT						預設 0
		// commentRecomCount 	: 推薦數			INT						預設 0
		// commentDate       	: 評論時間			DATETIME NOT NULL		NOW() 紀錄評論上傳的時間
		 List<StoreComment> listComment = new ArrayList<>();
		 try (				 
			 // StoreComment.dat存放要新增的多筆資料
			 InputStreamReader isr0 = new InputStreamReader(
					 new FileInputStream("data/StoreComment.dat"), "UTF-8");
			 BufferedReader br = new BufferedReader(isr0);)
			 // 由檔案("data/StoreComment.dat")讀入StoreComment的資料，然後寫入資料庫
		 {
			 String line = "";
			 int count = 0;
			 while ((line = br.readLine()) != null) {
				// 去除 UTF8_BOM
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					count++;
				 String[] sa = line.split("\\|");
				 try {
					 tx = session.beginTransaction();	
					 StoreComment comment = new StoreComment(); 
					 comment.setCommentMId(listMember.get(Integer.parseInt(sa[0]) - 1 )); 
					 comment.setCommentSId(listStore.get(Integer.parseInt(sa[1]) - 1 ));
				// --------------處理Blob(圖片)欄位----------------
					// Blob sb = fileToBlob(sa[2]);
					// fileToBlob為自己編寫的方法,
					// 本程式後來改用 Hibernate 4.3 提供的標準API:
					// Hibernate.getLobCreator(session).createBlob(is, size)
					 long size = 0;
					 if (sa[2].trim().length() > 4) {
					 		File fBlob = new File(sa[2].trim());
					 		size = fBlob.length();
					 		InputStream is = new FileInputStream(fBlob);
					 		Blob sb = Hibernate.getLobCreator(session).createBlob(is, size);
					 		comment.setCommentPicture(sb);
					 }				 
				// --------------處理Clob欄位----------------
					// fileToClob為自己編寫的方法
					// Clob clob = fileToClob(sa[3]);
					// 本程式後來改用 Hibernate 4.3 提供的標準API:
					// Hibernate.getLobCreator(session).createBlob(reader, size)
					 if (sa[3].trim().length() > 4) {
					 	File fClob = new File(sa[3].trim());
					 	size = meteringReader(fClob);
					 	Reader reader = new FileReader(fClob);
					 	Clob clob = Hibernate.getLobCreator(session).createClob(reader, size);					 
					 	comment.setCommentContent(clob);
					 }
					 comment.setCommentAlterCount(Integer.parseInt(sa[4].trim()));
					 comment.setCommentRecomCount(Integer.parseInt(sa[5].trim()));
					 
					 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 Date date = formatter.parse(sa[6].trim());
					 Timestamp ts = new Timestamp(date.getTime());
					 comment.setCommentDate(ts);
					 
					 listComment.add(comment);
					 session.save(comment);
					 tx.commit();		 
				 } catch (Exception e) {
					 System.err.println("新增第 " + count + "筆記錄時發生例外: " + e.getMessage());
					 e.getStackTrace();
					 if (tx != null) {
						 tx.rollback();
					 }
				 } finally {
					 System.out.println("新增 StoreComment 資料， 第" + count + "筆記錄 memberID: " + sa[0]);
					 
				 }
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		// -------------Gift資料，寫入資料庫-----------------------
			// Gift 欄位說明: 
			// giftId      	 : 折價劵編號			INT				主鍵, AUTO_INCREMENT
		    // giftName      : 折價劵名稱			VARCHAR(40)		NOT NULL
		    // giftPicture   : 折價劵圖片			MEDIUMBLOB		NOT NULL
		    // giftContent   : 折價劵內容			VARCHAR(40)		NOT NULL
		    // giftPrice	 : 折價劵價格			DECIMAL(10,2)	NOT NULL
		    // giftDeadline  : 營業時間			DATE			NOT NULL
			try (
					// Gift.dat存放要新增的多筆資料
					InputStreamReader isr0 = new InputStreamReader(
							new FileInputStream("data/Gift.dat"), "UTF-8");
					BufferedReader br = new BufferedReader(isr0);)
			// 由檔案("data/Gift.dat")讀入Gift的資料，然後寫入資料庫
			{
				String line = "";
				int count = 0;
				while ((line = br.readLine()) != null) {
					// 去除 UTF8_BOM
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					
					String[] sa = line.split("\\|");
					try {
						tx = session.beginTransaction();
						Gift gift = new Gift();
						gift.setGiftName(sa[0].trim());	
						// --------------處理Blob(圖片)欄位----------------
						// Blob picture = fileToBlob(sa[1]);
						// fileToBlob為自己編寫的方法,
						// 本程式後來改用 Hibernate 4.3 提供的標準API:
						// Hibernate.getLobCreator(session).createBlob(is, size)
						if (sa[1].trim().length() > 4) {
							File file = new File(sa[1].trim());
							long size = file.length();
							InputStream is = new FileInputStream(file);
							Blob picture = Hibernate.getLobCreator(session).createBlob(is, size);
							gift.setGiftPicture(picture);
						}
						gift.setGiftContent(sa[2].trim());
						gift.setGiftPrice(Double.valueOf(sa[3].trim()));					
						gift.setGiftDeadline(sa[4].trim());
						
						session.save(gift);
						session.flush();
						tx.commit();
					} catch (Exception e) {
						e.getMessage();
						if (tx != null) {
							tx.rollback();
						}
					} finally {
						count++;
						System.out.println("新增 Gift 資料， 第" + count + "筆記錄 : " + sa[0]);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// -------------Gift資料，寫入資料庫-----------------------
			// Message 欄位說明: 
			// messageId      : 留言編號			INT				主鍵, AUTO_INCREMENT
			// messageDate    : 留言發佈時間		DATETIME		NOT NULL
			// msgContent     : 留言內容			VARCHAR(40)		限制字數 40 NOT NULL
			// msgCId     	   : 評論編號			INT				外來鍵
			// msgMId 	       : 會員編號			INT				外來鍵
			 try (				 
					 // Message.dat存放要新增的多筆資料
					 InputStreamReader isr0 = new InputStreamReader(
							 new FileInputStream("data/Message.dat"), "UTF-8");
					 BufferedReader br = new BufferedReader(isr0);)
					 // 由檔案("data/Message.dat")讀入Message的資料，然後寫入資料庫
				 {
					 String line = "";
					 int count = 0;
					 while ((line = br.readLine()) != null) {
						// 去除 UTF8_BOM
							if (line.startsWith(UTF8_BOM)) {
								line = line.substring(1);
							}
							count++;
						 String[] sa = line.split("\\|");
						 try {
							 tx = session.beginTransaction();	
							 Message message = new Message(); 
							 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							 Date date = formatter.parse(sa[0].trim());
							 Timestamp ts = new Timestamp(date.getTime());
							 message.setMessageDate(ts);
							 
							 message.setMsgContent(sa[1].trim());
							 message.setMsgCId(listComment.get(Integer.parseInt(sa[2]) - 1 ));
							 message.setMsgMId(listMember.get(Integer.parseInt(sa[3]) - 1 )); 
							
							 session.save(message);
							 tx.commit();		 
						 } catch (Exception e) {
							 System.err.println("新增第 " + count + "筆記錄時發生例外: " + e.getMessage());
							 e.getStackTrace();
							 if (tx != null) {
								 tx.rollback();
							 }
						 } finally {
							 System.out.println("新增 Message 資料， 第" + count + "筆記錄 memberID: " + sa[3]);
							 
						 }
					 }
				 } catch (Exception e) {
					 e.printStackTrace();
				 }

		if (session != null) {
			session.close();
		}
		HibernateUtil_MySQL.close();
		
	}

	// 計算一個文字檔的字元數
	private static long meteringReader(File f10) {
		long total = 0;
		int len = 0;
		try (FileReader reader = new FileReader(f10);) {
			char[] ca = new char[8192];
			while ((len = reader.read(ca)) != -1) {
				total += len;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return total;
	}
}
