package other.Repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import member.Model.Member;
import other.Model.Store;
import other.Model.StoreComment;
import other.Repository.StoreDao;

@Repository
public class StoreDaoImpl implements StoreDao {
	public static final Integer RECORDS_PER_PAGE = 9; // 預設值：每頁9筆
	private Integer pageNo = 1; // 存放目前顯示之頁面的編號
	private Integer totalPages = 1;

	@Autowired
	SessionFactory factory;

	private Session getSession() {
		return factory.getCurrentSession();
	}

	// 網頁用 分頁 限制取9筆資料
	private void pagination(Query query, String features) {
		if (features.equals("web")) {
			int startNo = (pageNo - 1) * RECORDS_PER_PAGE;
			query.setFirstResult(startNo);
			query.setMaxResults(RECORDS_PER_PAGE);
		}
	}

	@Override
	public Store getStoreById(Integer storeId) {
		Session session = getSession();
		Store store = session.get(Store.class, storeId);
		return store;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getAllStores(String features) {
		String hql = "FROM Store s";
		Session session = getSession();
		Query query = session.createQuery(hql);
		pagination(query, features);
		return (List<Store>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getStoreBySortNum(Integer sortNum, String features) {
		String hql = "FROM Store s WHERE s.sortNumber = :sortNum";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter("sortNum", sortNum);
		pagination(query, features);
		return (List<Store>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getStoreByName(String storeName, String features) {
		String hql = "FROM Store s WHERE s.storeName LIKE :storeName";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter("storeName", "%" + storeName + "%");
		pagination(query, features);
		return (List<Store>) query.getResultList();
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
		// Math.ceil() 函式會回傳大於等於所給數字的最小整數。
		totalPages = (int) (Math.ceil(getRecordCounts(sortNum) / (double) RECORDS_PER_PAGE));
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getUserStores(String userPref) {
		Session session = getSession();
		String[] spiltUserPref = userPref.split(",");
		StringBuilder sb = new StringBuilder("FROM Store s WHERE s.sortNumber = :sortNumber0");
		for (int i = 1; i < spiltUserPref.length; i++) {
			sb.append(" OR s.sortNumber = :sortNumber" + i);
		}
		sb.append(" ORDER BY s.storeRecomCount DESC");
		String hql = sb.toString();
		System.out.println("getUserStores HQL!!:" + hql);
		Query query = session.createQuery(hql);
		for (int i = 0; i < spiltUserPref.length; i++) {
			String s = spiltUserPref[i];
			query.setParameter("sortNumber" + i, Integer.parseInt(s));
		}
		query.setFirstResult(0);
		query.setMaxResults(6);
		return (List<Store>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getTopStores() {
		String hql = "FROM Store s ORDER BY s.storeRecomCount DESC";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(6);
		return (List<Store>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getStoresById(Integer storeId) {
		String hql = "FROM Store s WHERE s.storeId = :storeId";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter("storeId", storeId);
		return (List<Store>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoreComment> getTopComm() {
		List<StoreComment> list = new ArrayList<>();
		String hql = "FROM StoreComment sc ORDER BY sc.commentRecomCount DESC";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(3);
		list = (List<StoreComment>) query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoreComment> getCommByStore(Integer storeId) {
		List<StoreComment> list = new ArrayList<>();
		String hql = "FROM StoreComment sc WHERE sc.commentSId.storeId = :storeId";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter("storeId", storeId);
		list = query.getResultList();
		return list;
	}

	@Override
	public Member getMemberById(Integer memberId) {
		Session session = getSession();
		String hql = "FROM Member m WHERE m.memberId = :memberId";
		Member mb = null;
		try {
			mb = (Member) session.createQuery(hql).setParameter("memberId", memberId).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mb;
	}

	@Override
	public StoreComment getCommentById(Integer commentId) {
		Session session = getSession();
		String hql = "FROM StoreComment sc WHERE sc.commentId = :commentId";
		StoreComment sc = null;
		try {
			sc = (StoreComment) session.createQuery(hql).setParameter("commentId", commentId).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sc;
	}

	@Override
	public String[] findStoreById(Integer storeId) {
		String hql = "FROM Store s WHERE s.storeId = :storeId";
		Session session = getSession();
		Store store = (Store) session.createQuery(hql).setParameter("storeId", storeId).getSingleResult();
		return store.getStorePicture().split(",");
	}

}
