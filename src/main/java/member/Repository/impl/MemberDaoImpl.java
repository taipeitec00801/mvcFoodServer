package member.Repository.impl;

import java.sql.Blob;
import java.sql.Date;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javaClass.GlobalService;
import member.Model.Member;
import member.Repository.MemberDao;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	SessionFactory factory;
		
	@Override
	public boolean checkAccount(String userAccount) {
		boolean exist = false;
		String hql = "FROM Member m WHERE m.userAccount = :userAccount ";
		
		try {
			Session session = getSession();
			Query query = session.createQuery(hql);
			query = query.setParameter("userAccount", userAccount);
			Member mb = (Member) query.getSingleResult();
			exist = true;
		} catch(NoResultException ex) {
			exist = false;
		}
		return exist;
	}
	
	@Override
	public Member getUserId(String userAccount) {
		Session session = getSession();
		String hql = "FROM Member m WHERE m.userAccount = :userAccount";
		Member mb = null;
		try {
			mb = (Member) session.createQuery(hql)
					.setParameter("userAccount", userAccount).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mb;
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
	public Member getUserDateNoPortrait(String userAccount) {
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
			int n = session.createQuery(hql)
					.setParameter("newNickname", nickname)
					.setParameter("newBirthday", birthday)
					.setParameter("newPortrait", portrait)
					.setParameter("id", memberId)
					.executeUpdate();
		}
		

	private Session getSession() {
        return factory.getCurrentSession();
	}
}
