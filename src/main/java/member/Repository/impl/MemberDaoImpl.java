package member.Repository.impl;


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
	
//	@Override
//	public Member getMemberById(Integer memberId) {
//		Session session = getSession();
//		Member member = session.get(Member.class, memberId);
//		return member;
//	}
	
	@Override
	public Member getMemberById(Integer memberId) {
		Session session = getSession();
		String hql = "FROM Member m WHERE m.memberId = :id";
		Member member = null;
		try {
			member = (Member) session.createQuery(hql).setParameter("id",memberId).getSingleResult();
		} catch (Exception e) {
			System.err.println("MemberDaoImpl getMemberById   發生例外: " + e.getMessage());
		}
		return member;
	}
	
	
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
}
