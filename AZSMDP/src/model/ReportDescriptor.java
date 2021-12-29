package model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

import com.sun.javafx.binding.StringFormatter;

public class ReportDescriptor implements Serializable{
	String username;
	String stationId;
	String fileName;
	LocalTime time;
	long fileSize;
	
	
	
	public ReportDescriptor() {
		super();
	}

	public ReportDescriptor(String username, String stationId, String fileName, LocalTime time, long fileSize) {
		super();
		this.username = username;
		this.stationId = stationId;
		this.fileName = fileName;
		this.time = time;
		this.fileSize = fileSize;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fileName, fileSize, stationId, time, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportDescriptor other = (ReportDescriptor) obj;
		return Objects.equals(fileName, other.fileName) && fileSize == other.fileSize
				&& Objects.equals(stationId, other.stationId) && Objects.equals(time, other.time)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "ReportDescriptor [username=" + username + ", stationId=" + stationId + ", fileName=" + fileName
				+ ", time=" + time + ", fileSize=" + fileSize + "]";
	}
	
	public String getInformation() {
		return fileName + ", " + fileSize + "byte, " + time + ", " + username + ", " + stationId; 
	}
	
	
	
}
