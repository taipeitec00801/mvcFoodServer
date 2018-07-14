package shoppingCart.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
				throw new RuntimeException("getGiftPicture的getOnePicture()發生SQLException: " + e.getMessage());
			}
		}
		// headers.setCacheControl(CacheControl.noC);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping("/gift_Info")
	public String gift_Info(@RequestParam("giftId") Integer giftId, Model model) {
		Gift gift = new Gift();
		Integer number = 0;
		gift = service.getGiftById(giftId);
		model.addAttribute("gift", gift);
		return "gift_Info";
	}

	@RequestMapping("/cartMain")
	public String cartMain(Model model, ServletRequest request, ServletResponse response) {
		// System.out.println("-----------來了--------------");

		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			// **********Remember Me****************
			String cookieName = "";
			String giftList = "";
			Integer cartCount = 0;
			List<Gift> lg = new ArrayList<>();
			Cookie[] cookies = req.getCookies();
			if (cookies != null) { // 如果含有Cookie
				System.out.println("開始找Cookie");
				for (int i = 0; i < cookies.length; i++) {
					// 檢視每個Cookie
					cookieName = cookies[i].getName();
					if (cookieName.equals("giftList")) {
						// 找到user這個Cookie
						giftList = cookies[i].getValue();
						System.out.println("找到giftList" + giftList);

						String[] gifts = giftList.split(",");
						String[] b = null;
						for (String s : gifts) {
							b = s.split("-");
							for(int e = 0 ; e < (b.length / 2) ; e++) {
								Gift gift = service.getGiftById(Integer.parseInt(b[e]));
								gift.setGiftId(Integer.parseInt(b[e + 1]));
								lg.add(gift);
							}
						}
						if(b != null) {							
							cartCount = gifts.length;
						}
					}
				}
			}

			model.addAttribute("orderList", lg);
			model.addAttribute("cartCount", cartCount);
		}
		return "car_bar";
	}

	@RequestMapping("/cartAddGift")
	public String cartAddGift(@RequestParam("giftId") Integer giftId, @RequestParam("number") Integer number,
			Model model, ServletRequest request , ServletResponse response) {
		System.out.println("----------------number=" + number);
		System.out.println("----------------giftId=" + giftId);
		Gift gift = new Gift();
		gift = service.getGiftById(giftId);
		// Cookie giftList = new Cookie("giftList", "");
		String cookieName = "";
		String giftList = "";

		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				System.out.println("開始找Cookie");
				for (int i = 0; i < cookies.length; i++) {
					cookieName = cookies[i].getName();
					if (cookieName.equals("giftList")) {
						giftList = cookies[i].getValue();
						System.out.println("找到giftList" + giftList);
						String newList = giftList + "," + giftId + "-" + number;
						Cookie newCookie = new Cookie("giftList" , newList);
						newCookie.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
						newCookie.setPath(req.getContextPath());
						((HttpServletResponse) response).addCookie(newCookie);
					}
				}
			}
			if(giftList.equals("")) {	
				String newList = giftId + "-" + number;
				Cookie newCookie = new Cookie("giftList" , newList);
				newCookie.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
				newCookie.setPath(req.getContextPath());
				((HttpServletResponse) response).addCookie(newCookie);
			}
		}

		// model.addAttribute("gift", gift);
		return "gift_Info";
	}

}
