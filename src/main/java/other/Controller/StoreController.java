package other.Controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
import other.Service.StoreService;

@Controller
public class StoreController {

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
		// List<Store> list = new ArrayList<Store>();
		// list = storeService.getStoresById(storeId);
		// System.out.println(list.get(0).getStoreName());
		// model.addAttribute("store",list);
		Store store = new Store();
		store = storeService.getStoreById(storeId);
		model.addAttribute("store", store);
		return "store_Info";
	}
}
