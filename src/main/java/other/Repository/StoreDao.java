package other.Repository;

import java.util.List;

import member.Model.Member;
import other.Model.Message;
import other.Model.Store;
import other.Model.StoreComment;

public interface StoreDao {
	Store getStoreById(Integer storeId);	
	List<Store> getAllStores(String features);
	List<Store> getStoreBySortNum(Integer sortNum, String features);
	List<Store> getStoreByName(String myRequest, String features);
	List<Store> getUserStores(String userPref);
	List<Store> getTopStores();
	List<Store> getStoresById(Integer storeId);


	Integer updateStRecomYNByMSId(Member member, Store store, Integer recomYN);
	Integer getStRecomYNByMSId(Member member, Store store);

	List<Message> getMessageByComm(Integer msgCId);
	int updateStoreComment(StoreComment sc);
	
	//----分頁---------
	Integer getPageNo();
	void setPageNo(Integer pageNo);
	Integer getTotalPages(Integer sortNum);
	Long getRecordCounts(Integer sortNum);
	//非店家
	Member getMemberById(Integer memberId);
	StoreComment getCommentById(Integer commentId);
	List<StoreComment> getTopComm();
	List<StoreComment> getCommByStore(Integer storeId);
	//app
	String[] findStoreById(Integer storeId);
	void sendMessage(Message addMsg);
}
