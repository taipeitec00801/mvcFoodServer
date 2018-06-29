package other.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import other.Model.Store;
import other.Repository.StoreDao;
import other.Service.StoreService;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreDao storeDao;
	
	
	@Override
	public Store getStoreById(Integer storeId) {
		return storeDao.getStoreById(storeId);
	}
	
}
