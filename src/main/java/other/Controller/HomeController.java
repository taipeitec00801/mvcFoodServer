package other.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import other.Model.Store;
import other.Service.StoreService;

@Controller("Home")
public class HomeController {
	
	@Autowired
	StoreService storeService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/sorts")
	public String sort(Model model) {
		List<Store> list = storeService.getAllStores();
		model.addAttribute("stores", list);
		return "sorts";
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
