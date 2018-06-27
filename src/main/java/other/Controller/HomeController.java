package other.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("Home")
public class HomeController {

	
	@RequestMapping("/")
	public String index() {		
		return "index";
	}
	@RequestMapping("/sort")
	public String sort() {		
		return "sort";
	}
	@RequestMapping("/store_Info")
	public String store_Info() {		
		return "store_Info";
	}
}
