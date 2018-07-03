package member.Repository.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import member.Model.Member;
import member.Repository.MemberDao;



@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	SessionFactory factory;
	

	public MemberDaoImpl() {
		super();
	}
	@Override
	public boolean checkAccount(String userAccount) {
		boolean exist = false;
		Member mb = null;
		String hql = "FROM Member m WHERE m.userAccount = :userAccount ";
		
		try {
			Session session = getSession();
			Query query = session.createQuery(hql);
			query = query.setParameter("userAccount", userAccount);
			mb = (Member) query.getSingleResult();
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
		Session session = factory.getCurrentSession();
		session.update(mb);
		n++;
		return n;
	}

	@Override
	public int insertMemberDate(Member mb ) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(mb);
		n++;
		return n;
	}

	@Override
	public byte[] getPortrait(String userAccount) {
		// TODO Auto-generated method stub
		return null;
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
			             .setParameter("userPassword", userPassword)
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
		

	private Session getSession() {
        return factory.getCurrentSession();
	}
	@Override
	public boolean userLogin(String userAccount, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
