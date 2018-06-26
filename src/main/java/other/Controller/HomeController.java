package other.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("Home")
public class HomeController {

	
	@RequestMapping("/")
	public String index() {		
		return "index";
	}
	
}
