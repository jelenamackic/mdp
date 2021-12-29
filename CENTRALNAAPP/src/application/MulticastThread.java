package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.ls.LSOutput;

import com.sun.javafx.binding.StringFormatter;

import controllers.ReadNotificationFormController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MulticastThread extends Thread{
	
	
	protected byte[] buf = new byte[256];
	public MulticastSocket socket;


	public MulticastThread(MulticastSocket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
			byte buffer[] = new byte[256];
			InetAddress address;
			try {
				address = InetAddress.getByName(Main.properties.getProperty("multicastHost"));
				while (true) {
					   DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
					 
						   socket.receive(packet);
			               String received = new String(packet.getData(), 0, packet.getLength());
			               String parts[] = received.split("#");
			               if(parts.length >= 3) {
			            	   String userNameString = parts[0];
				               String titleString = parts[1];
				               String contentString = parts[2];
				              
				               System.out.println("Title: " + titleString + "\n Content: " + contentString);
				              // System.out.println("Title: " + titleString + "\n Content: " + contentString);
			            	   	FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.properties.getProperty("readNotificationForm")));	
				    			Parent rootParent = loader.load();
				    			ReadNotificationFormController readNotificationFormController = loader.getController();
				    			Platform.runLater(() -> Main.mainPageController.showNotification(titleString, contentString, userNameString));
				    				
			               }
			              
			   
				} 
			} catch (UnknownHostException e) {
				Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
				e.printStackTrace();
			} catch (IOException e) {
				Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
				e.printStackTrace();
			}
			
		
	}
}
