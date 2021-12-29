package UserServicesCentralApp;

import java.util.ArrayList;

import model.Line;
import model.Timetable;
import redis.clients.jedis.JedisPool;

public class NewLineService {
	public static String instanceName = "Test";
	public static ArrayList<Line> lines = new ArrayList<Line>();
	public static ArrayList<Integer> lineIDs = new ArrayList<Integer>();
	public static JedisPool pool = new JedisPool("localhost");
	public static Line[] getAllLines() {
		return lines.toArray(new Line[lines.size()]);
	}
	
	public static Line[] getLinesForOneStation(int stationId) {
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
	
	public static boolean addLine(int lineId, Timetable[] stations, int numberOfStations) {
		Line line = new Line(lineId,stations,numberOfStations);
		//putInRedis(line);
		lineIDs.add(lineId);
		return lines.add(line);
	}
	
	public static boolean deleteLine(int lineId) {
		Line[] linesArray = getAllLines();
		for(Line line: linesArray) {
			if(line.getLineID() == lineId) {
				return lines.remove(line);
			}
		}
		return false;
	}
	public static boolean isLine(int lineId) {
		Line[] linesArray = getAllLines();
		if(linesArray != null) {
			for(Line line : linesArray) {
				if(lineId == line.getLineID()) {
					return true;
				}
			}
		}
		return false;
	}
	
	
}
