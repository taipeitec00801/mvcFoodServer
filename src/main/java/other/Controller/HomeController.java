package other.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import other.Service.StoreService;

@Controller("Home")
public class HomeController {
	
	@Autowired
	StoreService storeService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "index";
	}

	@RequestMapping("/store_Info")
	public String store_Info() {
		return "store_Info";
	}

//	@RequestMapping("/member_setting")
//	public String member_setting() {
//		return "member_setting";
//	}
	
	
	
	
	
}
