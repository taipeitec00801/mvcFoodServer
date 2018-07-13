package shoppingCart.Service.impl;

import java.sql.Blob;
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
	GiftRepository repository;
	
	@Override
	public List<Gift> getAllGift() {
		// TODO Auto-generated method stub
		return repository.getAllGift();
	}

	@Override
	public Gift getGiftById(Integer giftId) {
		// TODO Auto-generated method stub
		return repository.getGiftById(giftId);
	}

}
