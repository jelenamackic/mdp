package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import org.apache.jasper.tagplugins.jstl.core.If;

public class Line implements Serializable {
	private int lineID;
	private Timetable[] stations;
	private int numOfStations;
	
	public Line() {
		super();
	}
	public Line(int lineID,Timetable[] stations, int numOfStations) {
		super();
		this.lineID = lineID;
		this.stations = stations;
		this.numOfStations = numOfStations;
	}
	
	
	public int getLineID() {
		return lineID;
	}
	public void setLineID(int lineID) {
		this.lineID = lineID;
	}
	public Timetable[] getStations() {
		return stations;
	}
	public void setStations(Timetable[] stations) {
		this.stations = stations;
	}
	@Override
	public int hashCode() {
		return Objects.hash(lineID, stations);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Line other = (Line) obj;
		return lineID == other.lineID && Objects.equals(stations, other.stations);
	}
	@Override
	public String toString() {
		return "Line [lineID=" + lineID + ", stations=" + stations + "]";
	}
	
	public HashMap<String, String> toMap(){
		HashMap<String, String> obj = new HashMap<>();
		obj.put("lineID", lineID + "");
		return obj;
	}
	
	public boolean findStation(String stationId) {
		for(Timetable timetable : stations) {
			if(timetable.getStationId().equals(stationId)) {
				return true;
			}
		}
		return false;
	}
	public boolean setStationPassed(String stationID,String realTime) {
		for(Timetable timetable : stations) {
			if(timetable.getStationId().equals(stationID)) {
				timetable.setPassed(true);
				timetable.setRealTime(realTime);
				return true;
			}
		}
		return false;
	}
	
	public String writeLine() {
		System.out.println("WRITE LINE ");
		String allStationString = "";
		for(Timetable timetable : getStations()) {
			System.out.println("uslo u for");
			allStationString += timetable.getStationId() + " (" + timetable.getEstimatedTime();
			if(timetable.isPassed()) {
				allStationString += ", " + timetable.getRealTime();
			}
			allStationString += ")    ";
		}
		return "Line " + getLineID() + " " + allStationString;
	}
	
	
	
}
