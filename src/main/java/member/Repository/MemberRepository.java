package member.Repository;

import java.util.List;

import member.Model.Member;

public interface MemberRepository {

	List<Member> getAllMember();

	boolean checkEmail(String email);

	int addMember(Member mb);

	String getUserPass(String userId);

	Member getMember(String userId);
}