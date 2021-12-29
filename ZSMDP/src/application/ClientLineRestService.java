package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.ls.LSOutput;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Line;


public class ClientLineRestService {

	static Client client = ClientBuilder.newClient();
	public static WebTarget target = client.target(App.properties.getProperty("BASE_URL"));
	
	public static void makeStationPassed(String lineID, String stationID, String time) {
		
			/*URL url = new URL(BASE_URL + "/" + lineID + "/" + "setPassed/" + stationID);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type", "application/json");
			if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("Failed: HTTP error code : " + connection.getResponseCode());
				
			}
			JSONObject
			jsonObject. */
			
			
			Response response = target.path(lineID + "/setPassed/" + stationID).request(MediaType.APPLICATION_JSON).put(Entity.json(time));
			if(response.getStatus() == Status.OK.getStatusCode()) {
				System.out.println("Uspjesna izmjena");
			}
			
		
	}
	
	public static ArrayList<Line> getAllLines() {
		JSONArray jsonArray;
		ArrayList<Line> lines = new ArrayList<Line>();
		try {
			jsonArray = readAllJSON(App.properties.getProperty("BASE_URL"));
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject obj = jsonArray.getJSONObject(i);
				String jsonString = obj.toString();
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				Line line = gson.fromJson(jsonString, Line.class);
				System.out.println("LINE: " + line);
				lines.add(line);
						
			}
			return lines;
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			return null;
		}
		
		
	}
	public static ArrayList<Line> getAllLinesForStation(String stationID) {
		JSONArray jsonArray;
		ArrayList<Line> lines = new ArrayList<Line>();
		try {
			jsonArray = readAllJSON(App.properties.getProperty("BASE_URL") + "/stations/" + stationID);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject obj = jsonArray.getJSONObject(i);
				String jsonString = obj.toString();
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				Line line = gson.fromJson(jsonString, Line.class);
				System.out.println("LINE: " + line);
				lines.add(line);
						
			}
			return lines;
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			return null;
		}
		
		
	}
	public static String readALine(String lineID) {
		Response response = target.path(lineID).request(MediaType.APPLICATION_JSON).get();
		return response.readEntity(String.class);
	}
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readOne(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static JSONArray readAllJSON(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONArray json = new JSONArray(jsonText);
			return json;
		} finally {
			is.close();
		}
	}


	public static void main(String[] args) {
		getAllLines().stream().forEach(l -> System.out.println(l));
		getAllLinesForStation("BL").stream().forEach(l -> System.out.println(l));
		makeStationPassed("1", "BL", "13:30");
		getAllLinesForStation("BL").stream().forEach(l ->System.out.println( "aaaaaaa" + l.getStations()[1].getRealTime()));
		System.out.println("TEST");
		System.out.println(readALine("1"));
		
	}
}
