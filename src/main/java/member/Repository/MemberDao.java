package member.Repository;

import java.sql.Blob;
import java.util.List;

import member.Model.Member;
import other.Model.Store;

public interface MemberDao {
	
	//------哲瑋-----------------------------------------------------------------------------
	boolean checkAccount(String userAccount);
	int updateMemberDate(Member mb);
	int insertMemberDate(Member mb);
	Member getMemberByAccount(String userAccount);	
	boolean checkACPassword(String userAccount, String userPassword);	
	void updateMemInfo(Integer memberId, String nickname, String birthday, Blob portrait);
	
	//------冠禎-----------------------------------------------------------------------------
	List<Member> getAllMember();
	boolean checkEmail(String email);
	int addMember(Member mb);
	String getUserPass(String userId);	
	Member getMember(String userId);
	//非會員
	List<Store> getMemberLikeStore(String[] like);

}