package shoppingCart.Service;

import java.util.List;

import member.Model.Member;
import shoppingCart.Model.Gift;
import shoppingCart.Model.OrderMain;
import shoppingCart.Model.OrderItem;

public interface GiftService {

	List<Gift> getAllGift();
	Gift getGiftById(Integer giftId);	
	Integer setOrder(OrderMain order);
	OrderMain getOrderByMember(Member member);
	void setOrderItem(OrderItem oi);
	OrderMain getOrderByNo(Integer orderNo);
	Gift getGiftByOrderItem(OrderItem oi);
	List<OrderItem> getOrderitemByOrder(OrderMain order);
	void updateOrder(OrderMain order);
	boolean checkMemberOrder(Member member);
	
}
