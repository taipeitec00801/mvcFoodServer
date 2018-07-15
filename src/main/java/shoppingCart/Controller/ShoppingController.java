package shoppingCart.Controller;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.ResponseBody;

import member.Model.Member;
import member.Service.MemberService;
import shoppingCart.Model.Gift;
import shoppingCart.Model.OrderMain;
import shoppingCart.Model.OrderItem;
import shoppingCart.Service.GiftService;

@Controller
public class ShoppingController {

	@Autowired
	GiftService service;

	@Autowired
	MemberService mService;

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
				for (int i = 0; i < cookies.length; i++) {
					// 檢視每個Cookie
					cookieName = cookies[i].getName();
					if (cookieName.equals("giftList")) {
						// 找到user這個Cookie
						giftList = cookies[i].getValue();

						String[] gifts = giftList.split("%2C");
						String[] b = null;
						for (String s : gifts) {
							b = s.split("-");
							for (int e = 0; e < (b.length / 2); e++) {
								Gift gift = service.getGiftById(Integer.parseInt(b[e]));
								gift.setGiftId(Integer.parseInt(b[e + 1]));
								lg.add(gift);
							}
						}
						if (b != null) {
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
			Model model, ServletRequest request, ServletResponse response) {
		String cookieName = "";
		String giftList = "";

		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					cookieName = cookies[i].getName();
					if (cookieName.equals("giftList")) {
						giftList = cookies[i].getValue();
						String newList = giftList + "%2C" + giftId + "-" + number;
						Cookie newCookie = new Cookie("giftList", newList);
						newCookie.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
						newCookie.setPath(req.getContextPath());
						((HttpServletResponse) response).addCookie(newCookie);
					}
				}
			}
			if (giftList.equals("")) {
				String newList = giftId + "-" + number;
				Cookie newCookie = new Cookie("giftList", newList);
				newCookie.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
				newCookie.setPath(req.getContextPath());
				((HttpServletResponse) response).addCookie(newCookie);
			}
		}

