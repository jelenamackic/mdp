package UserServicesCentralApp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.jasper.compiler.StringInterpreterFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Line;
import model.Timetable;
import model.User;
import model.Zsmdp;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ZSMDPService {
	public static JedisPool pool = new JedisPool("localhost");
	
	
	public static Zsmdp[] getAllZsmdps() {
		ArrayList<Zsmdp> arrayList = new ArrayList<Zsmdp>();
		System.out.println("Da li je uopste uslo u ovu metodu");
		try (Jedis jedis = pool.getResource()) {
			System.out.println("Uslo u getAllZsmdps");
			Map<String, String> allZSMDPs = jedis.hgetAll("zsmdps:");
			System.out.println("REDISSSSSSS");
			Iterator hmIterator = allZSMDPs.entrySet().iterator();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			int i = 0;
		        while (hmIterator.hasNext()) {
		           System.out.println("Uslo " + i++);
		           Map.Entry mapElement = (Map.Entry)hmIterator.next();
		          // System.out.println("Key: " + mapElement.getKey() + " value in bytes: " + mapElement.getValue());
		           String gsonString = (String)mapElement.getValue();
		           System.out.println("STATION FROM REDIS: "  + gsonString);
		           Zsmdp ZSMDPFromRedis = gson.fromJson(gsonString, Zsmdp.class);
		           
		           arrayList.add(ZSMDPFromRedis);
		           }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return arrayList.toArray(new Zsmdp[arrayList.size()]);
	}
	public static void putInRest(Zsmdp zsmdp) {
		try(Jedis jedis = pool.getResource()){
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonString = gson.toJson(zsmdp);
			System.out.println("JSON pri serijalizaciji: " + jsonString);
			jedis.hset("zsmdps:", zsmdp.getIdZSMDP() + "",jsonString);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	 
	  
	  public static boolean addZSMDP(String location, String idZSMDP)
	  { 
		  if(isZSMDP(idZSMDP)) {
			  System.out.println("Zsmdp aready exists");
			  return false;
		  }else {
			  putInRest(new Zsmdp(location, idZSMDP));
			  return true;
		  }
		  
	  }
	
	  
	  public static boolean isZSMDP(String id) {
		 try(Jedis jedis = pool.getResource()){
			 String redisString = jedis.hget("zsmdps:", id);
			 Gson gson = new GsonBuilder().setPrettyPrinting().create();
			 if(redisString != null) {
				 Zsmdp zsmdp = gson.fromJson(redisString, Zsmdp.class);
				 System.out.println("Pronadjena zsmdp: " + zsmdp);
				 return true;
			 }
			 return false;
		 }catch (Exception e) {
			 e.printStackTrace();
			// TODO: handle exception
			 return false;
		}
		  
	  }
	  
	  
	 /* public static void main(String args[]) {
		  addZSMDP("BanjaLuka", "BL");
		  addZSMDP("Doboj", "D");
		  addZSMDP("Trebinje", "T");
		  addZSMDP("Prijedor", "P");
		  addZSMDP("Bijeljina", "B");
		  addZSMDP("Sarajevo", "S");
	  } */
	  
}
