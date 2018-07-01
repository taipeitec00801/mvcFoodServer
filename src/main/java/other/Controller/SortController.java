package other.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import other.Model.Store;
import other.Service.StoreService;

@Controller("Sort")
public class SortController {
	
	@Autowired
	StoreService storeService;

	@RequestMapping("/sorts/{sortNum}")
	public String getProductByCategory(@PathVariable("sortNum") Integer sortNum, Model model) {
		List<Store> list = storeService.getStoreBySortNum(sortNum);
		model.addAttribute("stores", list);
		model.addAttribute("pages", pagination(list));
		return "sorts";
	}
	
	
	@RequestMapping("/sorts")
	public String sort(Model model) {
		List<Store> list = storeService.getAllStores();
		model.addAttribute("stores", list);				
		model.addAttribute("pages", pagination(list));
		return "sorts";
	}

	private int pagination(List<Store> list) {
		int pages = 0;
		if (list.size() > 9) {
			pages = list.size()/9;	
			System.out.println(pages);
		}
		return pages;
	}
}
