package co.kr.hanyang1;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy.Type;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTMLEditorKit.Parser;
import javax.xml.ws.RequestWrapper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends Thread {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
	@RequestMapping("/result")
	public String questionResult(HttpServletRequest http, Model model) {
		String time = String.valueOf(System.currentTimeMillis());
		System.out.println(time);
		
		//Qid : 랜덤 생성하기
		String qid = "";
		for(int i = 0; i < 7; i++) {
		    char ch = (char) ((Math.random() * 26) + 97);
			qid = qid+ch;
		}
		qid = time + qid;
		
		//입력 받은 질문
		String question = http.getParameter("question");
		
		//JSON 형태로 Request 요청하기
		JSONObject json = new JSONObject();
		
		json.put("question", question);
		json.put("qid", qid);
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://192.168.1.182:5000/login");
		
		httpPost.addHeader("Content-type", "application/json");

		try {
			httpPost.setEntity(new StringEntity(json.toString()));
			System.out.println(json.toString());
			
			//Response(JSON형태)
			HttpResponse response = client.execute(httpPost);
			
			System.out.println("entityTest_1");
			
			String resEntity = EntityUtils.toString(response.getEntity());
			System.out.println("RESENTITIY : " + resEntity);
			
			//response된 문자열 정제하기
			JsonParser jsonParser = new JsonParser();
			JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonParser.parse(resEntity);
			//JSON형태로 변환하여 String으로 변환하기(JsonObject로 바로 변형하면 오류가 발생하여 JsonPrimiter로 우선 처리하여 변경)
			//JsonObject jsonObject = (JsonObject) jsonParser.parse(resEntity);
			System.out.println("question : " + jsonPrimitive.getAsString());
			
			String resJson = jsonPrimitive.getAsString();
			JsonObject obj = (JsonObject) jsonParser.parse(resJson);
			System.out.println("JSON OBJECT QID : " + obj.get("qid").getAsString());
			
			int res = obj.get("prediction").getAsInt();
			String tmpRes = "";
			if (res < 1) {
				tmpRes = "Good Question! 좋은 질문입니다.!";
			} else {
				tmpRes = "Insincere Questions! 당신은 트롤이군요!";
			}
			
			model.addAttribute("question", obj.get("question").getAsString());
			model.addAttribute("qid", obj.get("qid").getAsString());
			model.addAttribute("prediction", tmpRes);
			
			client.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "view";
	}
}
