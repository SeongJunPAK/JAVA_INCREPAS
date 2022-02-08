package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vo.PlaceVo;

@Controller
public class PlaceController {

	@Autowired
	HttpSession session;

	@Autowired
	HttpServletRequest request;

	@RequestMapping("camping/search.do")
	public String search_map(@RequestParam(value = "text_search", defaultValue = "") 
	String text_search, Model model) {
		//������ ������ ���� list ����
		List<PlaceVo> list = new ArrayList<PlaceVo>();
		//īī��API�� ���� �ӽ���ǥ ����
		String imsi_x = "";
		String imsi_y = "";
		//īī�� API
		try {
			text_search = URLEncoder.encode(text_search, "utf-8");
			String kakaoAK = "KakaoAK 6b374997db253e62e6e35773bd3daf88";
			//�˻� ������ ����
			String urlStr = String.format(
					"https://dapi.kakao.com/v2/local/search/address.json?analyze_type=similar&page=1&size=10&query=%s",
					text_search);
			URL k_url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) k_url.openConnection();
			// �߱޹��� key
			connection.setRequestProperty("Authorization", kakaoAK);
			connection.setRequestProperty("Content-Type", "application/plain");
			connection.connect();

			InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "utf-8");
			BufferedReader br = new BufferedReader(isr);

			String k_sb = "";
			String k_line;
			while ((k_line = br.readLine()) != null) {
				k_sb = k_sb.concat(k_line);
				k_sb.concat(k_line);
				// ---------------------------------------
				JSONParser parser = new JSONParser();

				JSONObject obj = (JSONObject) parser.parse(k_sb);
				// �˻��������
				JSONObject meta = (JSONObject) obj.get("meta");
				// �˻����
				JSONArray local_array = (JSONArray) obj.get("documents");
				for (int i = 0; i < local_array.size(); i++) {
					JSONObject local = (JSONObject) local_array.get(i);
					imsi_x = (String) local.get("x");
					imsi_y = (String) local.get("y");
				}
			}
			connection.disconnect();
			//īī������ ���� ��ǥ�� double ����ȯ
			double mapX = Double.parseDouble(imsi_x);
			double mapY = Double.parseDouble(imsi_y);
			System.out.printf("%f",mapX);
			System.out.printf("%f",mapY);
			String serviceKey = "HUGsei948k7GTAIm951Gwaph5wGoiBzWrH7jKaVNWZ56lzC84RVFoXia4FQqpBlT3ncDyVnrgO%2BGaIG0gvp%2BOQ%3D%3D";
			String urlBuilder = "http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/locationBasedList?"
					+ "serviceKey=" + serviceKey + "&MobileOS=ETC" + "&MobileApp=AppTest" + "&mapX=" + mapX + "&mapY="
					+ mapY + "&radius=10000" + "&pageNo=1" + "&numOfRows=10" + "&_type=json";
			
			/* �߰� �ؾߵ� ���� */
			// urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" +
			// URLEncoder.encode("1", "UTF-8")); /*���� ��������ȣ*/
			// urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" +
			// URLEncoder.encode("10", "UTF-8")); /*�� ������ ��� ��*/

			URL url = new URL(urlBuilder);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); //"UTF-8" �Ͽ� �޴� ������ ���ڵ�
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
			}
			String sb = "";
			String line;
			while ((line = rd.readLine()) != null) {
				sb = sb.concat(line);
				
				//���� �����Ͱ� �ִ� "item" ������Ʈ���� ���� �� JSON�迭�̱⿡ item�� �迭����			
				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(sb);
				JSONObject parse_response = (JSONObject) obj.get("response");
				JSONObject parse_body = (JSONObject) parse_response.get("body");
				JSONObject parse_items = (JSONObject) parse_body.get("items");
				JSONArray parse_item = (JSONArray) parse_items.get("item");
				JSONObject camping_data;
				
				
				for (int i = 0; i < parse_item.size(); i++) {
					camping_data = (JSONObject) parse_item.get(i);
					PlaceVo vo = new PlaceVo();
					vo.setP_name((String) camping_data.get("facltNm"));
					vo.setP_addr((String) camping_data.get("addr1"));
					vo.setP_tel((String) camping_data.get("tel"));
					vo.setP_filename((String) camping_data.get("firstImageUrl"));
					vo.setP_x((Double) camping_data.get("mapX"));
					vo.setP_y((Double) camping_data.get("mapY"));

					list.add(vo);
				}

				rd.close();
				conn.disconnect();
			}

		} // end try-catch
		catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		model.addAttribute("list", list);
		return "place/place_list";
	}// end-search

}// end-controller