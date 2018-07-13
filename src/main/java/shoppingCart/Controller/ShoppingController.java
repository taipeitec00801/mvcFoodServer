package shoppingCart.Controller;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import other.Model.Store;
import other.Model.StoreComment;
import shoppingCart.Model.Gift;
import shoppingCart.Service.GiftService;

@Controller
public class ShoppingController {

	@Autowired
	GiftService service;	
	
	
	@Autowired
	ServletContext context;

	@RequestMapping("/shoppingMain")
	public String mainPage(Model model) {
		List<Gift> giftList = new ArrayList<>();
		
		giftList = service.getAllGift();
		model.addAttribute("giftList", giftList);
		
		return "shoppingMain";
	}
	
	@RequestMapping(value = "/getGiftPicture/{giftId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getOnePicture(HttpServletResponse resp, @PathVariable Integer giftId) {
	    HttpHeaders headers = new HttpHeaders();
	    Blob blob = service.getGiftById(giftId).getGiftPicture();
	    int len = 0;
		byte[] media = null;
		if (blob != null) {
			try {
				len = (int) blob.length();
				media = blob.getBytes(1, len);
			} catch (SQLException e) {
				throw new RuntimeException("ProductController的getPicture()發生SQLException: " + e.getMessage());
			}
		}
	    //headers.setCacheControl(CacheControl.noC);
	    ResponseEntity<byte[]> responseEntity = 
					new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return responseEntity;
	}
	
	@RequestMapping("/gift_Info")
	public String store_Info(@RequestParam("giftId") Integer giftId, Model model) {
		Gift gift = new Gift();
		gift = service.getGiftById(giftId);
		model.addAttribute("gift", gift);
		return "gift_Info";
	}

}
