package other.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import other.Model.Store;
import other.Service.StoreService;

@Controller
public class SortController {
	
	@Autowired
	StoreService storeService;

	@RequestMapping("/sorts")
	public String getStore(@RequestParam("sortNo") Integer sortNum,
									@RequestParam("pages") Integer pages, Model model) {
		storeService.setPageNo(pages);
		List<Store> list = new ArrayList<Store>();
		if (sortNum < 10 && sortNum >= 0) {
			list = storeService.getStoreBySortNum(sortNum);
		} else {
			list = storeService.getAllStores();
		}
		model.addAttribute("totalPages", storeService.getTotalPages(sortNum));
		model.addAttribute("stores", list);
		model.addAttribute("sortNumber", sortNum);
		return "sorts";
	}
		
//	@RequestMapping("/sort")
//	public String pages(@RequestParam("pages") Integer pages, Model model) {
//		storeService.setPageNo(pages);
//		List<Store> list = storeService.getAllStores();
//		model.addAttribute("totalPages", storeService.getTotalPages(-1));
//		model.addAttribute("stores", list);
//		return "sorts";
//	}

}
