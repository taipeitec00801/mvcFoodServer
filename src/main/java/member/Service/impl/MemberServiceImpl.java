package member.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import member.Model.Member;
import member.Repository.MemberDao;
import member.Service.MemberService;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;


	@Override
	public Member getMemberById(Integer memberId) {
		return memberDao.getMemberById(memberId);
	}
	
}
