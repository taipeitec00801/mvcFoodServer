package other.Repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import other.Model.Store;
import other.Repository.StoreDao;

@Repository
public class StoreDaoImpl implements StoreDao {
	private Integer recordsPerPage = 9; // 預設值：每頁9筆
	private Integer pageNo = 1;		// 存放目前顯示之頁面的編號
	private Integer totalPages = 1;

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
		List<Store> list = new ArrayList<Store>();
		String hql = "FROM Store s";		
		Session session = getSession();
		Query query = session.createQuery(hql);
		int startNo = (pageNo - 1) * recordsPerPage;
		query.setFirstResult(startNo);
		query.setMaxResults(recordsPerPage);
		list = (List<Store>)query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getStoreBySortNum(Integer sortNum) {
		List<Store> list = new ArrayList<Store>();
		String hql = "FROM Store s WHERE s.sortNumber = :sortNum";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter("sortNum", sortNum);
		int startNo = (pageNo - 1) * recordsPerPage;
		query.setFirstResult(startNo);
		query.setMaxResults(recordsPerPage);
		list = (List<Store>)query.getResultList();
		return list;
	}
	
	@Override
	public Integer getPageNo() {
		return pageNo;
	}
	
	@Override
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public Integer getTotalPages(Integer sortNum) {
		totalPages = (int) (Math.ceil(getRecordCounts(sortNum) / (double) recordsPerPage));
		return totalPages;
	}
	
	@Override
	public Long getRecordCounts(Integer sortNum) {
		long count = 0; // 必須使用 long 型態
		String hql = "SELECT COUNT(*) FROM Store";
		Session session = getSession();
		if (sortNum < 10 && sortNum >= 0) {
			hql = "SELECT COUNT(*) FROM Store s WHERE s.sortNumber = :sortNum";
			count = (long) session.createQuery(hql).setParameter("sortNum", sortNum).getSingleResult();	
		} else {
			count = (long) session.createQuery(hql).getSingleResult();
		}			
		return count;
	}

	@Override
	public List<Store> getUserStores(String userPref) {
		String[] spiltUserPref = userPref.split(",");
		return null;
	}

	@Override
	public List<Store> getTopStores() {
		List<Store> list = new ArrayList<Store>();
		String hql = "FROM Store s ORDER BY s.storeRecomCount DESC";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(6);
		list = (List<Store>)query.getResultList();
		return list;
	}

	@Override
	public List<Store> getStoresById(Integer storeId) {
		List<Store> list = new ArrayList<Store>();
		String hql = "FROM Store s WHERE s.storeId = :storeId";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter("storeId", storeId);
		list = (List<Store>)query.getResultList();
		return list;
	}
	
}
