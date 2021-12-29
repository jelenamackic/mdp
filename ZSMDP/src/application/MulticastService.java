package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import controllers.MainPageController;

public class MulticastService {
		public static MulticastSocket socket = null;
		public static boolean receive = true;
		public MainPageController mainPageController;
		public MulticastService() {
		
			
	        byte[] buf = new byte[256];
	        try {
	            socket = new MulticastSocket(Integer.parseInt(App.properties.getProperty("MULTICAST_PORT")));
	            InetAddress address = InetAddress.getByName(App.properties.getProperty("MULTICAST_HOST"));
	            socket.joinGroup(address);
	            MulticastThread multicastThread = new MulticastThread(socket);
	            multicastThread.multicastService = this;
	            multicastThread.start();
	            
	            System.out.println("After start");
	        } catch (IOException e) {
	            System.out.println(e);
	            Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
	        }
	    }
		
		
		
		public static  void sendNotification(String username, String title, String content) {
			String messageString = username + "#" + title + "#" + content;
			byte [] buf = messageString.getBytes();
			if(socket == null) {
				System.out.println("NULLLLLLLLLLLLL");
			}
			InetAddress address;
			try {
				address = InetAddress.getByName(App.properties.getProperty("MULTICAST_HOST"));
				DatagramPacket packet = new DatagramPacket(buf, buf.length,address,Integer.parseInt(App.properties.getProperty("MULTICAST_PORT")));
				
				if(packet ==  null) {
					System.out.println("packet is null");
				}
				try {
					MulticastService.receive = false;
					socket.send(packet);
					Thread.sleep(1000);
					MulticastService.receive = true;
				} catch (IOException e) {
					
					e.printStackTrace();
					Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
				} catch (InterruptedException e) {
				
					e.printStackTrace();
					Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
				}
			} catch (UnknownHostException e) {
			
				e.printStackTrace();
				Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			}
			
		} 
}
