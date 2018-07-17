package other.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import member.Model.Member;
import other.Model.Message;
import other.Model.Store;
import other.Model.StoreComment;
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
	public List<Store> getAllStores(String features) {
		return storeDao.getAllStores(features);
	}

	@Override
	public List<Store> getStoreBySortNum(Integer sortNum, String features) {
		return storeDao.getStoreBySortNum(sortNum, features);
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
	public List<Store> getStoreByName(String myRequest, String features) {
		return storeDao.getStoreByName(myRequest, features);
	}

	@Override
	public List<StoreComment> getTopComm() {
		return storeDao.getTopComm();
	}

	@Override
	public List<StoreComment> getCommByStore(Integer storeId) {
		return storeDao.getCommByStore(storeId);
	}

	@Override
	public Member getMemberById(Integer memberId) {
		return storeDao.getMemberById(memberId);
	}

	@Override
	public StoreComment getCommentById(Integer commentId) {
		return storeDao.getCommentById(commentId);

	}

	@Override
	public List<Message> getMessageByComm(Integer msgCId) {
		return storeDao.getMessageByComm(msgCId);
	}

	@Override
	public int updateStoreComment(StoreComment sc) {

		return storeDao.updateStoreComment(sc);
	}

	// app
	@Override
	public String[] findStoreById(Integer storeId) {
		return storeDao.findStoreById(storeId);
	}

	@Override

	public Integer updateStRecomYNByMSId(Member member, Store store, Integer recomYN) {
		return storeDao.updateStRecomYNByMSId(member, store, recomYN);
	}

	@Override
	public Integer getStRecomYNByMSId(Member member, Store store) {
		return storeDao.getStRecomYNByMSId(member, store);
	}

	public void sendMessage(Message addMsg) {
		storeDao.sendMessage(addMsg);

	}

}
