package member.Repository.impl;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javaClass.GlobalService;
import member.Model.Member;
import member.Repository.MemberDao;
import other.Model.Store;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	SessionFactory factory;
	
	private Session getSession() {
        return factory.getCurrentSession();
	}
	
	@SuppressWarnings("unused")
	@Override
	public boolean checkAccount(String userAccount) {
		boolean exist = false;
		Session session = getSession();
		String hql = "FROM Member m WHERE m.userAccount = :userAccount ";
		try {
			Member mb = (Member) session.createQuery(hql)
					.setParameter("userAccount", userAccount)
					.getSingleResult();
			exist = true;
		} catch(NoResultException ex) {
			exist = false;
		}
		return exist;
	}
		
	@Override
	public int updateMemberDate(Member mb) {
		int n = 0;
		Session session = getSession();
		session.update(mb);
		n++;
		return n;
	}

	@Override
	public int insertMemberDate(Member mb ) {
		int n = 0;
		Session session = getSession();
		session.save(mb);
		n++;
		return n;
	}
	
	@Override
	public Member getMemberByAccount(String userAccount) {
		Member mb = null;
		String hql = "FROM Member m WHERE m.userAccount = :userAccount ";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query = query.setParameter("userAccount", userAccount);
		try {
			mb = (Member) query.getSingleResult();		
		} catch(NoResultException ex) {
			System.out.println("mb = NULL");
			mb = null;
		}
		return mb;
	}
	
	
	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
	// 否則傳回 null。
	@Override
	public boolean checkACPassword(String userAccount, String userPassword) {
		boolean exist = false;
		Member memb = null;
		String hql = "FROM Member m WHERE m.userAccount = :userAccount and m.userPassword = :userPassword";
		Session session = getSession();
		try {
			memb = (Member) session.createQuery(hql)
					.setParameter("userAccount", userAccount)
		             .setParameter("userPassword", GlobalService.getMD5Endocing(userPassword))
					.getSingleResult();
			if(memb!=null) {
				exist = true;
			}else {
				exist = false;
			}
			
		} catch(NoResultException ex) {
			exist = false;
			ex.printStackTrace();		
		}
		return exist;
	}
	
	@Override
	public void updateMemInfo(Integer memberId, String nickname, String birthday, Blob portrait) {
		String hql = "UPDATE Member mb SET mb.nickname = :newNickname,"
				+ " mb.birthday = :newBirthday,"
				+ " mb.portrait = :newPortrait"
				+ " WHERE mb.memberId = :id";
		Session session = getSession();
		session.createQuery(hql)
				.setParameter("newNickname", nickname)
				.setParameter("newBirthday", birthday)
				.setParameter("newPortrait", portrait)
				.setParameter("id", memberId)
				.executeUpdate();
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getAllMember() {
		String hql = "FROM Member";
		Session session = getSession();
		return session.createQuery(hql).getResultList();
	}

	@Override
	public boolean checkEmail(String email) {
		String hql = "FROM Member mb WHERE mb.userAccount = :userAccount";
		Session session = getSession();
		try {
			session.createQuery(hql).setParameter("userAccount", email).getSingleResult();
			// 有這帳號
			return false;
		} catch (NoResultException e) {
			// 沒這帳號
			return true;
		}
	}

	@Override
	public int addMember(Member mb) {
		int n = 0;
		Session session = getSession();
		session.save(mb);
		n++;
		return n;
	}

	@Override
	public String getUserPass(String userId) {
		String hql = "SELECT mb.userPassword FROM Member mb WHERE mb.userAccount = :userAccount";
		Session session = getSession();
		return (String) session.createQuery(hql).setParameter("userAccount", userId).getSingleResult();
	}

	@Override
	public Member getMember(String userId) {
		String hql = "FROM Member mb WHERE mb.userAccount = :userAccount";
		Session session = getSession();
		try {
			return (Member) session.createQuery(hql).setParameter("userAccount", userId).getSingleResult();
		} catch (NoResultException e) {
			Member mb = new Member();
			return mb;
		}
	}

	@Override
	public List<Store> getMemberLikeStore(String[] like) {
		List<Store> stores = new ArrayList<Store>();
		String hql = "FROM Store st WHERE st.storeId = :storeId";
		Session session = getSession();
		try {
			for (String l : like) {
				try {
					Store st = (Store) session.createQuery(hql).setParameter("storeId", Integer.parseInt(l)).getSingleResult();
					stores.add(st);
				} catch (NoResultException e) {
					System.out.println("Member -> getMemberLikeStore 拋出 NoResultException");
				} catch (NullPointerException ex) {
					System.out.println("Member -> getMemberLikeStore 拋出 NullPointerException");
				}

			}
		} catch (Exception ex) {
			System.out.println("Member -> getMemberLikeStore 拋出 Exception");
		}
		return stores;
	}
	
}
