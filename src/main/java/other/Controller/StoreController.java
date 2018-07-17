package other.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import init.JDBC.Utils.SystemUtils;
import member.Model.Member;
import member.Service.MemberService;
import javax.servlet.http.Cookie;
import other.Model.Message;
import other.Model.Store;
import other.Model.StoreComment;
import other.Service.StoreService;

@Controller
public class StoreController {
	@Autowired
	MemberService memberService;

	@Autowired
	StoreService storeService;

	@Autowired
	ServletContext context;

	@RequestMapping("/sorts")
	public String getStore(@RequestParam("sortNo") Integer sortNum, @RequestParam("pages") Integer pages, Model model) {
		storeService.setPageNo(pages);
		model.addAttribute("totalPages", storeService.getTotalPages(sortNum));
		List<Store> list = new ArrayList<Store>();
		if (sortNum < 10 && sortNum >= 0) {
			list = storeService.getStoreBySortNum(sortNum, "web");
		} else {
			list = storeService.getAllStores("web");
		}
		model.addAttribute("stores", list);
		model.addAttribute("sortNumber", sortNum);
		return "sorts";
	}

	// Store_Info getImage
	@RequestMapping(value = "/getPicture/{storeId}/{i}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer storeId,
			@PathVariable Integer i) {
		Store bean = storeService.getStoreById(storeId);
		HttpHeaders headers = new HttpHeaders();
		String images = bean.getStorePicture();
		String[] storeImg = images.split(",");
		int len = 0;
		byte[] media = null;
		System.out.println("image/" + storeImg[i] + ".jpg");
		InputStream is = context.getResourceAsStream("images/Store_img/" + storeImg[i] + ".jpg");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[8192];
		try {
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}

		} catch (Exception e) {
			throw new RuntimeException("ProductController的getPicture()發生IOException: " + e.getMessage());
		}
		media = baos.toByteArray();
		// headers.setCacheControl(CacheControl.noC);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	// Store_Info getOneImage
	@RequestMapping(value = "/getOnePicture/{storeId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getOnePicture(HttpServletResponse resp, @PathVariable Integer storeId) {
		Store bean = storeService.getStoreById(storeId);
		HttpHeaders headers = new HttpHeaders();
		String images = bean.getStorePicture();
		String[] storeImg = images.split(",");
		int len = 0;
		byte[] media = null;
		System.out.println("image/" + storeImg[0] + ".jpg");
		InputStream is = context.getResourceAsStream("images/Store_img/" + storeImg[0] + ".jpg");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[8192];
		try {
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}

		} catch (Exception e) {
			throw new RuntimeException("ProductController的getPicture()發生IOException: " + e.getMessage());
		}
		media = baos.toByteArray();
		// headers.setCacheControl(CacheControl.noC);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping("/store_Info")
	public String store_Info(@RequestParam("storeId") Integer storeId,
			Model model,
			HttpServletResponse response,
			HttpServletRequest request) {
		Store store = new Store();
		List<StoreComment> comList = new ArrayList<>();
		List<String> contentList = new ArrayList<>();
		List<Message> msgListTemp = new ArrayList<>();
		List<Message> msgList = new ArrayList<>();
		store = storeService.getStoreById(storeId);
		System.out.println("storeId!!!:" + storeId);
		comList = storeService.getCommByStore(storeId);
		if (comList != null) {
			for (int i = 0; i < comList.size(); i++) {
				Integer commId = comList.get(i).getCommentId();
				System.out.println("commId=====:" + commId);
				msgListTemp = storeService.getMessageByComm(commId);
				if (!msgListTemp.isEmpty()) {
					for (Message ms : msgListTemp) {
						System.out.println("Message=====:" + ms.getMsgContent());
						msgList.add(ms);
					}
				}
			}
			System.out.println("msgList=====:" + msgList.size());
		}

		// Clob to Sring
		for (int i = 0; i < comList.size(); i++) {
			Clob clob = comList.get(i).getCommentContent();
			try {
				String clobString = clob.getSubString(1, (int) clob.length());
				contentList.add(clobString);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			Clob cb = SystemUtils.stringToClob("1");
			StoreComment scomment = new StoreComment(cb);
			model.addAttribute("scomment", scomment);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		model.addAttribute("store", store);
		model.addAttribute("storeComments", comList);
		model.addAttribute("contentList", contentList);
		model.addAttribute("msgList", msgList);
		return "store_Info";
	}

	// getMemberImg
	@RequestMapping(value = "/getMemberImg/{memberId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getMemberPortrait(Model model, @PathVariable Integer memberId,
			HttpServletResponse response, HttpServletRequest request) {
		Member mb = null;
		System.out.println("memberId!!!!:" + memberId);
		mb = storeService.getMemberById(memberId);
		System.out.println("memberName!!!!:" + mb.getNickname());
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
			Integer gender = mb.getGender();
			System.out.println("context: " + context.getContextPath());
			InputStream is = null;
			if (gender == 1)
				is = context.getResourceAsStream("images/man.png");
			else
				is = context.getResourceAsStream("images/Logo.png");

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] b = new byte[8192];
			try {
				while ((len = is.read(b)) != -1) {
					baos.write(b, 0, len);
				}

			} catch (Exception e) {
				throw new RuntimeException("ProductController的getPicture()發生IOException: " + e.getMessage());
			}
			media = baos.toByteArray();
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	// getCommentImg
	@RequestMapping(value = "/getCommentImg/{commentId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getCommentImg(Model model, @PathVariable Integer commentId,
			HttpServletResponse response, HttpServletRequest request) {
		StoreComment sc = null;
		sc = storeService.getCommentById(commentId);
		HttpHeaders headers = new HttpHeaders();
		Blob blob = sc.getCommentPicture();
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
			// System.out.println("context: " + context.getContextPath());
			// InputStream is = context.getResourceAsStream(filePath);
			// ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// byte[] b = new byte[8192];
			// try {
			// while ((len = is.read(b)) != -1) {
			// baos.write(b, 0, len);
			// }
			//
			// } catch (Exception e) {
			// throw new RuntimeException("ProductController的getPicture()發生IOException: "
			// + e.getMessage());
			// }
			// media = baos.toByteArray();
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping(value = "/store_Info", method = RequestMethod.POST)
	public String store_Info(@ModelAttribute("scomment") StoreComment scomment,
			@RequestParam("storeId") Integer storeId,
			 Model model, HttpServletResponse response,
			HttpServletRequest request) throws IOException, SQLException {

		System.out.println("StoreComment=====:"+scomment.getTempComment());
		
		Member mb = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					mb = memberService.getMemberByAccount(cookie.getValue());
					// 取得使用者帳號
				}
			}
		}
		Clob cb = SystemUtils.stringToClob(scomment.getTempComment());
		scomment.setCommentContent(cb);
		System.out.println("CommentContent======"+cb);
		scomment.setCommentMId(mb);
		Store scStore = storeService.getStoreById(storeId);
		scomment.setCommentSId(scStore);
		scomment.setCommentAlterCount(0);
		scomment.setCommentRecomCount(0);
		System.out.println("getNickname: " + scomment.getCommentMId());
		System.out.println("getScImage: " + scomment.getScImage());
		// scomment.setCommentContent(cb);
		// 時間型態轉換
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(strDate);
			System.out.println("Timestamp: " + ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		scomment.setCommentDate(ts);// 將上傳時間寫入
		// 照片上傳
		MultipartFile scImage = scomment.getScImage();
		String originalFilename = scImage.getOriginalFilename();
		System.out.println("length: " + originalFilename.length());
		String rootDirectory = context.getRealPath("/");
		String ext = null;
		if (originalFilename.length() > 0) {

			ext = originalFilename.substring(originalFilename.lastIndexOf("."));

			// 建立Blob物件，交由 Hibernate 寫入資料庫
			if (scImage != null && !scImage.isEmpty()) {
				try {
					byte[] b = scImage.getBytes();
					Blob blob = new SerialBlob(b);
					scomment.setCommentPicture(blob);
					// Update Funation
					storeService.updateStoreComment(scomment);
					System.out.println("上傳成功");
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
				}
			} else {
				System.out.println("沒有照片: " + scImage.isEmpty());
			}

		}

		return "redirect:/store_Info?storeId=" + storeId;
	}
	
	//Send Message
	@RequestMapping(value = "/member9487/sendMessage", method = RequestMethod.POST)
	public void sendMessage(HttpServletResponse response,
			HttpServletRequest request) {
		
		String msg = request.getParameter("commMsg");
		String msgcid = request.getParameter("commId");
		String msgMid = request.getParameter("commMid");
		//Timestamp
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(strDate);
			System.out.println("Timestamp: " + ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StoreComment sc = storeService.getCommentById(Integer.parseInt(msgcid));
		Member mb = storeService.getMemberById(Integer.parseInt(msgMid));
		if(mb.getMemberId() == sc.getCommentMId().getMemberId()) {
			
		}
		
		Message addMsg = new Message(ts,msg,sc,mb);
		storeService.sendMessage(addMsg);
		
		System.out.println("msg=====:"+msg+" msgcid=====:"+msgcid+" msgMid=====:"+msgMid);
		
	}
	
	//更新店家讚數
		@RequestMapping(value = "/member9487/updateStoreRecom", method = RequestMethod.POST)
		public void updateStoreRecom(HttpServletResponse response,
				HttpServletRequest request) {
			
			String memberId = request.getParameter("stRecomMId");
			String storeId = request.getParameter("stRecomSId");
			String stRecomYN = request.getParameter("stRecomYN");
			System.out.println("memberId=====:"+memberId+" storeId=====:"+storeId+" stRecomYN=====:"+stRecomYN);
			Member mb = storeService.getMemberById(Integer.parseInt(memberId));
			Store st =  storeService.getStoreById(Integer.parseInt(storeId));
			storeService.updateStRecomYNByMSId(mb, st, Integer.parseInt(stRecomYN));
			
		}

}
