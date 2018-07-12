package init.JDBC;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import init.JDBC.Model.Gift;
import init.JDBC.Model.Member;
import init.JDBC.Model.Message;
import init.JDBC.Model.Store;
import init.JDBC.Model.StoreComment;
import init.JDBC.Utils.DBService;
import init.JDBC.Utils.SystemUtils;
import javaClass.GlobalService;

public class TableReset_JDBC {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {
		String tableName = "";
		try (Connection con = DriverManager.getConnection(DBService.getDbUrl(), DBService.getMySQLUser(),
				DBService.getMySQLPassword()); Statement stmt = con.createStatement();) {
			// 因 FOREIGN KEY 規則
			// 需先刪除 Message表格--> StoreComment --> *

			// 1. 刪除表格
			tableName = "Gift";
			stmt.executeUpdate(DBService.getDropGift());
			System.out.println("Gift表格刪除成功");
			tableName = "Message";
			stmt.executeUpdate(DBService.getDropMessage());
			System.out.println("Message表格刪除成功");
			tableName = "Comment";
			stmt.executeUpdate(DBService.getDropStoreComment());
			System.out.println("StoreComment表格刪除成功");
			tableName = "Store";
			stmt.executeUpdate(DBService.getDropStore());
			System.out.println("Store表格刪除成功");
			tableName = "Member";
			stmt.executeUpdate(DBService.getDropMember());
			System.out.println("Member表格刪除成功");

			// 創建表格
			stmt.executeUpdate(DBService.getCreateMember());
			System.out.println("Member表格產生成功");
			tableName = "Store";
			stmt.executeUpdate(DBService.getCreateStore());
			System.out.println("Store表格產生成功");
			tableName = "Comment";
			stmt.executeUpdate(DBService.getCreateStoreComment());
			System.out.println("StoreComment表格產生成功");
			tableName = "Message";
			stmt.executeUpdate(DBService.getCreateMessage());
			System.out.println("Message表格產生成功");
			tableName = "Gift";
			stmt.executeUpdate(DBService.getCreateGift());
			System.out.println("Gift表格產生成功");
			tableName = "";
			System.out.println("============================================");

			// Member資料
			System.out.println("新增  Member資料");
			// 由"data/Member.dat"逐筆讀入Member表格內的初始資料，然後依序新增到Member表格中
			String line = "";
			int count = 0;
			try (FileInputStream fisMember = new FileInputStream("data/Member.dat");
					InputStreamReader isrMember = new InputStreamReader(fisMember, "UTF8");
					BufferedReader brMember = new BufferedReader(isrMember);) {
				while ((line = brMember.readLine()) != null) {
					// 去除 UTF8_BOM
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] sa = line.split("\\|");
					Member member = new Member();
					member.setUserAccount(sa[0].trim());
					member.setUserPassword(GlobalService.getMD5Endocing(GlobalService.encryptString(sa[1].trim())));

					member.setNickname(sa[2].trim());

					member.setBirthday(Date.valueOf(sa[3].trim()));
					member.setGender(Integer.parseInt(sa[4].trim()));
					member.setUserRank(Integer.parseInt(sa[5].trim()));
					// 讀取圖片檔
					if (!sa[6].trim().equals("null") && sa[6].trim().length() > 4) {
						Blob portrait = SystemUtils.fileToBlob(sa[6].trim());
						member.setPortrait(portrait);
					}
					member.setPreference(sa[7].trim());
					member.setCollection(sa[8].trim());
					if (!sa[9].trim().equals(null) && sa[9].trim().length() != 4) {
						member.setUserGift(sa[9].trim());
					}
					if (!sa[10].trim().equals(null) && sa[10].trim().length() != 4) {
						member.setUserFriends(sa[10].trim());
					}

					int n = saveMember(member, con);
					String s = seccessOrFail(n);
					count++;
					System.out.print("新增第 " + count + "筆記錄 : ");
					System.out.println(sa[0] + s);
				}
			} finally {
				// 印出資料新增成功的訊息
				System.out.println("Member資料新增成功，共 " + count + "筆記錄");
				count = 0;
				System.out.println("============================================");
			}

