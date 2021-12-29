package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class Timetable implements Serializable, Comparable<Timetable>{
	private String stationId;
	private String estimatedTime;
	private String realTime = "";
	private boolean isPassed = false;
	
	public Timetable() { }
	public Timetable(String estimatedTime, String realTime,String stationId, boolean isPassed) {
		super();
		this.estimatedTime = estimatedTime;
		this.realTime = realTime;
		this.setStationId(stationId);
		this.isPassed = isPassed;
	}
	
	public Timetable(String estimatedTime,String stationId) {
		super();
		this.estimatedTime = estimatedTime;
		this.setStationId(stationId);
		
	}
	public String getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public String getRealTime() {
		return realTime;
	}
	public void setRealTime(String realTime) {
		this.realTime = realTime;
	}
	public boolean isPassed() {
		return isPassed;
	}
	public void setPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(estimatedTime, isPassed, realTime, stationId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timetable other = (Timetable) obj;
		return Objects.equals(estimatedTime, other.estimatedTime) && isPassed == other.isPassed
				&& Objects.equals(realTime, other.realTime) && Objects.equals(stationId, other.stationId);
	}
	
	
	@Override
	public String toString() {
		return "Timetable [stationId=" + stationId + ", estimatedTime=" + estimatedTime + ", realTime=" + realTime
				+ ", isPassed=" + isPassed + "]";
	}
	
	public HashMap<String, String> toMap(){
		HashMap<String, String> obj = new HashMap<>();
		obj.put("stationId", stationId);
		obj.put("estimatedTime", estimatedTime);
		obj.put("realTime", realTime);
		obj.put("isPassed", isPassed + "");
		return obj;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	@Override
	public int compareTo(Timetable o) {
		if(Integer.parseInt(o.getEstimatedTime().substring(0, o.getEstimatedTime().indexOf(":"))) > Integer.parseInt(this.getEstimatedTime().substring(0, o.getEstimatedTime().indexOf(":"))) ) {
			return 1;
		}else if(Integer.parseInt(o.getEstimatedTime().substring(0, o.getEstimatedTime().indexOf(":"))) ==
				Integer.parseInt(this.getEstimatedTime().substring(0, o.getEstimatedTime().indexOf(":")))  &&
				Integer.parseInt(o.getEstimatedTime().substring(o.getEstimatedTime().indexOf(":"), o.getEstimatedTime().length())) >
		Integer.parseInt(this.getEstimatedTime().substring(o.getEstimatedTime().indexOf(":"), o.getEstimatedTime().length()))) {
			
			
			return 1;
			
		}else {
			return -1;
		}
		
	}
	
}