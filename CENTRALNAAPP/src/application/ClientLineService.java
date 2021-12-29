package application;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.rpc.ServiceException;

import UserServicesCentralApp.LineService;
import UserServicesCentralApp.LineServiceServiceLocator;
import UserServicesCentralApp.NewLineService;
import UserServicesCentralApp.NewLineServiceServiceLocator;
import model.Line;
import model.Timetable;



public class ClientLineService {
	public boolean addLine(int lineID,Timetable[] stations, int numOfStations) {
		//provjera
		LineServiceServiceLocator locator = new LineServiceServiceLocator();
		try {
			LineService lineService = locator.getLineService();
			try {
				return lineService.addLine(lineID, stations, numOfStations);
			} catch (RemoteException e) {
				
				e.printStackTrace();
				Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
				return false;
			}
			
		} catch (ServiceException e) {
			
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			return false;
		}
	}
	
	public boolean deleteLine(int lineId) {
		LineServiceServiceLocator locator = new LineServiceServiceLocator();
		try {
			LineService service = locator.getLineService();
			return service.deleteLine(lineId);
		} catch (ServiceException e) {
			
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (RemoteException e) {
			
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return false;
	}
	
	public Line[] viewAllLines() {
		LineServiceServiceLocator locator = new LineServiceServiceLocator();
		try {
			LineService service = locator.getLineService();
			
			return service.getAllLines();
		} catch (ServiceException e) {
			
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (IOException e) {
			
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return null;
		
	}
	
	public String writeLine(Line line) {
		LineServiceServiceLocator locator = new LineServiceServiceLocator();
		try {
			LineService service = locator.getLineService();
			System.out.println("LINEEEEE" + service.writeLine(line));
			return service.writeLine(line);
		} catch (ServiceException e) {
			
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (RemoteException e) {
			
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return "";
		
	}
	
	public Timetable geTimetable(String statioId,String estimatedTime) {
		LineServiceServiceLocator locator = new LineServiceServiceLocator();
		try {
			LineService service = locator.getLineService();
			
			return service.makeTimetable(estimatedTime, statioId);
		} catch (ServiceException e) {
			
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (RemoteException e) {
			
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return null;
	}

	
}
