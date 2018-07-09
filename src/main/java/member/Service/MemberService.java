package member.Service;



import member.Model.Member;

public interface MemberService {

	boolean checkAccount(String userAccount);

	int updateMemberDate(Member mb);

	int insertMemberDate(Member mb);

	Member getUserDateNoPortrait(String userAccount);
	
	boolean checkACPassword(String userAccount, String userPassword);
	
	Member getUserId(String userAccount);
}
