package shoppingCart.Service;

import java.sql.Blob;
import java.util.List;

import shoppingCart.Model.Gift;

public interface GiftService {

	List<Gift> getAllGift();

	Gift getGiftById(Integer giftId);

	
	
}
