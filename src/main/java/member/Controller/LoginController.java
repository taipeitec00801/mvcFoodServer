package member.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import javaClass.GlobalService;
import member.Model.Member;
import member.Service.MemberService2;

@Controller
public class LoginController {

	@Autowired
	MemberService2 service;
	
	
	
	@Autowired
	ServletContext context;

	@RequestMapping("/member9487")
	public String memberPage() {
		return "Registered";
	}

	@RequestMapping("add")
	public void addMember(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String nickName = request.getParameter("nickName");
		String brithDay = request.getParameter("brithDay");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(brithDay);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		Integer gender = Integer.parseInt(request.getParameter("gender"));
		Member mb = new Member(userId, GlobalService.getMD5Endocing(userPassword), nickName, sqlDate, gender);
		int checked = service.addMember(mb);
		// int checked =
		String result = "{\"memberAdd\":\"" + checked + "\"}";
		PrintWriter out = null;
		System.out.println(result);
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("googleAdd")
	public String googleAdd(HttpServletRequest request, HttpServletResponse response) {	
		
		String userId = request.getParameter("userId");
		String password = userId.split("@")[0] + "00801";
		String rememberMe = "true";
		boolean checkEmail = service.checkEmail(userId);
		if(checkEmail) {
			String brithDay = "2018/07/19";
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = sdf.parse(brithDay);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			Integer gender = 1;
			Member mb = new Member(userId,
					GlobalService.getMD5Endocing(password),
					userId.split("@")[0],
					sqlDate
					,gender);
			service.addMember(mb);
		}else{
			password = service.getUserPass(userId);
		}		
		request.setAttribute("userId", userId);
		request.setAttribute("userPassword", password);
		request.setAttribute("rmMe", rememberMe);
		
		return "forward:login";
	}
	
	
	@RequestMapping("/member9487/member8877")
	public String showAllMember(Model model) {
		List<Member> list = service.getAllMember();
		model.addAttribute("memberList99", list);
		return "allMember63";
	}

	@RequestMapping("checkEmail")
	@ResponseBody
	public boolean checkEmail(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		boolean checked = service.checkEmail(userId);
		// 帳號重複
		return checked;
	}

	@RequestMapping("/memberLogin22")
	public String memberMain() {
		return "redirect:/";
	}

	@RequestMapping("/member9487/testLogin88")
	public void testLogin(ServletRequest request, ServletResponse response) {
		Gson gson = new Gson();
		String jsonString = "";
		System.out.println("開始測試登入");
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			// **********Remember Me****************
			String cookieName = "";
			String user = "";
			Cookie[] cookies = req.getCookies();
			if (cookies != null) { // 如果含有Cookie
				System.out.println("開始找Cookie");
				for (int i = 0; i < cookies.length; i++) {
					// 檢視每個Cookie
					cookieName = cookies[i].getName();
					if (cookieName.equals("user")) {
						// 找到user這個Cookie
						user = cookies[i].getValue();
						System.out.println("找到user" + user);
						jsonString = gson.toJson(service.getMember(user));
					}
				}
				PrintWriter out = null;
				System.out.println("jsonString= " + jsonString);
				response.setContentType("application/json");
				try {
					out = response.getWriter();
					out.write(jsonString);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@RequestMapping("rememberMe")
	@ResponseBody
	public boolean rememberMe(ServletRequest request, ServletResponse response) {
		System.out.println("開始測試rememberMe");
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			// **********Remember Me****************
			String cookieName = "";
			String user = null;
			String password = null;
			Cookie[] cookies = req.getCookies();
			if (cookies != null) { // 如果含有Cookie
				System.out.println("開始找Cookie");
				for (int i = 0; i < cookies.length; i++) {
					// 檢視每個Cookie
					cookieName = cookies[i].getName();
					if (cookieName.equals("user")) {
						// 找到user這個Cookie
						user = cookies[i].getValue();
					} else if (cookieName.equals("password")) {
						// 找到password這個Cookie
						password = cookies[i].getValue();						
					}
				}
				System.out.println("-----------" + user + "-----------");
				System.out.println("-----------" + password + "-----------");
				if (password != null &&user != null) {
					if(password.equals(service.getUserPass(user))) {
						System.out.println("---------帳號密碼正確---------");
						Member mb = service.getMember(user);
						HttpSession session = ((HttpServletRequest) request).getSession();
						session.setAttribute("LoginOK", mb);
						return true;
					}
				}else {
					System.out.println("---------找不到帳號密碼---------");
					return false;
				}
			}
		}
		return false;
	}

	@RequestMapping("login")
	@ResponseBody
	public boolean loginMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. 讀取使用者輸入資料
		String userId = request.getParameter("userId");
		System.out.println("------------" + userId + "-------------");
		String password = request.getParameter("userPassword");
		if(password == null) {
			password = (String) request.getAttribute("userPassword");
		}
		System.out.println("------------" + password + "-------------");
		HttpSession session = request.getSession();
		boolean b = false;
		boolean checkEmail = service.checkEmail(userId);
		if (!checkEmail) {
			// 有這個帳號才做查詢密碼的動作
			String rm = request.getParameter("rmMe");
			if(rm == null) {
				rm = (String) request.getAttribute("rmMe");
			}
			// **********Remember Me****************************
			Cookie cookieUser = null;
			Cookie cookiePassword = null;
			Cookie cookieRememberMe = null;
			// rm存放瀏覽器送來之RememberMe的選項，如果使用者對RememberMe打勾，rm就不會是null
			if (rm != null) {
				cookieUser = new Cookie("user", userId);
				cookieUser.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
				cookieUser.setPath(request.getContextPath());
				if(request.getAttribute("userPassword") != null) {
					cookiePassword = new Cookie("password", password);
				}else {					
					String encodePassword = GlobalService.getMD5Endocing(password);
					cookiePassword = new Cookie("password", encodePassword);
				}
				cookiePassword.setMaxAge(7 * 24 * 60 * 60);
				cookiePassword.setPath(request.getContextPath());

				cookieRememberMe = new Cookie("rm", "true");
				cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
				cookieRememberMe.setPath(request.getContextPath());
			} else { // 使用者沒有對 RememberMe 打勾
				cookieUser = new Cookie("user", userId);
				cookieUser.setMaxAge(7 * 24 * 60 * 60); // MaxAge==0 表示要請瀏覽器刪除此Cookie
				cookieUser.setPath(request.getContextPath());

				String encodePassword = GlobalService.getMD5Endocing(password);
				cookiePassword = new Cookie("password", encodePassword);
				cookiePassword.setMaxAge(0);
				cookiePassword.setPath(request.getContextPath());

				cookieRememberMe = new Cookie("rm", "false");
				cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
				cookieRememberMe.setPath(request.getContextPath());
			}
			response.addCookie(cookieUser);
			response.addCookie(cookiePassword);
			response.addCookie(cookieRememberMe);
			String checkPass = service.getUserPass(userId);
			if (GlobalService.getMD5Endocing(password).equals(checkPass)) {
				// 密碼正確
				Member mb = service.getMember(userId);
				session.setAttribute("LoginOK", mb);
				return !b;
			} else {
				// 密碼錯誤
				return b;
			}
		} else {
			// 沒這帳號直接返回，不做查詢密碼的動作
			return b;
		}
	}

	@RequestMapping("/member9487/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;
		cookieUser = new Cookie("user", "");
		cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
		cookieUser.setPath(request.getContextPath());

		cookiePassword = new Cookie("password", "");
		cookiePassword.setMaxAge(0);
		cookiePassword.setPath(request.getContextPath());

		cookieRememberMe = new Cookie("rm", "false");
		cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
		cookieRememberMe.setPath(request.getContextPath());

		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		response.addCookie(cookieRememberMe);
	}

	@RequestMapping("getMemberImg")
	public void getMemberImg(HttpServletRequest request, HttpServletResponse resp) {
		Blob blob = service.getMember(request.getParameter("userId")).getPortrait();
		int len = 0;
		byte[] media = null;
		if (blob != null) {
			try {
				len = (int) blob.length();
				media = blob.getBytes(1, len);
			} catch (SQLException e) {
				throw new RuntimeException("ProductController的getPicture()發生SQLException: " + e.getMessage());
			}
		} else {
			InputStream is = context.getResourceAsStream("images/liam-stahnke-261528-unsplash.jpg");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] b = new byte[8192];
			try {
				while ((len = is.read(b)) != -1) {
					baos.write(b, 0, len);
				}
			} catch (Exception e) {
				throw new RuntimeException("ProductController的getPicture()發生IOException: " + e.getMessage());
			}
			media = baos.toByteArray();
		}
		String encoded = Base64.getEncoder().encodeToString(media);
		Gson gson = new Gson();
		String jsonString = gson.toJson(encoded);
		PrintWriter out = null;
		// System.out.println("jsonString= " + jsonString);
		resp.setContentType("application/json");
		try {
			out = resp.getWriter();
			out.write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
