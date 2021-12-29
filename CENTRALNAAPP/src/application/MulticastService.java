package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MulticastService {

		public static MulticastSocket socket = null;
		public static boolean receive = true;
	
		public MulticastService() {
		
		
	        byte[] buf = new byte[256];
	        try {
	            socket = new MulticastSocket(Integer.parseInt(Main.properties.getProperty("multicastServerPort")));
	            InetAddress address = InetAddress.getByName(Main.properties.getProperty("multicastHost"));
	            socket.joinGroup(address);
	            MulticastThread multicastThread = new MulticastThread(socket);
	            multicastThread.start();
	            System.out.println("After start");
	        } catch (IOException ioe) {
	            System.out.println(ioe);
	            Logger.getLogger(Main.class.getName()).log(Level.WARNING, ioe.fillInStackTrace().toString());
	        }
	    }
}
