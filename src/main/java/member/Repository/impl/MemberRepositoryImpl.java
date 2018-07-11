package member.Repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import member.Model.Member;
import member.Repository.MemberRepository;
import other.Model.Store;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

	@Autowired
	SessionFactory factory;

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

	private Session getSession() {
		return factory.getCurrentSession();
	}
}
