package model.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Service ("holyDayService")
public class HolyDayService {
	// 공공 데이터 에서 받은 code
	private static final String SERVICE_CODE = "lMRbRl%2BmjOxVJXeAnFwSgfeB5ZhzVjnpCaRLIPKwJ%2BoNX1GT2PtsVmJoyWuuGD%2BwNHs5PkayxxxFWla29YQcPQ%3D%3D";
	
	// y = 연도, m= 월
	public static List<String> get(int y , int m) {
		HttpURLConnection conn = null;
		List<String> resultList = new ArrayList<String>();
		try {
			// 공공data에 휴일관련 api의 년과 월을 주어 휴일을 확인함
			URL url = new URL("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?ServiceKey="+SERVICE_CODE+
					"&solYear="+y+"&solMonth="+m);
			conn = (HttpURLConnection) url.openConnection();
			
			// accept 언어를 한글로
			conn.setRequestProperty("Accept-language", "ko");
			
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(conn.getInputStream());
			
			boolean ok = false;
			Element e;
			// header라는 node
			NodeList nodeList= doc.getElementsByTagName("header");
			
			if(nodeList.getLength()>0) {
			e= (Element)nodeList.item(0);
			// resultCode가 00 이라면 ok는 true
				if(e.getElementsByTagName("resultCode").item(0).getTextContent().equals("00")) {
					ok = true;
				}else {
					ok = false;
					resultList.add("error");
				}
				
			}
			
			if(ok) { // true라면 item node를 가져옴
				nodeList = doc.getElementsByTagName("item");
				for (int i = 0; i < nodeList.getLength(); i++) {
					e = (Element)nodeList.item(i);
					// 휴일을 가져옴
					resultList.add(e.getElementsByTagName("locdate").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(conn != null) {
			// 연결 해제
			conn.disconnect();
		}
		return resultList;
	}
	
}
