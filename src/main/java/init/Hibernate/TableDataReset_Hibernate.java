package init.Hibernate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import init.Hibernate.Utils.HibernateUtil_MySQL;
import init.Hibernate.Utils.GlobalService;
import member.Model.Member;
import other.Model.Store;

/**
 * 
 */
public class TableDataReset_Hibernate {
	public static void main(String[] args) throws FileNotFoundException, IOException, SerialException, SQLException {
		System.out.println("Maven + Hibernate + MySQL 新增多筆資料到Food");
		Session session = HibernateUtil_MySQL.getSessionFactory().openSession();
		Transaction tx = null;
		// -------------Member資料，寫入資料庫-----------------------
		// Member 欄位說明: 
		// memberId : 		會員編號 	INT AUTO_INCREMENT		主鍵  
		// userAccount : 	帳號  		VARCHAR(40) NOT NULL	信箱 
		// userPassword : 	密碼 		VARCHAR(12) NOT NULL 
		// nickname : 		暱稱 		VARCHAR(20) 			預設 UserAccount "@"前字串 
		// birthday : 		生日 		DATE NOT NULL 
		// gender : 		性別 		TINYINT NOT NULL		(0~1), 0=女, 1=男 
		// userRank : 		會員級別 	TINYINT 				(1~9) 預設 1, root=0
		// portrait : 		頭像 		MEDIUMBLOB 
		// preference : 	喜好種類 	VARCHAR(20) 			紀錄 SortNumber
		// collection : 	收藏 		VARCHAR(1000) 			紀錄 StoreId 
		// userGift : 		禮物 		VARCHAR(1000) 			紀錄GiftId 
		// userFriends : 	追蹤 		VARCHAR(1000) 			紀錄 MemberId
		
		try (
				// Member.txt存放要新增的多筆資料
				InputStreamReader isr0 = new InputStreamReader(new FileInputStream("data/Member.dat"), "UTF-8");
				BufferedReader br = new BufferedReader(isr0);)
		// 由檔案("data/Member.dat")讀入Member的資料，然後寫入資料庫
		{
			String line = "";
			int count = 0;
			while ((line = br.readLine()) != null) {
				// 未處理BOM字元，若有需要，請自行加入
				String[] sa = line.split("\\|");
				try {
					tx = session.beginTransaction();
					Member member = new Member();
					member.setUserAccount(sa[0].trim());
					
//					member.setUserPassword(sa[1].trim());
					// 若又進行編碼 需先至 member/Model/Member.java 中修改 欄位型態
					member.setUserPassword(GlobalService.getMD5Endocing(GlobalService.encryptString(sa[1].trim())));

					member.setNickname(sa[2].trim());
					member.setBirthday(sa[3].trim());
					member.setGender(Integer.parseInt(sa[4].trim()));
					member.setUserRank(Integer.parseInt(sa[5].trim()));
					// --------------處理Blob(圖片)欄位----------------
					// Blob portrait = fileToBlob(sa[6]);
					// fileToBlob為自己編寫的方法,
					// 本程式後來改用 Hibernate 4.3 提供的標準API:
					// Hibernate.getLobCreator(session).createBlob(is, size)
					if (sa[6].trim().length() > 0) {
						File file = new File(sa[6].trim());
						System.out.println(sa[2].trim() + "的 Portrait Path : " + file.getAbsolutePath());
						long size = file.length();
						InputStream is = new FileInputStream(file);
						Blob portrait = Hibernate.getLobCreator(session).createBlob(is, size);
						member.setPortrait(portrait);
					}
					member.setPreference(sa[7].trim());
					member.setCollection(sa[8].trim());
					member.setUserGift(sa[9].trim());
					member.setUserFriends(sa[10].trim());

					session.save(member);
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
		// storeId : 			店家編號 			INT AUTO_INCREMENT 			主鍵,  
		// storeName : 			店家名稱 			VARCHAR(40) NOT NULL 
		// storeAddress : 		店家地址 			VARCHAR(100) NOT NULL 
		// storePhone :			店家電話 			VARCHAR(15) 				格式-->02-1234-1234 
		// serviceHours : 		營業時間 			VARCHAR(40)					格式-->00:00~00:00 & 00:00~00:00 或是-->00:00~00:00 (公休時間) 
		// storePicture :		店家圖片(圖片名稱) 	VARCHAR(700) NOT NULL 		StoreId(000~999)+Picture(00~99) 格式-->000_00
		// sortNumber : 		分類編號 			TINYINT NOT NULL			(0~9) 
		// storeRecomCount :	推薦數 			INT 						預設 0
		// storeCommentCount :	評論數 			INT 						預設 0

		 try (
			 // Store.txt存放要新增的多筆資料
			 InputStreamReader isr0 = new InputStreamReader(new
			 FileInputStream("data/Store.dat"), "UTF-8");
			 BufferedReader br = new BufferedReader(isr0);)
			 // 由檔案("data/Store.txt")讀入Store的資料，然後寫入資料庫
		 {
			 String line = "";
			 int count = 0;
			 while ((line = br.readLine()) != null) {
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
			
					 session.save(store);
					 tx.commit();		 
				 } catch (Exception e) {
					 e.getMessage();
					 if (tx != null) {
						 tx.rollback();
					 }
				 } finally {
					 count++;
					 System.out.println("新增 Store 資料， 第" + count + "筆記錄 : " + sa[0]);
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
