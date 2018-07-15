package shoppingCart.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import member.Model.Member;
import shoppingCart.Model.Gift;
import shoppingCart.Model.OrderMain;
import shoppingCart.Model.OrderItem;
import shoppingCart.Repository.GiftRepository;
import shoppingCart.Service.GiftService;

@Transactional
@Service
public class GiftServiceImpl implements GiftService {

	@Autowired
	GiftRepository giftRepository;
	
	@Override
	public List<Gift> getAllGift() {
		return giftRepository.getAllGift();
	}

	@Override
	public Gift getGiftById(Integer giftId) {
		return giftRepository.getGiftById(giftId);
	}

	@Override
	public Integer setOrder(OrderMain order) {
		// TODO Auto-generated method stub
		return giftRepository.setOrder(order);
	}

	@Override
	public OrderMain getOrderByMember(Member member) {
		// TODO Auto-generated method stub
		return giftRepository.getOrderByMember(member);
	}

	@Override
	public void setOrderItem(OrderItem oi) {
		// TODO Auto-generated method stub
		giftRepository.setOrderItem(oi);
	}

	@Override
	public OrderMain getOrderByNo(Integer orderNo) {
		// TODO Auto-generated method stub
		return giftRepository.getOrderByNo(orderNo);
	}

	@Override
	public Gift getGiftByOrderItem(OrderItem oi) {
		// TODO Auto-generated method stub
		return giftRepository.getGiftByOrderItem(oi);
	}

	@Override
	public List<OrderItem> getOrderitemByOrder(OrderMain order) {
		// TODO Auto-generated method stub
		return giftRepository.getOrderitemByOrder(order);
	}

	@Override
	public void updateOrder(OrderMain order) {
		// TODO Auto-generated method stub
		giftRepository.updateOrder(order);
	}

	@Override
	public boolean checkMemberOrder(Member member) {
		// TODO Auto-generated method stub
		return giftRepository.checkMemberOrder(member);
	}
	
	

}
