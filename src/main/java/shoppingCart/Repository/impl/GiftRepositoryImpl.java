package shoppingCart.Repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import member.Model.Member;
import shoppingCart.Model.Gift;
import shoppingCart.Model.OrderMain;
import shoppingCart.Model.OrderItem;
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

	@Override
	public Integer setOrder(OrderMain order) {
		Session session = getSession();
		session.save(order);

		return null;
	}

	@Override
	public OrderMain getOrderByNo(Integer orderNo) {
		String hql = "FROM OrderMain o WHERE o.orderNo = :orderNo";
		Session session = getSession();
		session.createQuery(hql).setParameter("orderNo", orderNo).getResultList();
		return (OrderMain) session.createQuery(hql).setParameter("orderNo", orderNo).getSingleResult();
	}

	@Override
	public void setOrderItem(OrderItem oi) {
		Session session = getSession();
		session.save(oi);
	}

	@Override
	public OrderMain getOrderByMember(Member member) {
		String hql = "FROM OrderMain o WHERE o.memberId = :memberId AND o.commit = :count";
		Session session = getSession();
		OrderMain order = null;
		try {
			order = (OrderMain) session.createQuery(hql).setParameter("memberId", member).setParameter("count", 1).getSingleResult();
		} catch (NoResultException e) {
			try {
				String hql2 = "FROM OrderMain o WHERE o.memberId = :memberId";
				List<OrderMain> om = new ArrayList<>();
				om = session.createQuery(hql2).setParameter("memberId", member).getResultList();
				if(om.isEmpty()) {
					
				}else {
					order = om.get(om.size() - 1);
				}
				
			}catch(NoResultException ex) {
				;
			}
			
		}

		return order;
	}

	@Override
	public Gift getGiftByOrderItem(OrderItem oi) {
		String hql = "FROM Gift g WHERE g.giftId = :giftId";
		Session session = getSession();

		return (Gift) session.createQuery(hql).setParameter("giftId", oi.getGiftId().getGiftId()).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderItem> getOrderitemByOrder(OrderMain order) {
		String hql = "FROM OrderItem o WHERE o.oiNo = :order";
		Session session = getSession();

		return session.createQuery(hql).setParameter("order", order).getResultList();
	}

	@Override
	public void updateOrder(OrderMain order) {
		String hql = "UPDATE OrderMain om SET om.commit = :commit WHERE om.orderNo = :orderNo";
		Session session = getSession();
		session.createQuery(hql).setParameter("commit", 0).setParameter("orderNo", order.getOrderNo()).executeUpdate();

	}
	
	

	@Override
	public boolean checkMemberOrder(Member member) {
		try {
			String hql = "FROM OrderMain o WHERE o.memberId = :memberId AND o.commit = :count";
			Session session = getSession();
			session.createQuery(hql).setParameter("memberId", member).setParameter("count", 1).getSingleResult();
			return true;
		}catch(NoResultException e) {
			
			return false;
		}
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}
}
