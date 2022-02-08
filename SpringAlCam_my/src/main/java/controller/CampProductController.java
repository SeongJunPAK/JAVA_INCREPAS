package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import myutil.MyProduct;
import myutil.Paging;
import vo.CampProductVo;


@Controller
public class CampProductController {
	
	@Autowired
	HttpServletRequest request;

	@RequestMapping(value = "/goods/camp_list.do", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String list( 
		@RequestParam(value = "p_name", defaultValue = "") String p_name,	
		@RequestParam(value = "page", defaultValue = "1") int nowPage,
		Model model) throws Exception{
		
		request.setCharacterEncoding("utf-8");
		
		String pageMenu = "";
		// 검색범위 및 조건을 담을 객체
		
		int start = 1;
		int display = 5;
		//페이징 처리를 위한 전체 검색 갯수 찾기
		int rowTotal = 0;
		
		
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			display = Integer.parseInt(request.getParameter("display"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			rowTotal = Integer.parseInt(request.getParameter("total"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String clientID = "Agnln2mMFnjsK4Wa3o7D"; 
		String clientSecret = "ZWFfNy1ILS"; 
		
		try {
			p_name = URLEncoder.encode(p_name, "utf-8");
			String urlStr = String.format("https://openapi.naver.com/v1/search/shop.json?query=%s&start=%d&display=%d",
					         p_name,start,display
					);
		
		Map < String,String > requestHeaders = new HashMap<>(); 
	
		requestHeaders.put("X-Naver-Client-Id", clientID); 
		requestHeaders.put("X-Naver-Client-Secret", clientSecret); 
		
		String responseBody = get(urlStr, requestHeaders); 
		
		System.out.println(responseBody);
		
		
		 //String json = URLEncoder.encode(responseBody,"utf-8");
		String json = responseBody; 
	
		JSONParser parser = new JSONParser(); 
		JSONObject obj = (JSONObject)parser.parse(json); 
		
		JSONArray item = (JSONArray)obj.get("items"); 
		
		List < CampProductVo > list = new ArrayList<CampProductVo>(); 
		
		for (int i = 0; i < item.size(); i++) {
			 CampProductVo vo = new CampProductVo(); 
			
			JSONObject tmp = (JSONObject)item.get(i); 
			
			String g_category = (String)tmp.get("category3");
			String g_name  = (String)tmp.get("title");
			String g_filename = (String)tmp.get("image");
			int g_price=0;
				try {
					g_price = Integer.parseInt((String)tmp.get("lprice"));
				} catch (Exception e) {
					// TODO: handle exception
				}
			
			String g_link = (String)tmp.get("link");
			
			vo.setG_category(g_category);
			vo.setG_name(g_name);
			vo.setG_filename(g_filename);
			vo.setG_price(g_price);
			vo.setG_link(g_link);
			
			list.add(vo);
			
			
		}//end-for
		
		String search_filter = String.format("p_name=%s", p_name);
		 pageMenu = Paging.getPaging("camp_list.do", 
				 					  search_filter,
				 					  nowPage,
				 					  rowTotal,
				 					  MyProduct.Product.BLOCK_LIST,
				 					  MyProduct.Product.BLOCK_PAGE );
		
		model.addAttribute("list",list);
		model.addAttribute("pageMenu", pageMenu);
		
		System.out.println(nowPage);
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "goods/camp_product_list";
	}
	
	///////////get,connect,readbody는 네이버 제공 예제 메소드, 서버연결시 필요////////////////////////////////////////////////////
	
	
	private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        
    	InputStreamReader streamReader=null;
		try {
			streamReader = new InputStreamReader(body,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
	
	
	
}
