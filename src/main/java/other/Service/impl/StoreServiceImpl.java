package other.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import other.Model.Store;
import other.Repository.StoreDao;
import other.Service.StoreService;

@Transactional
@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreDao storeDao;
	
	
	@Override
	public Store getStoreById(Integer storeId) {
		return storeDao.getStoreById(storeId);
	}

	@Override
	public List<Store> getAllStores() {
		return storeDao.getAllStores();
	}


	@Override
	public List<Store> getStoreBySortNum(Integer sortNum) {
		return storeDao.getStoreBySortNum(sortNum);
	}

	@Override
	public void setPageNo(Integer pageNo) {
		storeDao.setPageNo(pageNo);
	}

	@Override
	public Integer getTotalPages(Integer sortNum) {
		return storeDao.getTotalPages(sortNum);
	}


	@Override
	public Integer getPageNo() {
		return storeDao.getPageNo();
	}

	@Override
	public Long getRecordCounts(Integer sortNum) {
		return storeDao.getRecordCounts(sortNum);
	}

	@Override
	public List<Store> getUserStores(String userPref) {
		return storeDao.getUserStores(userPref);
	}

	@Override
	public List<Store> getTopStores() {
		return storeDao.getTopStores();
	}

	@Override
	public List<Store> getStoresById(Integer storeId) {
		return storeDao.getStoresById(storeId);
	}

	@Override
	public List<Store> getStoreByName(String myRequest) {
		return storeDao.getStoreByName(myRequest);
	}
	
}
