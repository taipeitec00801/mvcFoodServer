package member.Controller;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import member.Model.Member;
import member.Service.MemberService;
import other.Model.Store;
import other.Service.StoreService;


@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	@Autowired
	StoreService storeService;
	
	@Autowired
	ServletContext context;
	
	
	@RequestMapping("/memInfo")			
	public String setting_memInfo(Model model, 
			HttpServletResponse response,
			HttpServletRequest request
//			@CookieValue("帳號") String mbCookie,會員登入後不須自行設定Cookie,直接抓值就行
	) {
		Member mb = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			 for (Cookie cookie : cookies) {
			   if (cookie.getName().equals("user")) {
				   mb = memberService.getMemberByAccount(cookie.getValue());
				   //取得使用者帳號
			    }
			  }
			}
		
		Member mb1 = new Member();
		Member mb2 = new Member();
		
		mb1.setUserAccount(mb.getUserAccount());
		mb1.setNickname(mb.getNickname());
		mb1.setBirthday(mb.getBirthday());
		
		mb2.setUserAccount(mb.getUserAccount());
		model.addAttribute("member",mb1);
		model.addAttribute("foto",mb2);
		System.out.println("進入會員設定: "+mb1.getUserAccount());

		return "setting_memInfo";
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/memInfo", method = RequestMethod.POST)
	public String mbInfoChange(
			@ModelAttribute("member") Member mb,
//			@ModelAttribute("portrait") Member mbPortrait,
			@CookieValue("user") String user) {
		
		Member mbAllInfo = memberService.getMemberByAccount(user);
		Integer mbId = mbAllInfo.getMemberId();
		
//		=======================檔案上傳==========================
		
		MultipartFile mbImage = mb.getMbImage();
		String originalFilename = mbImage.getOriginalFilename();
		System.out.println("length: "+originalFilename.length());
		String rootDirectory = context.getRealPath("/");
		String ext = null;
		if(originalFilename.length() > 0) {

		ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		
		//  建立Blob物件，交由 Hibernate 寫入資料庫
		if (mbImage != null && !mbImage.isEmpty() ) {
			try {
				byte[] b = mbImage.getBytes();
				Blob blob = new SerialBlob(b);
//				Member mb1 = new Member();
//				mb1.setPortrait(blob);
//				mb1.setBirthday(mb.getBirthday());
//				mb1.setNickname(mb.getNickname());
				memberService.updateMemInfo(mbId, mb.getNickname(), mb.getBirthday(), blob);
				System.out.println("上傳成功");
			} catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}else {
			System.out.println("照片: "+mbImage.isEmpty());
		}
		
		}
//	    productService.addProduct(bb);
//	//  將上傳的檔案移到指定的資料夾
//			try {
//				File imageFolder = new File(rootDirectory, "images");
//				if (!imageFolder.exists()) imageFolder.mkdirs();
//				File file = new File(imageFolder, bb.getBookId() + ext);
//				productImage.transferTo(file);
//			} catch(Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
//			}
//	    return "redirect:/products";
		
//		=======================檔案上傳==========================
			return "redirect:/";
	}
	
	@RequestMapping(value = "/mbPicture/mbfoto", method = RequestMethod.GET)			
	public ResponseEntity<byte[]> setting_portrait(Model model, 
			HttpServletResponse response,
			HttpServletRequest request
	) {
		Member mb = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			 for (Cookie cookie : cookies) {
			   if (cookie.getName().equals("user")) {
				   mb = memberService.getMemberByAccount(cookie.getValue());
				   //取得使用者帳號
			    }
			  }
			}
		HttpHeaders headers = new HttpHeaders();
		Blob blob = mb.getPortrait();
	    int len = 0;
	    byte[] media = null;
	    if (blob != null) {
	        try {
	            len = (int) blob.length();
	            media = blob.getBytes(1, len);
	        } catch (SQLException e) {
	            throw new RuntimeException("ProductController的getPicture()發生SQLException: " + e.getMessage());
	        }
	    } else {
//	    	System.out.println("context: " + context.getContextPath());
//	        InputStream is = context.getResourceAsStream(filePath);
//	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//	        byte[] b = new byte[8192];
//	        try {
//	            while ((len = is.read(b)) != -1) {
//	                baos.write(b, 0, len);
//	            } 
//	        
//	  	  } catch (Exception e) {
//	            throw new RuntimeException("ProductController的getPicture()發生IOException: " 
//				+ e.getMessage());
//	        }
//	        media = baos.toByteArray();
	    }
	    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
	    ResponseEntity<byte[]> responseEntity = 
					new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return responseEntity;
	}
	
	@RequestMapping("/foto")			
	public String setting_memInfo_foto(Model model) {
		List<Store> list = storeService.getAllStores("web");
		model.addAttribute("Store", list);
		return "foto";
	}

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model,
			@CookieValue("user") String user) {
		System.out.println(user);
//		Contorller從setting_memInfo頁面收到/password的GET請求
//		因passwordChange.jsp頁功能是更換密碼,故需要在進入這一頁面同時給他一個物件來裝密碼,
//		並利用passwordChange.jsp的<form:>標籤來回傳物件並裝著密碼
		Member mb = new Member();
	    model.addAttribute("Member", mb); 
	    return "passwordChange";//送回foto.jsp
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public String passwordChange(Model model,
			@RequestParam("password") String pswd,
			@RequestParam("password2") String pswd2,
			@CookieValue("user") String user
			) {
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		model.addAttribute("ErrorMsgKey", errorMsg);
		// 準備存放修改成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
		boolean exist = memberService.checkEmail(user);
		if(exist==true) {
			//檢查新密碼1&2是否相同
			if(pswd==pswd2) {
				Member mb = memberService.getMemberByAccount(user);
				//利用user先取得永續物件
				mb.setUserPassword(pswd);
				memberService.updateMemberDate(mb);
					return "setting_memInfo";
			}else {
				//密碼不相同
				errorMsg.put("PasswordEmptyError", "新密碼不相同");
				return "passwordChange";
			}
		}else {
			//帳號不存在
		}
		System.out.println(user);
		System.out.println(pswd);
		System.out.println(pswd2);
		
		
		return "setting_memInfo";
			//需要修改
	}
	//當輸入第一筆舊密碼時,網頁執行ajax查詢密碼是否正確
	@RequestMapping(value = "/ajaxPassword.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean ajaxPasswordCheck(Model model,
			@CookieValue("user") String user,
			@RequestParam("oldPassword") String password) {
		boolean exist = memberService.checkACPassword(user, password);
		System.out.println("ajax執行結果 :" + exist);
		return exist;
	}

	@RequestMapping("/memberIfon")
	public @ResponseBody Member mbInfoServlet(String userAccount){
		System.out.println(userAccount);
		try {
			System.out.println(111);
			System.out.println(memberService.getMemberByAccount(userAccount));
			System.out.println(222);
		} catch (Exception e) {
			System.out.println("發生例外: " + e.getMessage());
		}

		return memberService.getMemberByAccount(userAccount);
	}
//	@RequestMapping("/memberIfon")
//	public Member mbInfoServlet(Model model){
//		System.out.println(model);
////		Member mb = memberService.getUserDateNoPortrait(model);
//		return null;
//	}
////	@RequestMapping("/member_setting")
//	public String member_setting() {
//		return "member_setting";
//	}
	@RequestMapping(value = "/pictures/{Picture}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp,
											@PathVariable("Picture") String pictureNO) {
		System.out.println("收到:"+pictureNO+"請求");
		String filePath = "/images/Store_img/" + pictureNO + ".jpg";
		InputStream is = context.getResourceAsStream(filePath);
		System.out.println(is);
		File file = new File(filePath);
		if(file.exists()) {
			System.out.println("路徑: "+filePath);
			System.out.println("檔案存在");
		}else {
			System.out.println("路徑: "+filePath);
			System.out.println("檔案不存在");
		}
		HttpHeaders headers = new HttpHeaders();
		byte[] media = null;
		int len = 0;
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        byte[] b = new byte[8192];
	        try {
	            while ((len = is.read(b)) != -1) {
	                baos.write(b, 0, len);
	            } 
	        
	  	  } catch (Exception e) {
	            throw new RuntimeException("ProductController的getPicture()發生IOException: " 
				+ e.getMessage());
	        }
	        media = baos.toByteArray();

	    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		    ResponseEntity<byte[]> responseEntity = 
						new ResponseEntity<>(media, headers, HttpStatus.OK);
		    return responseEntity;
	}
//	@RequestMapping(value = "images/Store_img/${Picture}.jpg", method = RequestMethod.GET)
//	public String getServerPicture(HttpServletResponse resp,
//						@PathVariable("pictureNO")String pictureNO) {
//		
//		    return responseEntity;
//	}
//	@RequestMapping("/member_mainPage")
//	public String member_mainPage() {
//		return "member_mainPage";
//	}
	@RequestMapping("/history")
	public String member_setting() {
		return "setting_history";
	}
	@RequestMapping("/member9487/memInfo")
	public String member_main() {
		return "redirect:/memInfo";
	}
}