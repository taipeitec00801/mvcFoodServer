package member.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import member.Model.Member;
import member.Repository.MemberDao;
import member.Service.MemberService;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;

	public MemberServiceImpl() {
	}

	@Override
	public boolean checkAccount(String userAccount) {

		return dao.checkAccount(userAccount);
	}

	@Override
	public int updateMemberDate(Member mb) {

		return dao.updateMemberDate(mb);
	}

	@Override
	public int insertMemberDate(Member mb) {

		return dao.insertMemberDate(mb);
	}

	@Override
	public Member getUserDateNoPortrait(String userAccount) {

		return dao.getUserDateNoPortrait(userAccount);
	}

	@Override
	public boolean checkACPassword(String userAccount, String userPassword) {
		boolean exist = dao.checkACPassword(userAccount, userPassword);
		return exist;
	}

	@Override
	public Member getUserId(String userAccount) {

		return dao.getUserId(userAccount);
	}

}
