package UserServicesCentralApp;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.apache.tomcat.util.descriptor.web.LoginConfig;

import model.User;
public class UserService {
	
	  public static ArrayList<User> users = new ArrayList<User>();
	  public static ArrayList<User> activeUsers = new ArrayList<User>();
	  public static User[]  getUsers() { 
		  return deserializeWithXML();
	  }
	  
	  public static boolean addUser(String username, String password, String idZSMDP)
	  { 
			System.out.println("Uslo");
			  if(!ZSMDPService.isZSMDP(idZSMDP)) {
			  System.out.println("Ne postoji stanica sa ovim id // to do"); // return false;
			  return false;
			  }
			 
		  if(isAlreadyUser(username)) {
			  System.out.println("User aready exists");
			  return false;
		  }else {
			  User user = new User(username,password,idZSMDP);
			  System.out.println("Prije s " + user + "kako???");
			  serializeWithXML(user);
			  return users.add(user); 
		  }
		  
	  }
	  
	  public static boolean deleteUser(User user) {
		  if(isActive(user)) {
			  System.out.println("Aktivan user ne moze biti obrisan");
			  return false;
		  }
		  deleteFile(user.getUsername());
		  return users.remove(user);
	  }
	  
	  public static String isUser(String username, String password) {
		  User[] users = getUsers();
		  for(User user: users) {
			  if(user.getUsername().equals(username) 
					  && user.getPassword().equals(password)) {
				  return user.getIdZSMDP();
			  }
		  }
		  return null;
	  }
	  
	  public static boolean isAlreadyUser(String username) {
		  User[] users = getUsers();
		  for(User user: users) {
			  if(user.getUsername().equals(username)) {
				  System.out.println("Pronaslo je istog usera");
				  return true;
			  }
		  }
		  return false;
	  }
	  
	  
	  
	  
	  public static void serializeWithXML(User user) {
			System.out.println("Uslo u serijalizaciju");
			try {
				
				File file = new File("C:\\Users\\admin\\eclwrkspace\\AppSoap\\users\\"
				+ user.getUsername() + ".out" );
				
				file.createNewFile();
				System.out.println("NAPRAVILO FAJL " + file.getAbsolutePath());
				XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file));
				encoder.writeObject(user);
				encoder.flush();
				encoder.close();
				
			} catch (Exception e) {
				e.printStackTrace();

			}
			System.out.println("Prosla serijalizacija");
			
		}

	  public static void main(String args[]) {
		
	  }
	  public static User getThisUser(String username, String password) {
		  User user = null;
		  try {
				
				File dir = new File("C:\\Users\\admin\\eclwrkspace\\AppSoap\\users\\" + username + ".out");
				if(dir.exists()) {
					System.out.println("Nasao je usera");
					XMLDecoder decoder = new XMLDecoder(new FileInputStream(dir));
					user = (User) decoder.readObject();
					if(!user.getPassword().equals(password)) {
						user = null;
					}
					decoder.close();
					
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		  return user;
	  }
		public static User [] deserializeWithXML() {
			User [] nizUsers = null;
			ArrayList<User> usersInDesserialization = new ArrayList<User>();
		
			try {
				
				File dir = new File("C:\\Users\\admin\\eclwrkspace\\AppSoap\\users\\");
				//System.out.println(dir.isDirectory());
				File[] directoryListing = dir.listFiles();
				if (directoryListing != null) {
					for (File child : directoryListing) {
						System.out.println(child.getName());
						XMLDecoder decoder = new XMLDecoder(new FileInputStream(child)); 
						User user = (User)decoder.readObject();
						//System.out.println(user);
						usersInDesserialization.add(user);
						decoder.close();
						
					}
					System.out.println(usersInDesserialization.toString());
					users = usersInDesserialization;
					return usersInDesserialization.toArray(new User[usersInDesserialization.size()]);
				} else {
					// Handle the case where dir is not really a directory.
					// Checking dir.isDirectory() above would not be sufficient
					// to avoid race conditions with another process that deletes
					// directories.
					return null;
				}

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			// System.out.println("Trajanje XML deserijalizacije: " +
			// (System.currentTimeMillis() - start));
		}
		public static void deleteFile(String filename) {
			File dir = new File("C:\\Users\\admin\\eclwrkspace\\AppSoap\\users\\");
			  System.out.println(dir.isDirectory());
			  File[] directoryListing = dir.listFiles();
				if (directoryListing != null) {
					for (File child : directoryListing) {
						System.out.println(child.getName());
						if(child.getName().equals(filename + ".out")) {
							child.delete();
							break;
						}
					}
				}
		}
		
		public static User[] getUsersFromStation(String stationId) {
			ArrayList<User> usersFromStation = new ArrayList<User>();
			for(User user : getUsers()) {
				if(user.getIdZSMDP().equals(stationId)) {
					usersFromStation.add(user);
				}
			}
			return usersFromStation.toArray(new User[usersFromStation.size()]);
		}
	  
		public static User login(String username, String password) {
			System.out.println("LOGINNNN");
			User user = getThisUser(username, password);
			if(user == null) {
				System.out.println("Nije user");
			}else {
				if(isActive(user)) {
					System.out.println("User je vec prijavljen");
				}else {
					System.out.println("Uspjesna prijava");
					activeUsers.add(user);
					return user;
				}
				
			}
			return null;
		}
	  
		public static boolean isActive(User user) {
			for(User u : activeUsers) {
				System.out.println("USER FROM ACTIVE: " + u.getUsername());
				if(u.getUsername().equals(user.getUsername())) {
					System.out.println("User je vec aktivan");
					return true;
				}
			}
			return false;
		}
		
		public static void logout(User user) {
			activeUsers.remove(user);
		}
		
		public static User[] getActiveUsersForStation(String stationId) {
			ArrayList<User> activeUsersFromStation = new ArrayList<User>();
			for(User user: activeUsers) {
				if(user.getIdZSMDP().equals(stationId)) {
					activeUsersFromStation.add(user);
				}
			}
			return activeUsersFromStation.toArray(new User[activeUsersFromStation.size()]);
		}
}