		// model.addAttribute("gift", gift);
		return "gift_Info";
	}

	@RequestMapping("/member9487/cartAddGift")
	public String buyMain(Model model, ServletRequest request, ServletResponse response) {
		String cookieName = "";
		String giftList = "";
		Integer cartCount = 0;
		List<Gift> lg = new ArrayList<>();
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					cookieName = cookies[i].getName();
					if (cookieName.equals("giftList")) {
						// 找到user這個Cookie
						giftList = cookies[i].getValue();

						String[] gifts = giftList.split("%2C");
						String[] b = null;
						for (String s : gifts) {
							b = s.split("-");
							for (int e = 0; e < (b.length / 2); e++) {
								Gift gift = service.getGiftById(Integer.parseInt(b[e]));
								gift.setGiftContent(b[e + 1]);
								lg.add(gift);
							}
						}
						if (b != null) {
							cartCount = gifts.length;
						}
					}
				}
				model.addAttribute("orderList", lg);
				model.addAttribute("cartCount", cartCount);
			}
		}
		return "buyMain";
	}

	@RequestMapping("/member9487/buyMain2")
	public String buyMain2(Model model, ServletRequest request, ServletResponse response) {
		String cookieName = "";
		String giftList = "";
		Integer cartCount = 0;
		List<Gift> lg = new ArrayList<>();
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					cookieName = cookies[i].getName();
					if (cookieName.equals("giftList")) {
						// 找到user這個Cookie
						giftList = cookies[i].getValue();

						String[] gifts = giftList.split("%2C");
						String[] b = null;
						for (String s : gifts) {
							b = s.split("-");
							for (int e = 0; e < (b.length / 2); e++) {
								Gift gift = service.getGiftById(Integer.parseInt(b[e]));
								gift.setGiftContent(b[e + 1]);
								lg.add(gift);
							}
						}
						if (b != null) {
							cartCount = gifts.length;
						}
					}
				}
				model.addAttribute("orderList", lg);
				model.addAttribute("cartCount", cartCount);
			}
		}
		return "buyMain2";
	}

	@RequestMapping("/member9487/orderConfirm")
	public void orderConfirm(HttpServletRequest request, HttpServletResponse response) {

		String[] giftAndNum = request.getParameter("orderList").split(",");
		String userAccount = request.getParameter("user");
		Date date = new Date(System.currentTimeMillis());
		Member member = mService.getMember(userAccount);
		OrderMain order = new OrderMain(member, date, 1);
		service.setOrder(order);
		OrderMain newOrder = service.getOrderByMember(member);
		OrderItem oi = null;
		for (String s : giftAndNum) {
			String[] test = s.split("-");
			Gift gift = service.getGiftById(Integer.parseInt(test[0]));
			// System.out.println("-------------" + test[1] + "------------------------");
			Integer quantity = Integer.parseInt(test[1]);
			oi = new OrderItem(newOrder, gift, quantity);
			service.setOrderItem(oi);
		}
		request.setAttribute("user", userAccount.split("@")[0]);
		request.setAttribute("orderNo", newOrder.getOrderNo());

		// return "forward:/member9487/orderSuccess";
	}

	@RequestMapping("/member9487/orderSuccess")
	public String orderSuccess(HttpServletRequest request, Model model) {

		String cookieName = "";
		String user = "";
		Member member = null;
		OrderMain order = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) { // 如果含有Cookie
			for (int i = 0; i < cookies.length; i++) {
				// 檢視每個Cookie
				cookieName = cookies[i].getName();
				if (cookieName.equals("user")) {
					// 找到user這個Cookie
					user = cookies[i].getValue();
					member = mService.getMember(user);
				}
			}
			order = service.getOrderByMember(member);
		}

		// Integer orderNo = (Integer) request.getAttribute("orderNo");
		// Order order = service.getOrderByNo(orderNo);

		model.addAttribute("user", member.getNickname());
		model.addAttribute("orderMain", order);

		// System.out.println("--------------------準備跳轉至buyMain3--------------------");

		return "buyEnd";
	}

	@RequestMapping("/member9487/myOrder")
	public String goToOrder(HttpServletRequest request, Model model) {

		Member member = null;
		OrderMain order = null;
		String cookieName = "";
		String user = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) { // 如果含有Cookie
			for (int i = 0; i < cookies.length; i++) {
				// 檢視每個Cookie
				cookieName = cookies[i].getName();
				if (cookieName.equals("user")) {
					// 找到user這個Cookie
					user = cookies[i].getValue();
					member = mService.getMember(user);
					order = service.getOrderByMember(member);
				}
			}
		}
		if (order != null) {
			List<OrderItem> oi = service.getOrderitemByOrder(order);
			List<Gift> giftList = new ArrayList<>();
			Double total = 0.0;
			for (int i = 0; i < oi.size(); i++) {
				Gift gift = service.getGiftByOrderItem(oi.get(i));
				total += oi.get(i).getQuantity() * gift.getGiftPrice();
				gift.setGiftContent(oi.get(i).getQuantity().toString());
				giftList.add(gift);
			}
			String orderStatus = "等待付款";

			if (order.isCommit() == 0) {
				orderStatus = "訂單完成";
			}
			// model.addAttribute("user", member.getNickname());
			model.addAttribute("orderMain", order);
			model.addAttribute("total", total);
			model.addAttribute("orderStatus", orderStatus);
			model.addAttribute("orderList", giftList);
		}
		return "myOrder";
	}

	@RequestMapping("/member9487/reflash8787")
	public String reflash(HttpServletRequest request) {

		System.out.println("*******************reflash***********************");

		Member member = null;
		OrderMain order = null;
		String cookieName = "";
		String user = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) { // 如果含有Cookie
			System.out.println("開始找Cookie");
			for (int i = 0; i < cookies.length; i++) {
				// 檢視每個Cookie
				cookieName = cookies[i].getName();
				if (cookieName.equals("user")) {
					// 找到user這個Cookie
					user = cookies[i].getValue();
					member = mService.getMember(user);
				}
			}
			order = service.getOrderByMember(member);
		}

		service.updateOrder(order);

		return "redirect:/member9487/myOrder";
	}
	
	
	@RequestMapping("/member9487/orderTest")
	@ResponseBody
	public boolean orderTest(HttpServletRequest request) {
		Member member = null;
		String cookieName = "";
		String user = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) { // 如果含有Cookie
			for (int i = 0; i < cookies.length; i++) {
				// 檢視每個Cookie
				cookieName = cookies[i].getName();
				if (cookieName.equals("user")) {
					// 找到user這個Cookie
					user = cookies[i].getValue();
					member = mService.getMember(user);
					
				}
			}
		}
		boolean b = service.checkMemberOrder(member);
		return b;
	}	
}
