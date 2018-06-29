package other.Repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import other.Model.Store;
import other.Repository.StoreDao;

@Repository
public class StoreDaoImpl implements StoreDao {

	@Autowired
	SessionFactory factory;
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Store getStoreById(Integer storeId) {
		Session session = getSession();
		Store store = session.get(Store.class, storeId);
		return store;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getAllStores() {
		String hql = "FROM Store";
		Session session = getSession();
		return session.createQuery(hql).getResultList();
	}
	
	@Override
	public List<Store> getStoreBySortNum() {
//		String hql = "FROM Store";
//		Session session = getSession();
		return null;
	}
}
