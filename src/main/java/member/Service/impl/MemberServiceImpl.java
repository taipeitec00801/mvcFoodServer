package member.Service.impl;

import java.sql.Blob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import member.Model.Member;
import member.Repository.MemberDao;
import member.Service.MemberService;
import other.Model.Store;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;

	@Override
	public boolean checkAccount(String userAccount) {
		return memberDao.checkAccount(userAccount);
	}

	@Override
	public int updateMemberDate(Member mb) {
		return memberDao.updateMemberDate(mb);
	}

	@Override
	public int insertMemberDate(Member mb) {
		return memberDao.insertMemberDate(mb);
	}

	@Override
	public Member getMemberByAccount(String userAccount) {
		return memberDao.getMemberByAccount(userAccount);
	}

	@Override
	public boolean checkACPassword(String userAccount, String userPassword) {
		return memberDao.checkACPassword(userAccount, userPassword);
	}

	@Override
	public void updateMemInfo(Integer memberId, String nickname, String birthday, Blob portrait) {
		memberDao.updateMemInfo(memberId, nickname, birthday, portrait);
	}

	@Override
	public List<Member> getAllMember() {
		return memberDao.getAllMember();
	}

	@Override
	public boolean checkEmail(String email) {
		return memberDao.checkEmail(email);
	}

	@Override
	public int addMember(Member mb) {
		return memberDao.addMember(mb);
	}

	@Override
	public String getUserPass(String userId) {
		return memberDao.getUserPass(userId);
	}

	@Override
	public Member getMember(String userId) {
		return memberDao.getMember(userId);
	}

	@Override
	public List<Store> getMemberLikeStore(String[] like) {
		return memberDao.getMemberLikeStore(like);
	}

	@Override
	public int updatePreference(String userAccount, String preference) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePortrait(String userAccount, byte[] image) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int regisMemberDate(Member member, byte[] image) {
		// TODO Auto-generated method stub
		return 0;
	}
}
