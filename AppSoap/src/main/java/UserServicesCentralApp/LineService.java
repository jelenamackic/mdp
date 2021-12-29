package UserServicesCentralApp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.el.parser.BooleanNode;
import org.w3c.dom.ls.LSOutput;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Line;
import model.Timetable;
import model.User;
import model.Zsmdp;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class LineService {
	public static String instanceName = "Test";
	public static ArrayList<Line> lines = new ArrayList<Line>();
	public static ArrayList<Integer> lineIDs = new ArrayList<Integer>();
	public static JedisPool pool = new JedisPool("localhost");
	public static Line[] getAllLines() {
		
		return readAllFromRedis();
	}
	
	public static Line[] getLinesForOneStation(String stationId) {
		if(!ZSMDPService.isZSMDP(stationId)) {
			System.out.println("Ne postoji stanica");
			return null;
		}
		ArrayList<Line> linesOfStation = new ArrayList<Line>();
		Line[] linesArray = getAllLines();
		if(linesArray != null) {
			for(Line line : linesArray) {
				if(line.findStation(stationId + "")) {
					System.out.println("Pronadjena stanica  " + stationId);
					linesOfStation.add(line);
				}
			}
			return linesOfStation.toArray(new Line[linesOfStation.size()]);
		}else {
			System.out.println("Nema stanica na datoj liniji");
			return linesArray;
		}
		
	}
	//servis sam koristila na soap i sve dobro radi 
	public static boolean addLine(int lineId, Timetable[] stations, int numberOfStations) {
		Line line = new Line(lineId,stations,numberOfStations);
		if(isLine(lineId)) {
			System.out.println("Linija vec postoji");
			return false;
		}
		putInRedis(line);
		lineIDs.add(lineId);
		return lines.add(line);
	}
	
	public static boolean deleteLine(int lineId) {
		try(Jedis jedis = pool.getResource()){
			if(jedis.hdel("lines:", lineId + "") == 1) {
				System.out.println("Izbrisana linija: " + lineId);
				return true;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public static boolean isLine(int lineId) {
		try (Jedis jedis = pool.getResource()){
			jedis.set(instanceName, "OK");
			jedis.get(instanceName);
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String gsonString = jedis.hget("lines:", lineId+"");
			Line line = gson.fromJson(gsonString, Line.class);
			if(line != null) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void putInRedis(Line line) {
		
		try (Jedis jedis = pool.getResource()) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonString = gson.toJson(line);
			System.out.println("JSON pri serijalizaciji: " + jsonString);
			jedis.hset("lines:", line.getLineID() + "",jsonString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Line[] readAllFromRedis() {
		//Line linesArray [] = null;
		ArrayList<Line> arrayList = new ArrayList<Line>();
			
			try (Jedis jedis = pool.getResource()) {

				// string
				jedis.set(instanceName, "OK");
				System.out.println(instanceName);
				System.out.println(jedis.get(instanceName));
				System.out.println("==================================");
				Map<String, String> allLines = jedis.hgetAll("lines:");
				Iterator hmIterator = allLines.entrySet().iterator();
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				int i = 0;
			        while (hmIterator.hasNext()) {
			           System.out.println("Uslo " + i++);
			           Map.Entry mapElement = (Map.Entry)hmIterator.next();
			          // System.out.println("Key: " + mapElement.getKey() + " value in bytes: " + mapElement.getValue());
			           String gsonString = (String)mapElement.getValue();
			           System.out.println("LINE FROM REDIS: "  + gsonString);
			           Line lineFromRedis = gson.fromJson(gsonString, Line.class);
			           arrayList.add(lineFromRedis);
			           
			           
			        }
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return arrayList.toArray(new Line[arrayList.size()]);
	}
	
	public static Timetable makeTimetable(String estimatedTime, String stationID) {
		if(!ZSMDPService.isZSMDP(stationID)) {
			System.out.println("Ne postoji stanica sa ovim id " + stationID);
			return null;
		}
		return new Timetable(estimatedTime,stationID);
	}
	
	public String writeLine(Line line) {
		System.out.println("WRITE LINE ");
		String allStationString = "";
		for(Timetable timetable : line.getStations()) {
			System.out.println("uslo u for");
			allStationString += timetable.getStationId() + " (" + timetable.getEstimatedTime();
			if(timetable.isPassed()) {
				allStationString += ", " + timetable.getRealTime();
			}
			allStationString += ")    ";
		}
		return "Line " + line.getLineID() + " " + allStationString;
	}
	
	public Line getALine(String lineId) {
		try (Jedis jedis = pool.getResource()){
			String redisString = jedis.hget("lines:", lineId);
			if(redisString != null) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				Line line = gson.fromJson(redisString, Line.class);
				return line;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public void markTheStationIsPassed(String lineID, String stationID, String time) {
		Line line = getALine(lineID);
		line.setStationPassed(stationID, time);
		putInRedis(line);
		System.out.println("kkkk");
		System.out.println("LINE iz fje" + writeLine(line));
		
		
		System.out.println("Line dohvaceno: " + writeLine(getALine(lineID)));
		
	}
	
	
	
	

	
	
	

}
