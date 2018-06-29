package other.Repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import member.Model.Member;
import other.Model.Store;
import other.Repository.StoreDao;

@Repository
public class StoreDaoImpl implements StoreDao {

	@Autowired
	SessionFactory factory;

//	@Override
//	public Store getStoreById(Integer storeId) {
//		Session session = getSession();
//		Store store = session.get(Store.class, storeId);
//		return store;
//	}
	
	@Override
	public Store getStoreById(Integer storeId) {
		Session session = getSession();
		String hql = "FROM Store s WHERE s.storeId = :id";
		Store store = null;
		try {
			store = (Store) session.createQuery(hql).setParameter("id",storeId).getSingleResult();
		} catch (Exception e) {
			System.err.println("StoreDaoImpl getStoreById   發生例外: " + e.getMessage());
		}
		return store;
	}
	
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
}
