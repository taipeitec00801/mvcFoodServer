package other.Controller;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import other.Model.Store;
import other.Model.StoreComment;
import other.Repository.impl.StoreDaoImpl;
import other.Service.StoreService;

@Controller
public class HomeController {
	
	@Autowired
	StoreService storeService;


	@RequestMapping("/")
	public String index(@RequestParam(value="userPref",defaultValue = "null") String userPref,Model model) {
		List<Store> list = new ArrayList<Store>();
		List<StoreComment> comList = new ArrayList<>();
		List<String> contentList = new ArrayList<>();
		if(userPref == null)
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
	public String search(@RequestParam(value="mySearch") String myRequest, 
			@RequestParam("pages") Integer pages, Model model) {
		if (myRequest != null) {
			storeService.setPageNo(pages);
			List<Store> list = new ArrayList<Store>();
			System.out.println("---------------------------------------" + myRequest);
			list = storeService.getStoreByName(myRequest);			
			model.addAttribute("totalPages",(int) Math.ceil(list.size()/ (double) StoreDaoImpl.RECORDS_PER_PAGE));
			model.addAttribute("stores", list);
			return "search";
		} else {
			//需改成不跳頁
			return "redirect:/";
		}		
	}
	

	

//	@RequestMapping("/member_setting")
//	public String member_setting() {
//		return "member_setting";
//	}
	
	
	
	
	
}
