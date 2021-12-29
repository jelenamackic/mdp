package application;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.rpc.ServiceException;

import UserServicesCentralApp.UserService;
import UserServicesCentralApp.UserServiceServiceLocator;
import model.User;

public class ClientUserService {

	public boolean addUser(String username,String password, String idZSMDP) {
		//provjera
		UserServiceServiceLocator locator = new UserServiceServiceLocator();
		try {
			UserService userService = locator.getUserService();
			try {
				model.User [] users= userService.getUsers();
				if(users != null) {
					for(model.User u: users) {
						System.out.println(u + "KORISNIK u 35");
					}
				}else {
					System.out.println("Jos uvijek nije dodan user");
				}
				
				return userService.addUser(username, password, idZSMDP);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				e.printStackTrace();
				Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
				return false;
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			return false;
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
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return null;
	}
	public boolean deleteUser(String username) {
		UserServiceServiceLocator locator = new UserServiceServiceLocator();
		try {
			UserService userService = locator.getUserService();
			try {
				model.User [] users= userService.getUsers();
				if(users != null) {
					for(model.User u: users) {
						System.out.println(u + " KORISNIK");
						if(u.getUsername().equals(username)){
							return userService.deleteUser(u);
						}
					}
					System.out.println("Nepoznat korisnik");
					
				}else {
					System.out.println("Jos uvijek nije dodan user");
					
				}
				
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				e.printStackTrace();
				Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
				
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			
		}
		return false;
	}
	
	
	public User[] getUsers(){
		User [] users = null;
		UserServiceServiceLocator locator = new UserServiceServiceLocator();
		try {
			UserService userService = locator.getUserService();
			try {
				
				users = userService.getUsers();
				System.out.println("Da li je getUsers proslo");
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				e.printStackTrace();
				Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
				
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			
		}
		return users;
	}
}
