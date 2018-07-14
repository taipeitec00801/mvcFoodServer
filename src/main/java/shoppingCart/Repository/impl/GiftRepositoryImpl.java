package shoppingCart.Repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shoppingCart.Model.Gift;
import shoppingCart.Repository.GiftRepository;

@Repository
public class GiftRepositoryImpl implements GiftRepository {

	@Autowired
	SessionFactory factory;	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gift> getAllGift() {
		String hql = "FROM Gift";
		Session session = getSession();		
		return session.createQuery(hql).getResultList();
	}
	
	@Override
	public Gift getGiftById(Integer giftId) {
		String hql = "FROM Gift g WHERE g.giftId = :giftId";
		Session session = getSession();
		return (Gift) session.createQuery(hql).setParameter("giftId", giftId).getSingleResult();
	}

	




	private Session getSession() {
		return factory.getCurrentSession();
	}
}
