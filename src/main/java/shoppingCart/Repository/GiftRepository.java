package shoppingCart.Repository;

import java.sql.Blob;
import java.util.List;

import member.Model.Member;
import other.Model.Store;
import shoppingCart.Model.Gift;

public interface GiftRepository {

	List<Gift> getAllGift();

	Gift getGiftById(Integer giftId);

}
