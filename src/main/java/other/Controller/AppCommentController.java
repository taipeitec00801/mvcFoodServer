package other.Controller;

import java.io.IOException;
import java.sql.SQLException;
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
import other.Model.CommentforApp;
import other.Model.StoreComment;
import other.Service.StoreService;

@Controller
public class AppCommentController {
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	private final static String CHARACTER_ENCODING = "UTF-8";

	@Autowired
	StoreService appStoreService;

	@Autowired
	ServletContext context;

	@RequestMapping("/appGetComment")
	public void appGetComment(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		request.setCharacterEncoding(CHARACTER_ENCODING);
		response.setContentType(CONTENT_TYPE);
		Gson gson = new Gson();
		JsonObject jsonObject = appJson.readJson(gson, request);

		Integer storeId = jsonObject.get("storeId").getAsInt();
		List<StoreComment> commList = appStoreService.getCommByStore(storeId);
		List<CommentforApp> cfaList = new ArrayList<>();
		for (int i = 0; i < commList.size(); i++) {
			System.out.println("---------------" + commList.get(i).getCommentMId().getNickname() + "----------");
			String comm = commList.get(i).getCommentContent().getSubString(1,
					(int) commList.get(i).getCommentContent().length());
			CommentforApp cfa = new CommentforApp(commList.get(i).getCommentMId().getNickname(), comm.substring(0, 15),
					String.valueOf(commList.get(i).getCommentRecomCount()), "0");
			cfaList.add(cfa);
		}

		appJson.writeText(response, gson.toJson(cfaList));

	}

}
