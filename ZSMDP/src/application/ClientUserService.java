package application;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.rpc.ServiceException;

import UserServicesCentralApp.UserService;
import UserServicesCentralApp.UserServiceServiceLocator;
import model.User;

public class ClientUserService {
	
	public User login(String username, String password) {
		UserServiceServiceLocator locator = new UserServiceServiceLocator();
		try {
			UserService service = locator.getUserService();
			return service.login(username, password);
			
		} catch (Exception e) {
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return null;
	}
	
	public User[] getActiveUsersForStation(String stationId){
		UserServiceServiceLocator locator = new UserServiceServiceLocator();
		try {
			UserService service = locator.getUserService();
			return service.getActiveUsersForStation(stationId);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return null;
		
	}
	public void logout(User user) {
		UserServiceServiceLocator locator = new UserServiceServiceLocator();
		try {
			UserService service = locator.getUserService();
			service.logout(user);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		
	}
	
	public String isUser(String username, String password) {
		UserServiceServiceLocator locator = new UserServiceServiceLocator();
		try {
			UserService service = locator.getUserService();
			return service.isUser(username, password);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return null;
	}
	public User getUser(String username, String password) {
		UserServiceServiceLocator locator = new UserServiceServiceLocator();
		try {
			UserService service = locator.getUserService();
			return service.getThisUser(username, password);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return null;
		
	}
	
	public User[] getUsersFromStation(String stationId) {
		UserServiceServiceLocator locator = new UserServiceServiceLocator();
		try {
			UserService service = locator.getUserService();
			return service.getUsersFromStation(stationId);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return null;
		
	}
}
