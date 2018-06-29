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
	public List<Store> getStoreBySortNum() {
		//...
		return null;
	}
	
}
