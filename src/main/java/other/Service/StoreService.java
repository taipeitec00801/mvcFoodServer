package other.Service;

import java.util.List;

import other.Model.Store;

public interface StoreService {
	Store getStoreById(Integer storeId);
	
	List<Store> getAllStores();
	List<Store> getStoreBySortNum(Integer sortNum);
	List<Store> getUserStores(String userPref);
	List<Store> getTopStores();
	List<Store> getStoresById(Integer storeId);
	List<Store> getStoreByName(String myRequest);
	
	Integer getPageNo();
	void setPageNo(Integer pageNo);
	Integer getTotalPages(Integer sortNum);
	Long getRecordCounts(Integer sortNum);
}
