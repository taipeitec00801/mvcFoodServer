package member._00_init.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


// 
// 程式功能：
// 本Servlet 類別會依據傳入的主鍵呼叫Service元件以讀取該主鍵所對應的紀錄，取出該紀錄內的BLOB欄，
// 進而讀取存放在BLOB欄內的圖片資料，然後傳回給提出請求的瀏覽器。

@WebServlet("/_00_init/getImage")
public class RetrieveImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		OutputStream os = null;
		InputStream is = null;
		String fileName = null;
		try {
			WebApplicationContext ctx = 
					WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			
			// 讀取瀏覽器傳送來的主鍵
//			String id = request.getParameter("id");
			// 讀取瀏覽器傳送來的type，以分辨要處理哪個表格
//			String type = request.getParameter("type"); 
//			switch(type.toUpperCase()){
//				case "BOOK":
//					BookService bookService = ctx.getBean(BookService.class);
//					int nId = 0;
//					try {
//						nId = Integer.parseInt(id);
//					} catch(NumberFormatException ex) {
//						;
//					}
//					BookBean bean1 = bookService.getBook(nId);
//					is = bean1.getCoverImage().getBinaryStream();
//					fileName = bean1.getFileName();
//					break;
//				case "MEMBER":
//					//MemberService memberService = new MemberServiceImpl();
//					MemberService memberService = ctx.getBean(MemberService.class);
//					MemberBean bean2 = memberService.queryMember(id);
//					is = bean2.getMemberImage().getBinaryStream();  
//					fileName = bean2.getFileName();
//			}

			// 由圖片檔的檔名來得到檔案的MIME型態
			String mimeType = getServletContext().getMimeType(fileName);
			// 設定輸出資料的MIME型態
			response.setContentType(mimeType);
			// 取得能寫出非文字資料的OutputStream物件
						
			// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.jpg)	
			if (is == null) {
				is = getServletContext().getResourceAsStream(
							"/images/NoImage.jpg");
			}
			os = response.getOutputStream();	
			// 由InputStream讀取位元組，然後由OutputStream寫出
			int len = 0;
			byte[] bytes = new byte[8192];
			while ((len = is.read(bytes)) != -1) {
				os.write(bytes, 0, len);
			}
//		} catch(SQLException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("_00_init.util.RetrieveImageServlet#doGet()發生SQLException: " + ex.getMessage());
		} finally{
			is.close();
			os.close();
		}
	}
}