package member.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import member.Model.Member;
import member.Repository.MemberRepository;
import member.Service.MemberService2;
import other.Model.Store;

@Service
@Transactional
public class MemberServiceImpl2 implements MemberService2 {

	@Autowired
	MemberRepository repository;

	
	@Override
	public List<Member> getAllMember() {
		return repository.getAllMember();
	}

	@Override
	public boolean checkEmail(String email) {
		return repository.checkEmail(email);
	}

	@Override
	public int addMember(Member mb) {
		return repository.addMember(mb);
	}

	@Override
	public String getUserPass(String userId) {
		return repository.getUserPass(userId);
	}

	@Override
	public Member getMember(String userId) {
		return repository.getMember(userId);
	}

	@Override
	public List<Store> getMemberLikeStore(String[] like) {
		// TODO Auto-generated method stub
		return repository.getMemberLikeStore(like);
	}
	
	

}