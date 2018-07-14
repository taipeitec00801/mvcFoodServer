package shoppingCart.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shoppingCart.Model.Gift;
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

}
