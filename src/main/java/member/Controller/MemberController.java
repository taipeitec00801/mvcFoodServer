package member.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("Member")
public class MemberController {

	@RequestMapping("/member_setting")
	public String member_setting() {		
		return "member_setting";
	}
}
