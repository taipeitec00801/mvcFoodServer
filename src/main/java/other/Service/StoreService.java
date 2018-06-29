package other.Service;

import java.util.List;

import other.Model.Store;

public interface StoreService {
	Store getStoreById(Integer storeId);
	
	List<Store> getAllStores();
	List<Store> getStoreBySortNum();
}
