package net.etfbl.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.RuntimeException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class Main {
	public static final String LOG_FILE = "restLogs.log";
	public static final String PROP_FILE = "restProp.properties";
	public final static Logger LOGGER = Logger.getLogger(Main.class.getName());
	public static Handler handler;
	public static Properties properties;
	//public static final String BASE_URL = "http://localhost:8080/pr/api/lines";
	
	
	static {
		System.out.println("STATICKI BLOK");
		try {
			handler = new FileHandler(LOG_FILE,true);
			LOGGER.addHandler(handler);
			LOGGER.setUseParentHandlers(false);
		} catch (IOException e) {
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		
		FileReader reader;
		try {
			
			reader = new FileReader(PROP_FILE);
			properties=new Properties();  
			properties.load(reader);  
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (IOException e) {
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}  
	}
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readOne(String id) throws IOException, JSONException {
		InputStream is = new URL(properties.getProperty("BASE_URL") + id).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static JSONArray readAllJSON() throws IOException, JSONException {
		InputStream is = new URL(properties.getProperty("BASE_URL")).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONArray json = new JSONArray(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static void deleteStudent(int id) {
		try {
			URL url = new URL(properties.getProperty("BASE_URL") + id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("DELETE");
			OutputStream os = conn.getOutputStream();
			// os.write("");
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			os.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createStudent(String studentJsonString) {
		try {
			// priprema i otvaranje HTTP zahtjeva
			URL url = new URL(properties.getProperty("BASE_URL"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST"); // slicno za PUT
			conn.setRequestProperty("Content-Type", "application/json");
			// podaci za body dio zahtjeva
			JSONObject input = new JSONObject(studentJsonString);
			// slanje body dijela
			OutputStream os = conn.getOutputStream();
			os.write(input.toString().getBytes());
			os.flush();
			// prijem odgovora na zahtjev
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			// citanje odgovora
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			os.close();
			br.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException, JSONException {
		// primjer: citanje prvog studenta
		JSONObject json = readOne("1");
		System.out.println(json.toString());
		System.out.println(json.get("firstName")); // pristup atributu
		// dodavanje
		createStudent("{\"firstName\":\"java\",\"lastName\":\"test\"}");
		deleteStudent(2);
		// ispis svih studenata nakon izmjena
		JSONArray jsonArray = readAllJSON();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			System.out.println(obj.get("firstName") + " " + obj.getString("lastName"));
		}
		//ispis svih u JSON formatu
		System.out.println(jsonArray.toString());

	}

}
