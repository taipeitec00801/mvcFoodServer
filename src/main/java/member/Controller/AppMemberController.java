package member.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javaClass.GlobalService;
import javaClass.ImageUtil;
import javaClass.appJson;
import member.Model.Member;
import member.Service.MemberService;

@Controller
public class AppMemberController {
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	private final static String CHARACTER_ENCODING = "UTF-8";
	
	@Autowired
	MemberService appMemberService;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("/appInsertMemberDate")
	public void appInsertMemberDate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding(CHARACTER_ENCODING);
		response.setContentType(CONTENT_TYPE);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		JsonObject jsonObject = appJson.readJson(gson, request);
		String action = jsonObject.get("action").getAsString();		
		if (action.equals("insertMemberDate")) {
			String stringMember = jsonObject.get("Member").getAsString();
			Member member = gson.fromJson(stringMember, Member.class);
			String imageBase64 = jsonObject.get("insertPortrait").getAsString();
			byte[] image = null;
			if (!imageBase64.equals("0")) {
				image = Base64.getMimeDecoder().decode(imageBase64);
			}
			int count = 0;
			member.setUserPassword(GlobalService.getMD5Endocing(member.getUserPassword()));	
			try {
				member.setPortrait(new javax.sql.rowset.serial.SerialBlob(image));
			} catch (SQLException e) {
				throw new RuntimeException("appInsertMemberDate() 發生SQLException: " + e.getMessage());
			}
			count = appMemberService.insertMemberDate(member);
			appJson.writeText(response, String.valueOf(count));
		}
	}
	
	@RequestMapping("/appGetPortrait")
	public void appGetPortrait(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding(CHARACTER_ENCODING);
		response.setContentType(CONTENT_TYPE);
		Gson gson = new Gson();
		JsonObject jsonObject = appJson.readJson(gson, request);
		String action = jsonObject.get("action").getAsString();		
		if (action.equals("getImage")) {
			OutputStream os = response.getOutputStream();
			String userAccount = jsonObject.get("UserAccount").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			Member member = appMemberService.getMemberByAccount(userAccount);
			byte[] portrait = null;
			int len = 0;
			if (member.getPortrait() != null) {
				try {
					len = (int) member.getPortrait().length();
					portrait = member.getPortrait().getBytes(1, len);
				} catch (SQLException e) {
					throw new RuntimeException("appGetPortrait() 發生SQLException: " + e.getMessage());
				}
			}			
			if (portrait != null) {
				portrait = ImageUtil.shrink(portrait, imageSize);
				response.setContentType("portrait/jpeg");
				response.setContentLength(portrait.length);
			}
			os.write(portrait);
		}
	}
	
	@RequestMapping("/appUserLogin")
	public void appUserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding(CHARACTER_ENCODING);
		response.setContentType(CONTENT_TYPE);
		Gson gson = new Gson();
		JsonObject jsonObject = appJson.readJson(gson, request);
		String action = jsonObject.get("action").getAsString();		
		if (action.equals("userLogin")) {
			String userAccount = jsonObject.get("UserAccount").getAsString();
			String userPassword = jsonObject.get("UserPassword").getAsString();
			boolean inputOk = false;
			inputOk = appMemberService.checkACPassword(userAccount, GlobalService.getMD5Endocing(userPassword));			
			appJson.writeText(response, String.valueOf(inputOk));
		}
	}
	
	@RequestMapping("/appCheckAccount")
	public void appCheckAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding(CHARACTER_ENCODING);
		response.setContentType(CONTENT_TYPE);
		Gson gson = new Gson();
		JsonObject jsonObject = appJson.readJson(gson, request);
		String action = jsonObject.get("action").getAsString();		
		if (action.equals("checkAccount")) {
			String userAccount = jsonObject.get("UserAccount").getAsString();
			boolean inputOk = false;
			inputOk = appMemberService.checkAccount(userAccount);		
			appJson.writeText(response, String.valueOf(inputOk));
		}
	}
	
	@RequestMapping("/appGetUserDate")
	public void appGetUserDate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding(CHARACTER_ENCODING);
		response.setContentType(CONTENT_TYPE);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		JsonObject jsonObject = appJson.readJson(gson, request);
		String action = jsonObject.get("action").getAsString();
		if (action.equals("getUserDate")) {
			String userAccount = jsonObject.get("UserAccount").getAsString();
			Member member = appMemberService.getMemberByAccount(userAccount);
			Member newMember = new Member(member.getMemberId(), userAccount, 
					 member.getUserPassword().substring(0, 12),		//故意顯示長度為12
					member.getNickname(), member.getBirthday(), member.getGender(), member.getUserRank(),
					member.getPreference(), member.getCollection(), member.getUserGift(), member.getUserFriends());
			
			appJson.writeText(response, gson.toJson(newMember));
		}
	}

	@RequestMapping("/appUpdate")
	public void appUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding(CHARACTER_ENCODING);
		response.setContentType(CONTENT_TYPE);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		JsonObject jsonObject = appJson.readJson(gson, request);
		String action = jsonObject.get("action").getAsString();
		if (action.equals("update")) {
			String update = jsonObject.get("update").getAsString();

			if (update.equals("updatePreference")) {
				String userAccount = jsonObject.get("UserAccount").getAsString();
				String preference = jsonObject.get("Preference").getAsString();
				int count = 0;
				count = appMemberService.updatePreference(userAccount, preference);
				appJson.writeText(response, String.valueOf(count));

			} else if (update.equals("updatePortrait")) {
				String userAccount = jsonObject.get("UserAccount").getAsString();
				String imageBase64 = jsonObject.get("updatePortrait").getAsString();
				int count = 0;
				byte[] image = Base64.getMimeDecoder().decode(imageBase64);
				count = appMemberService.updatePortrait(userAccount, image);
				appJson.writeText(response, String.valueOf(count));

			} else if (update.equals("updateMemberDate")) {
				String stringMember = jsonObject.get("Member").getAsString();
				Member member = gson.fromJson(stringMember, Member.class);
				int count = 0;
				member.setUserPassword(GlobalService.getMD5Endocing(member.getUserPassword()));
				count = appMemberService.updateMemberDate(member);
				appJson.writeText(response, String.valueOf(count));

			} else {
				appJson.writeText(response, "");
			}
		}
	}
}
