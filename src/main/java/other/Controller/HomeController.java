package other.Controller;

import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import member.Model.Member;
import member.Service.MemberService;
import other.Model.Store;
import other.Model.StoreComment;
import other.Repository.impl.StoreDaoImpl;
import other.Service.StoreService;

@Controller
public class HomeController {
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	MemberService memberService;


	@RequestMapping("/")
	public String index(Model model,HttpServletResponse response,HttpServletRequest request) {
		List<Store> list = new ArrayList<Store>();
		List<StoreComment> comList = new ArrayList<>();
		List<String> contentList = new ArrayList<>();
		//get User Data
		Member mb = null;
		String userPref = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			 for (Cookie cookie : cookies) {
			   if (cookie.getName().equals("user")) {
				   mb = memberService.getMemberByAccount(cookie.getValue());
				   System.out.println("UserPreference!!!!"+mb.getPreference());
				   userPref = mb.getPreference();
				   //取得使用者偏好
			    }
			  }
			}
		if(userPref != null)
			list = storeService.getUserStores(userPref);
		else
			list = storeService.getTopStores();
		comList = storeService.getTopComm();
		//Clob to Sring
		for(int i=0;i<comList.size();i++) {
			Clob clob = comList.get(i).getCommentContent();
			try {
				String clobString = clob.getSubString(1, (int) clob.length());
				contentList.add(clobString);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("contentList:"+contentList.size());
		model.addAttribute("stores", list);
		model.addAttribute("storeComments",comList);
		model.addAttribute("contentList",contentList);
		return "index";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "redirect:/";
	}


	
	@RequestMapping("/search")
	public String search(@RequestParam(value="mySearchReq") String myRequest, 
						@RequestParam(value="nowPosition") String nowPosition, 
			@RequestParam("pages") Integer pages, Model model) {
		System.out.println("搜尋內容---------------\"" + myRequest + "\"--length--" + myRequest.length());
		System.out.println("原本頁面---------------\"" + nowPosition);
		if (myRequest.length() > 0) {
			storeService.setPageNo(pages);
			List<Store> list = new ArrayList<Store>();
			list = storeService.getStoreByName(myRequest, "web");			
			model.addAttribute("totalPages",(int) Math.ceil(list.size()/ (double) StoreDaoImpl.RECORDS_PER_PAGE));
			model.addAttribute("stores", list);
			model.addAttribute("requestSize", list.size());
			model.addAttribute("searchReq", myRequest);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String nowTime = sdf.format(date);
			model.addAttribute("nowTime", nowTime);
			
			return "search";
		} else {
			int slash = nowPosition.indexOf("S");
			//不跳頁
			String NoPageJump = nowPosition.substring(slash+6);
			System.out.println(NoPageJump);
			return "redirect:" + NoPageJump;
		}		
	}
	

	

//	@RequestMapping("/member_setting")
//	public String member_setting() {
//		return "member_setting";
//	}
	
	
	
	
	
}
