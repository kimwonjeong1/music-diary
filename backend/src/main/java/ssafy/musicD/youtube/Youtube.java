package ssafy.musicD.youtube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Youtube {
	@Value("${youtube.apikey}")
	private String apikey;

	public String search(String search) throws IOException {
		String apiurl = "https://www.googleapis.com/youtube/v3/search";
		apiurl += "?key=";
		apiurl += apikey;
		apiurl += "&part=id&type=video&maxResults=10&videoEmbeddable=true";
		apiurl += "&q=" + URLEncoder.encode(search, "UTF-8");

		URL url = new URL(apiurl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();
		return response.toString();
	}

	public String getVideoId(String search) {
		String videoId = "";
		String jsonData = "";
		try {
			jsonData = this.search(search);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			JSONParser jsonParse = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParse.parse(jsonData);
			JSONArray itemsArray = (JSONArray) jsonObj.get("items");
			JSONObject itemObject = (JSONObject) itemsArray.get(0);
			JSONObject id = (JSONObject)itemObject.get("id");
			videoId = (String)id.get("videoId");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return videoId;
	}
}