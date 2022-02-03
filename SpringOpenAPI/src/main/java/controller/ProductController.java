package controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ProductController {
	
	HttpServletRequest request;

	@RequestMapping("/product/list.do")
	public String list(String p_name,int start,int display) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		p_name = "iphone";
		start = 1;
		display = 10;
		
		p_name = request.getParameter("p_name");
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
		
		/* List <ProductVo> list = MySearchUtil.search_shop(p_name, start, display); */
		
		String clientID = "Agnln2mMFnjsK4Wa3o7D"; 
		String clientSecret = "ZWFfNy1ILS"; 
		p_name = null; 
		
		try { p_name = URLEncoder.encode("p_name", "UTF-8"); } 
			catch (UnsupportedEncodingException e) 
			{ throw new RuntimeException("검색어 인코딩 실패", e); } 
		
		String apiURL = "https://openapi.naver.com/v1/search/shop.xml?query=" + text; 
		
		Map < String,String > requestHeaders = new HashMap<>(); 
	
		requestHeaders.put("X-Naver-Client-Id", clientID); 
		requestHeaders.put("X-Naver-Client-Secret", clientSecret); 
		
		String responseBody = get(apiURL, requestHeaders); 
		
		String json = responseBody; 
		
		JSONParser parser = new JSONParser(); 
		JSONObject obj = (JSONObject)parser.parse(json); 
		JSONArray item = (JSONArray)obj.get("items"); 
		
		List < Movie > list = null; 
		list = new ArrayList<Movie>(); 
		
		for (int i = 0; i < item.size(); i ++) 
			{ Movie m = new Movie(); 
			
		JSONObject tmp = (JSONObject)item.get(i); 
		
		String title = (String)tmp.get("title"); 
		String image = (String)tmp.get("image"); 
		String description = (String)tmp.get("description"); 
		String link = (String)tmp.get("link"); 
		String subtitle = (String)tmp.get("subtitle"); 
		m.setTitle(title); 
		m.setImage(image); 
		m.setDescription(description);
		m.setLink(link); 
		m.setSubtitle(subtitle); 
		
		if (m != null) list.add(m); } 
		for (Movie movie : list) { System.out.println(movie.getTitle()); }

		
		
		return "product/product_list";
	}
	
}
