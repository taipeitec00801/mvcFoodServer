package other.Repository;

import java.util.List;

import other.Model.Store;

public interface StoreDao {
	Store getStoreById(Integer storeId);
	
	List<Store> getAllStores();
	List<Store> getStoreBySortNum();
}
