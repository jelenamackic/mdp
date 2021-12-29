package rmi;

import java.io.FileDescriptor;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.ArrayList;

import model.ReportDescriptor;

public interface ReportServiceInterface extends Remote  {
	public void uploadReport(String username,String stationId,String filename, LocalTime time, long fileSize, byte[] data) throws RemoteException;
	public byte[] downloadReport(String fileName) throws RemoteException;
	public ArrayList<ReportDescriptor> getInormationAboutAllReports() throws RemoteException;
	
	
}
