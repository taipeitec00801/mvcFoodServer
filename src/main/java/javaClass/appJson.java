package javaClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class appJson {
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	
	public static JsonObject readJson(Gson gson, HttpServletRequest request) throws IOException {
		BufferedReader br = request.getReader();
		StringBuffer jsonIn = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("======App===input=====================================");
		System.out.println("input: " + jsonIn);
		
		return gson.fromJson(jsonIn.toString(), JsonObject.class);
	}
	
	
	public static void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("======App===output====================================");
		System.out.println("output: " + outText);
	}
}
