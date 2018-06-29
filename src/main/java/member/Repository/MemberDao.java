package member.Repository;

import member.Model.Member;

public interface MemberDao {
	Member getMemberById(Integer memberId);
}
