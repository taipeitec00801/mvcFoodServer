package member.Repository;

import java.sql.Blob;
import java.sql.Date;

import member.Model.Member;

public interface MemberDao {
	
	
	
	boolean checkAccount(String userAccount);
	
	int updateMemberDate(Member mb);
	
	int insertMemberDate(Member mb);
	
	
	Member getUserDateNoPortrait(String userAccount);

	boolean checkACPassword(String userAccount, String userPassword);

	Member getUserId(String userAccount);

	void updateMemInfo(Integer memberId, String nickname, String birthday, Blob portrait);

}