package UserServicesCentralApp;

import java.util.ArrayList;

import model.User;
import model.Zsmdp;

public class ZSMDPUserService {

	public static ArrayList<Zsmdp> zsmdps = new ArrayList<Zsmdp>();

	  public static Zsmdp[]  getZSMDPs() { 
		  if(zsmdps.size()==0) {
			  System.out.println("Nema Zsmdp");
		  }
		  return zsmdps.toArray(new Zsmdp[zsmdps.size()]); }
	  
	  public static boolean addZSMDP(String location, String idZSMDP)
	  { 
		  if(isZSMDP(idZSMDP)) {
			  System.out.println("Zsmdp aready exists");
			  return false;
		  }else {
			  return zsmdps.add(new Zsmdp(location,idZSMDP)); 
		  }
		  
	  }
	  
	  public static boolean deleteUser(User user) { 
		  return zsmdps.remove(user);
		  
	  }
	  
	  public static boolean isZSMDP(String id) {
		  Zsmdp[] zsmdps = getZSMDPs();
		  for(Zsmdp zsmdp: zsmdps) {
			  if(zsmdp.getIdZSMDP().equals(id)) {
				  return true;
			  }
		  }
		  return false;
	  }
	  
	 
}
