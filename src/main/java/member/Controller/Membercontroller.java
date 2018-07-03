package member.Controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import member.Model.Member;
import member.Service.MemberService;


@Controller
public class Membercontroller {

	@Autowired
	MemberService memberService;
	
	
	                 
	@RequestMapping("/memInfo")			
	public String setting_memInfo(Model model, 
			HttpServletResponse response,
			HttpServletRequest request
//			@CookieValue("帳號") String mbCookie,會員登入後不須自行設定Cookie,直接抓值就行
	) {
//		Cookie cookie = getCookie(request, cookieName);
		String userAccount = "hikarumiyasaki@gmail.com";
		Member mb = memberService.getUserDateNoPortrait(userAccount);
		Member mb1 = new Member();
		mb1.setUserAccount(mb.getUserAccount());
		mb1.setNickname(mb.getNickname());
		mb1.setBirthday(mb.getBirthday());
		model.addAttribute("userInfo",mb1);
		System.out.println(mb1.getUserAccount());

				Cookie cookieUser = null;

				if (mb != null) { 
					cookieUser = new Cookie("user", userAccount);
					cookieUser.setMaxAge(7 * 24 * 60 * 60);     // Cookie的存活期: 七天
					cookieUser.setPath(request.getContextPath());
					System.out.println(cookieUser.getValue());
				}
				response.addCookie(cookieUser);
		return "setting_memInfo";
	}
	@RequestMapping("/foto")			
	public String setting_memInfo_foto(Model model) {
		
		return "foto";
	}

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model,
			@CookieValue("user") String user) {
		System.out.println(user);
//		Contorller從setting_memInfo頁面收到/password的GET請求
//		因passwordChange.jsp頁功能是更換密碼,故需要在進入這一頁面同時給他一個物件來裝密碼,
//		並利用passwordChange.jsp的<form:>標籤來回傳物件並裝著密碼
		Member mb = new Member();
	    model.addAttribute("Member", mb); 
	    return "passwordChange";//送回foto.jsp
	}
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public String passwordChange(Model model,
			@RequestParam("password") String password,
			@RequestParam("password2") String password2,
			@CookieValue("user") String user
			) {
		System.out.println(user);
		System.out.println(password);
		System.out.println(password2);
		Member mb = memberService.getUserDateNoPortrait(user);
		mb.setUserPassword(password);
		memberService.updateMemberDate(mb);
			return "setting_memInfo";
			//需要修改
	}
	//當輸入第一筆舊密碼時,網頁執行ajax查詢密碼是否正確
	@RequestMapping(value = "/ajaxPassword.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean ajaxPasswordCheck(Model model,
			@CookieValue("user") String user,
			@RequestParam("oldPassword") String password) {
		boolean exist = memberService.checkACPassword(user, password);
		System.out.println("ajax執行結果 :" + exist);
		return exist;
	}

	@RequestMapping("/memberIfon")
	public @ResponseBody Member mbInfoServlet(String userAccount){
		System.out.println(userAccount);
		try {
			System.out.println(111);
			System.out.println(memberService.getUserDateNoPortrait(userAccount));
			System.out.println(222);
		} catch (Exception e) {
			System.out.println("發生例外: " + e.getMessage());
		}

		return memberService.getUserDateNoPortrait(userAccount);
	}
//	@RequestMapping("/memberIfon")
//	public Member mbInfoServlet(Model model){
//		System.out.println(model);
////		Member mb = memberService.getUserDateNoPortrait(model);
//		return null;
//	}
////	@RequestMapping("/member_setting")
//	public String member_setting() {
//		return "member_setting";
//	}
	@RequestMapping("/member_mainPage")
	public String member_mainPage() {
		return "member_mainPage";
	}
	@RequestMapping("/setting_memInfo")
	public String member_setting() {
		return "setting_memInfo";
	}
}
