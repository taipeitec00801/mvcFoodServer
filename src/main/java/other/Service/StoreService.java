package other.Service;

import java.util.List;

import member.Model.Member;
import other.Model.Store;
import other.Model.StoreComment;

public interface StoreService {
	Store getStoreById(Integer storeId);
	Member getMemberById(Integer memberId);
	StoreComment getCommentById(Integer commentId);
	
	List<Store> getAllStores();
	List<Store> getStoreBySortNum(Integer sortNum);
	List<Store> getUserStores(String userPref);
	List<Store> getTopStores();
	List<Store> getStoresById(Integer storeId);
	List<Store> getStoreByName(String myRequest);
	List<StoreComment> getTopComm();
	List<StoreComment> getCommByStore(Integer storeId);
	
	
	Integer getPageNo();
	void setPageNo(Integer pageNo);
	Integer getTotalPages(Integer sortNum);
	Long getRecordCounts(Integer sortNum);
}
