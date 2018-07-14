package shoppingCart.Repository;

import java.util.List;

import shoppingCart.Model.Gift;

public interface GiftRepository {

	List<Gift> getAllGift();
	Gift getGiftById(Integer giftId);

}
