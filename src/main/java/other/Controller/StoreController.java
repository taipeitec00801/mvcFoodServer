package other.Controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import member.Model.Member;
import member.Service.MemberService;
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
			list = storeService.getStoreBySortNum(sortNum);
		} else {
			list = storeService.getAllStores();
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
	
	//Store_Info getOneImage
			@RequestMapping(value = "/getOnePicture/{storeId}", method = RequestMethod.GET)
			public ResponseEntity<byte[]> getOnePicture(HttpServletResponse resp, @PathVariable Integer storeId) {
			    Store bean = storeService.getStoreById(storeId);
			    HttpHeaders headers = new HttpHeaders();
			    String images = bean.getStorePicture();
			    String[] storeImg=images.split(",");
			    int len = 0;
				byte[] media = null;
				System.out.println("image/"+ storeImg[0] + ".jpg");
				InputStream is = context.getResourceAsStream("images/Store_img/"+ storeImg[0] + ".jpg");
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
			    //headers.setCacheControl(CacheControl.noC);
			    ResponseEntity<byte[]> responseEntity = 
							new ResponseEntity<>(media, headers, HttpStatus.OK);
			    return responseEntity;
			}

	@RequestMapping("/store_Info")
	public String store_Info(@RequestParam("storeId") Integer storeId, Model model) {
		Store store = new Store();
		List<StoreComment> comList = new ArrayList<>();
		List<String> contentList = new ArrayList<>();
		store = storeService.getStoreById(storeId);
		System.out.println("storeId!!!:"+storeId);
		comList = storeService.getCommByStore(storeId);
		//Clob to Sring
		for(int i=0;i<comList.size();i++) {
			Clob clob = comList.get(i).getCommentContent();
			try {
				String clobString = clob.getSubString(1, (int) clob.length());
				contentList.add(clobString);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("store", store);
		model.addAttribute("storeComments",comList);
		model.addAttribute("contentList",contentList);
		return "store_Info";
	}
	
	//getMemberImg
	@RequestMapping(value = "/getMemberImg/{memberId}", method = RequestMethod.GET)			
	public ResponseEntity<byte[]> getMemberPortrait(Model model, 
			@PathVariable Integer memberId,
			HttpServletResponse response,
			HttpServletRequest request
	) {
		Member mb = null;
		System.out.println("memberId!!!!:"+memberId);
		mb = storeService.getMemberById(memberId);
		System.out.println("memberName!!!!:"+mb.getNickname());
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
	
	//getCommentImg
		@RequestMapping(value = "/getCommentImg/{commentId}", method = RequestMethod.GET)			
		public ResponseEntity<byte[]> getCommentImg(Model model, 
				@PathVariable Integer commentId,
				HttpServletResponse response,
				HttpServletRequest request
		) {
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
//		    	System.out.println("context: " + context.getContextPath());
//		        InputStream is = context.getResourceAsStream(filePath);
//		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		        byte[] b = new byte[8192];
//		        try {
//		            while ((len = is.read(b)) != -1) {
//		                baos.write(b, 0, len);
//		            } 
//		        
//		  	  } catch (Exception e) {
//		            throw new RuntimeException("ProductController的getPicture()發生IOException: " 
//					+ e.getMessage());
//		        }
//		        media = baos.toByteArray();
		    }
		    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		    ResponseEntity<byte[]> responseEntity = 
						new ResponseEntity<>(media, headers, HttpStatus.OK);
		    return responseEntity;
		}
	
}
