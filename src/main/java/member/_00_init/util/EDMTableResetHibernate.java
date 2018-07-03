package member._00_init.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import org.hibernate.Session;
import org.hibernate.Transaction;
import member.Model.Member;
import other.Model.Store;

public class EDMTableResetHibernate {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {
		System.out.println("Maven + Hibernate + MySQL 新增多筆資料到JSPDB");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int count = 0;
		try (
				// Member.txt存放要新增的多筆資料
				InputStreamReader isr0 = 
				new InputStreamReader(new FileInputStream("data/Member.dat"), "UTF-8");

				BufferedReader br = new BufferedReader(isr0);)
		// 由檔案("data/Member.txt")讀入Member的資料，然後寫入資料庫
		{
			String line = "";
			while ((line = br.readLine()) != null) {
				// 未處理BOM字元，若有需要，請自行加入
				String[] sa = line.split("\\|");
				try {
					tx = session.beginTransaction();
					Member member = new Member();
					member.setUserAccount(sa[0]);
					member.setUserPassword(GlobalService.getMD5Endocing(GlobalService.encryptString(sa[1])));
					member.setNickname(sa[2]);
//					member.setBirthday(Date.valueOf(sa[3]));
					//MemberBeane的Birthday資料型態Date改成String
					member.setBirthday(sa[3]);
					member.setGender(Integer.valueOf(Integer.parseInt(sa[4])));
					member.setUserRank(Integer.valueOf(Integer.parseInt(sa[5])));
					Blob sb = SystemUtils2018.fileToBlob(sa[6]);
					member.setPortrait(sb);
					member.setPreference(sa[7]);
					member.setCollection(sa[8].trim());
					member.setCollection(sa[9].trim());
					member.setCollection(sa[10].trim());
					session.save(member);
					session.flush();
					tx.commit();
					count++;
					System.out.println("新增" + count + "筆記錄:" + sa[1]);

				} catch (Exception e) {
					e.printStackTrace();
					if (tx != null) {
						tx.rollback();
						System.out.println("Transaction rollback");
					}
				}
			}
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// ------------- 由檔案(data/Store.dat)讀取Store資料，寫入資料庫-----------------------
		try (InputStreamReader isr0 = new InputStreamReader(new FileInputStream("data/Store.dat"), "UTF-8");
				BufferedReader br = new BufferedReader(isr0);) {
			String line = "";
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
					session.flush();
					tx.commit();
					count++;
					System.out.println("新增" + count + "筆記錄:" + sa[1]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (session != null) {
			session.close();
		}
		HibernateUtil.getSessionFactory().close();
		
	}
		
	
//	 try (
//			 
//			 // StoreComment.dat存放要新增的多筆資料
//			 InputStreamReader isr0 = new InputStreamReader(
//					 new FileInputStream("data/StoreComment.dat"), "UTF-8");
//			 BufferedReader br = new BufferedReader(isr0);)
//			 // 由檔案("data/StoreComment.dat")讀入Store的資料，然後寫入資料庫
//		 {
//			 String line = "";
//			 count = 0;
//			 while ((line = br.readLine()) != null) {
//				// 去除 UTF8_BOM
//					if (line.startsWith(UTF8_BOM)) {
//						line = line.substring(1);
//					}
//					count++;
//				 String[] sa = line.split("\\|");
//				 try {
//					 tx = session.beginTransaction();	
//					 StoreComment comment = new StoreComment();
////					 MemberService ms = new MemberServiceImpl();
//					 
//					 
//						
//					 MemberService ms = ctx.getBean("ms", MemberService.class);
//					 
////					 ApplicationContext ctx = 
////								ApplicationContextUtils.getApplicationContext(getServletContext());
////						MemberService ms = ctx.getBean(MemberService.class);
//					 
//					 int n = Integer.parseInt(sa[0].trim());
//					 Member commentMId = ms.getMemberById(n);							 
//					 comment.setCommentMId(commentMId);					 
//					 
////					 StoreService ss = new StoreServiceImpl();
//					 StoreService ss = ctx.getBean("ss", StoreService.class);
//					 
//					 Store sommentSId = ss.getStoreById(Integer.parseInt(sa[1].trim()));
//					 comment.setCommentSId(sommentSId);
//				// --------------處理Blob(圖片)欄位----------------
//					// Blob sb = fileToBlob(sa[2]);
//					// fileToBlob為自己編寫的方法,
//					// 本程式後來改用 Hibernate 4.3 提供的標準API:
//					// Hibernate.getLobCreator(session).createBlob(is, size)
//					 long size = 0;
//					 if (sa[2].trim().length() > 4) {
//					 		File fBlob = new File(sa[2].trim());
//					 		size = fBlob.length();
//					 		InputStream is = new FileInputStream(fBlob);
//					 		Blob sb = Hibernate.getLobCreator(session).createBlob(is, size);
//					 		comment.setCommentPicture(sb);
//					 }				 
//				// --------------處理Clob欄位----------------
//					// fileToClob為自己編寫的方法
//					// Clob clob = fileToClob(sa[3]);
//					// 本程式後來改用 Hibernate 4.3 提供的標準API:
//					// Hibernate.getLobCreator(session).createBlob(reader, size)
//					 if (sa[3].trim().length() > 4) {
//					 	File fClob = new File(sa[3].trim());
//					 	size = meteringReader(fClob);
//					 	Reader reader = new FileReader(fClob);
//					 	Clob clob = Hibernate.getLobCreator(session).createClob(reader, size);					 
//					 	comment.setCommentContent(clob);
//					 }
//					 comment.setCommentAlterCount(Integer.parseInt(sa[4].trim()));
//					 comment.setCommentRecomCount(Integer.parseInt(sa[5].trim()));
//					 
//					 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					 Date date = formatter.parse(sa[6].trim());
//					 Timestamp ts = new Timestamp(date.getTime());
//					 comment.setCommentDate(ts);
//			
//					 session.save(comment);
//					 tx.commit();		 
//				 } catch (Exception e) {
//					 System.err.println("新增第 " + count + "筆記錄時發生例外: " + e.getMessage());
//					 e.getStackTrace();
//					 if (tx != null) {
//						 tx.rollback();
//					 }
//				 } finally {
//					 System.out.println("新增 StoreComment 資料， 第" + count + "筆記錄 : " + sa[0]);
//					 ((ConfigurableApplicationContext)ctx).close();
//				 }
//			 }
//		 } catch (Exception e) {
//			 e.printStackTrace();
//		 }
//
//		if (session != null) {
//			session.close();
//		}
//		HibernateUtil_MySQL.close();
//		
//	}

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