			// Store資料
			System.out.println("新增  Store資料");
			// 由"data/Store.dat"逐筆讀入Store表格內的初始資料，然後依序新增到Store表格中
			try (FileInputStream fisStore = new FileInputStream("data/Store.dat");
					InputStreamReader isrStore = new InputStreamReader(fisStore, "UTF8");
					BufferedReader brStore = new BufferedReader(isrStore);) {
				while ((line = brStore.readLine()) != null) {
					// 去除 UTF8_BOM
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] sa = line.split("\\|");
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

					int n = saveStore(store, con);
					String s = seccessOrFail(n);
					count++;
					System.out.print("新增第 " + count + "筆記錄 : ");
					System.out.println(sa[0] + s);
				}
			} catch (Exception ex) {
				count++;
				System.err.println("新增第 " + count + "筆記錄時發生例外: " + ex.getMessage());
				ex.printStackTrace();
			} finally {
				// 印出資料新增成功的訊息
				System.out.println("Store資料新增成功，共 " + count + "筆記錄");
				count = 0;
				System.out.println("============================================");
			}

			// StoreComment資料
			System.out.println("新增  StoreComment資料");
			// 由"data/StoreComment.dat"逐筆讀入StoreComment表格內的初始資料，然後依序新增到StoreComment表格中
			try (FileInputStream fisComment = new FileInputStream("data/StoreComment.dat");
					InputStreamReader isrComment = new InputStreamReader(fisComment, "UTF8");
					BufferedReader brComment = new BufferedReader(isrComment);) {
				while ((line = brComment.readLine()) != null) {
					// 去除 UTF8_BOM
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] sa = line.split("\\|");
					StoreComment comment = new StoreComment();
					comment.setCommentMId(Integer.parseInt(sa[0].trim()));
					comment.setCommentSId(Integer.parseInt(sa[1].trim()));
					Blob picture = SystemUtils.fileToBlob(sa[2].trim());
					comment.setCommentPicture(picture);
					Clob content = SystemUtils.fileToClob(sa[3].trim());
					comment.setCommentContent(content);
					comment.setCommentAlterCount(Integer.parseInt(sa[4].trim()));
					comment.setCommentRecomCount(Integer.parseInt(sa[5].trim()));

					comment.setCommentDate(Timestamp.valueOf(sa[6].trim()));

					int n = saveComment(comment, con);
					String s = seccessOrFail(n);
					count++;
					System.out.print("新增第 " + count + "筆記錄 : ");
					System.out.println("memberID:" + sa[0] + s);
				}
			} finally {
				// 印出資料新增成功的訊息
				System.out.println("StoreComment資料新增成功，共 " + count + "筆記錄");
				count = 0;
				System.out.println("============================================");
			}

			// Gift資料
			System.out.println("新增  Gift資料");
			// 由"data/Gift.dat"逐筆讀入Gift表格內的初始資料，然後依序新增到Gift表格中
			try (FileInputStream fisGift = new FileInputStream("data/Gift.dat");
					InputStreamReader isrGift = new InputStreamReader(fisGift, "UTF8");
					BufferedReader brGift = new BufferedReader(isrGift);) {
				while ((line = brGift.readLine()) != null) {
					// 去除 UTF8_BOM
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] sa = line.split("\\|");
					Gift gift = new Gift();

					gift.setGiftName(sa[0].trim());
					Blob picture = SystemUtils.fileToBlob(sa[1].trim());
					gift.setGiftPicture(picture);
					gift.setGiftContent(sa[2].trim());
					gift.setGiftPrice(Double.valueOf(sa[3].trim()));
					gift.setGiftDeadline(Date.valueOf(sa[4].trim()));

					int n = saveGift(gift, con);
					String s = seccessOrFail(n);
					count++;
					System.out.print("新增第 " + count + "筆記錄 : ");
					System.out.println(sa[0] + s);
				}
			} finally {
				// 印出資料新增成功的訊息
				System.out.println("Gift資料新增成功，共 " + count + "筆記錄");
				count = 0;
				System.out.println("============================================");
			}

			// Message資料
			System.out.println("新增  Message資料");
			// 由"data/Message.dat"逐筆讀入Message表格內的初始資料，然後依序新增到Message表格中
			try (FileInputStream fisGift = new FileInputStream("data/Message.dat");
					InputStreamReader isrGift = new InputStreamReader(fisGift, "UTF8");
					BufferedReader brGift = new BufferedReader(isrGift);) {
				while ((line = brGift.readLine()) != null) {
					// 去除 UTF8_BOM
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] sa = line.split("\\|");
					Message message = new Message();

					message.setMessageDate(Timestamp.valueOf(sa[0].trim()));
					message.setMsgContent(sa[1].trim());					
					message.setMsgCId(Integer.parseInt(sa[2].trim()));
					message.setMsgMId(Integer.parseInt(sa[3].trim()));

					int n = saveMessage(message, con);
					String s = seccessOrFail(n);
					count++;
					System.out.print("新增第 " + count + "筆記錄 : ");
					System.out.println("memberID:" + sa[3] + s);
				}
			} finally {
				// 印出資料新增成功的訊息
				System.out.println("Message資料新增成功，共 " + count + "筆記錄");
				count = 0;
				System.out.println("============================================");
			}

		} catch (SQLException e) {
			System.err.println("新建 " + tableName + " 表格時發生SQL例外: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("新建 " + tableName + " 表格時發生IO例外: " + e.getMessage());
			e.printStackTrace();
		}

	}

	static public int saveMember(Member member, Connection con) {
		String sql = "INSERT INTO Member " + "	VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		int n = 0;
		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, member.getUserAccount());
			ps.setString(2, member.getUserPassword());
			ps.setString(3, member.getNickname());
			ps.setDate(4, member.getBirthday());
			ps.setInt(5, member.getGender());
			ps.setInt(6, member.getUserRank());
			ps.setBlob(7, member.getPortrait());
			ps.setString(8, member.getPreference());
			ps.setString(9, member.getCollection());
			ps.setString(10, member.getUserGift());
			ps.setString(11, member.getUserFriends());
			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n;
	}

	static public int saveStore(Store store, Connection con) {
		String sql = "INSERT INTO Store " + "	VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		int n = 0;
		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, store.getStoreName());
			ps.setString(2, store.getStoreAddress());
			ps.setString(3, store.getStorePhone());
			ps.setString(4, store.getServiceHours());
			ps.setString(5, store.getStorePicture());
			ps.setInt(6, store.getSortNumber());
			ps.setInt(7, store.getStoreRecomCount());
			ps.setInt(8, store.getStoreCommentCount());
			ps.setDouble(9, store.getLatitude());
			ps.setDouble(10, store.getLongitude());
			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n;
	}

	static public int saveComment(StoreComment comment, Connection con) {
		String sql = "INSERT INTO StoreComment " + "	VALUES(null, ?, ?, ?, ?, ?, ?, ?);";

		int n = 0;
		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, comment.getCommentMId());
			ps.setInt(2, comment.getCommentSId());
			ps.setBlob(3, comment.getCommentPicture());
			ps.setClob(4, comment.getCommentContent());
			ps.setInt(5, comment.getCommentAlterCount());
			ps.setInt(6, comment.getCommentRecomCount());
			ps.setTimestamp(7, comment.getCommentDate());
			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n;
	}

	static public int saveGift(Gift gift, Connection con) {
		String sql = "INSERT INTO Gift " + "	VALUES(null, ?, ?, ?, ?, ?);";

		int n = 0;
		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, gift.getGiftName());
			ps.setBlob(2, gift.getGiftPicture());
			ps.setString(3, gift.getGiftContent());
			ps.setDouble(4, gift.getGiftPrice());
			ps.setDate(5, gift.getGiftDeadline());
			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n;
	}
	
	static public int saveMessage(Message message, Connection con) {
		String sql = "INSERT INTO Message " 
					+ "	VALUES(null, ?, ?, ?, ?);";

		int n = 0;
		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setTimestamp(1, message.getMessageDate());
			ps.setString(2, message.getMsgContent());
			ps.setInt(3, message.getMsgCId());
			ps.setInt(4, message.getMsgMId());
			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n;
	}

	static public String seccessOrFail(int con) {
		String s = " --> ";
		if (con > 0) {
			s += "Seccess";
			return s;
		} else {
			s += "Fail";
			return s;
		}

	}
}