package other.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javaClass.appJson;
import member.Model.Member;
import other.Model.Store;
import other.Service.StoreService;

@Controller
public class AppStoreController {
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	private final static String CHARACTER_ENCODING = "UTF-8";
	
	
	@Autowired
	StoreService appStoreService;

	@Autowired
	ServletContext context;

	@RequestMapping("/appSort")
	public void appSort(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding(CHARACTER_ENCODING);
		response.setContentType(CONTENT_TYPE);
		Gson gson = new Gson();
		JsonObject jsonObject = appJson.readJson(gson, request);
		String action = jsonObject.get("action").getAsString();
		if (action.equals("findStoreByNumber")) {
			int sortNumber = jsonObject.get("sortNumber").getAsInt();
			System.out.println(sortNumber);
			List<Store> storeList = appStoreService.getStoreBySortNum(sortNumber, "app");
			appJson.writeText(response, gson.toJson(storeList));
		} else if (action.equals("findStoreByName")) {
			String storeName = jsonObject.get("storeName").getAsString();
			System.out.println(storeName);
			List<Store> storeList = appStoreService.getStoreByName(storeName, "app");
			appJson.writeText(response, gson.toJson(storeList));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/appStore")
	public void appStore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding(CHARACTER_ENCODING);
		response.setContentType(CONTENT_TYPE);
		Gson gson = new Gson();
		JsonObject jsonObject = appJson.readJson(gson, request);
		String action = jsonObject.get("action").getAsString();
		if (action.equals("findStoreByUserlocation")) {
			List<Store> storeList = appStoreService.getAllStores("app");
			appJson.writeText(response, gson.toJson(storeList));
		} else if (action.equals("getCollectionByUser")) {
			String storeId = jsonObject.get("userCollection").getAsString();
			System.out.println("eeee" + storeId);
			String[] userCollection = storeId.split(",");
			List<Store> storeList = new ArrayList();
			for (int i = 0; i < userCollection.length; i++) {
				Integer storeId1 = Integer.parseInt(userCollection[i]);
				storeList.addAll(appStoreService.getStoresById(storeId1));
			}
			appJson.writeText(response, gson.toJson(storeList));
		} else if (action.equals("getTopStores")) {
			List<Store> storesTop = appStoreService.getTopStores();
			appJson.writeText(response, gson.toJson(storesTop));
		}
	}

	@RequestMapping("/appGetImages")
	public void appGetImages(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding(CHARACTER_ENCODING);
		response.setContentType(CONTENT_TYPE);
		Gson gson = new Gson();
		JsonObject jsonObject = appJson.readJson(gson, request);
		String id = jsonObject.get("id").getAsString();
		System.out.println(id);
		// 001_00,001_01,001_02,001_03
		String[] images = appStoreService.findStoreById(Integer.valueOf(id));
		// String[] images = service.findStoreById(8);
		int len = 0;
		byte[] media = null;
		System.out.println("image/" + images[0] + ".jpg");
		InputStream is = context.getResourceAsStream("images/Store_img/" + images[0] + ".jpg");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[8192];
		try {
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}

		} catch (Exception e) {
			throw new RuntimeException("appGetImages()發生IOException: " + e.getMessage());
		}
		media = baos.toByteArray();
		OutputStream os = response.getOutputStream();
		response.setContentType("image/jpeg");
		response.setContentLength(media.length);
		os.write(media);
	}

	@RequestMapping("/appGetAllImages")
	public void appGetAllImages(HttpServletRequest request, HttpServletResponse resp) throws IOException {
		request.setCharacterEncoding(CHARACTER_ENCODING);
		resp.setContentType(CONTENT_TYPE);
		Gson gson = new Gson();
		JsonObject jsonObject = appJson.readJson(gson, request);
		String id = jsonObject.get("id").getAsString();
		int position = Integer.valueOf(jsonObject.get("position").getAsString());
		System.out.println(id);
		// 001_00,001_01,001_02,001_03
		String[] images = appStoreService.findStoreById(Integer.valueOf(id));
		// String[] images = service.findStoreById(8);
		int len = 0;
		byte[] media = null;
		System.out.println("image/" + images[position] + ".jpg");
		InputStream is = context.getResourceAsStream("images/Store_img/" + images[position] + ".jpg");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[8192];
		try {
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}

		} catch (Exception e) {
			throw new RuntimeException("appGetAllImages()發生IOException: " + e.getMessage());
		}
		media = baos.toByteArray();
		OutputStream os = resp.getOutputStream();
		resp.setContentType("image/jpeg");
		resp.setContentLength(media.length);
		os.write(media);
	}

	@RequestMapping("/appUpdateStRecom")
	public void appUpdateStRecom(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding(CHARACTER_ENCODING);
		response.setContentType(CONTENT_TYPE);
		Gson gson = new Gson();
		JsonObject jsonObject = appJson.readJson(gson, request);
		String memberId = jsonObject.get("memberId").getAsString();
		String storeId = jsonObject.get("storeId").getAsString();
		String recomYN = jsonObject.get("recomYN").getAsString();		
		Member mm = appStoreService.getMemberById(Integer.parseInt(memberId));
		Store ss = appStoreService.getStoreById(Integer.parseInt(storeId));
		Integer isStRecom = appStoreService.updateStRecomYNByMSId(mm, ss, Integer.parseInt(recomYN));
		appJson.writeText(response, String.valueOf(isStRecom));
	}
	
	@RequestMapping("/appGetStRecom")
	public void appGetStRecom(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding(CHARACTER_ENCODING);
		response.setContentType(CONTENT_TYPE);
		Gson gson = new Gson();
		JsonObject jsonObject = appJson.readJson(gson, request);
		String memberId = jsonObject.get("memberId").getAsString();
		String storeId = jsonObject.get("storeId").getAsString();		
		Member mm = appStoreService.getMemberById(Integer.parseInt(memberId));
		Store ss = appStoreService.getStoreById(Integer.parseInt(storeId));
		Integer isStRecom = appStoreService.getStRecomYNByMSId(mm, ss);
		appJson.writeText(response, String.valueOf(isStRecom));
	}

}
