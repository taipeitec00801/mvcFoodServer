package member.Service;



import java.util.List;

import member.Model.Member;
import other.Model.Store;

public interface MemberService2 {

	List<Member> getAllMember();

	boolean checkEmail(String email);

	int addMember(Member mb);

	String getUserPass(String userId);
	
	Member getMember(String userId);
	
	List<Store> getMemberLikeStore(String[] like);
}
