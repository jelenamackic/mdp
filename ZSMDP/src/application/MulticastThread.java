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

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import org.w3c.dom.ls.LSOutput;

import com.sun.javafx.binding.StringFormatter;

import controllers.ReadNotificationFormController;
import controllers.SetTimeFormController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MulticastThread extends Thread{
	
	
	protected byte[] buf = new byte[256];
	public MulticastSocket socket;
	private static final int PORT = 20000;
	private static final String HOST = "224.0.0.11";
	public MulticastService multicastService;
	public MulticastThread(MulticastSocket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
			byte buffer[] = new byte[256];
			InetAddress address;
			try {
				address = InetAddress.getByName(HOST);
				while (true) {
					   DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
					   	if(!socket.isClosed()) {
					   	
						   socket.receive(packet);
			               String received = new String(packet.getData(), 0, packet.getLength());
			               String parts[] = received.split(App.properties.getProperty("messageSeparator"));
			               if(parts.length >= 3) {
			            	   String userNameString = parts[0];
				               String titleString = parts[1];
				               String contentString = parts[2];
				               if(App.activeUser.getUsername().equals(userNameString)) {
				            	   System.out.println("Ova poruka se ne prima jer je od mene samog");
				               }else {
				            	   System.out.println("Title: " + titleString + "\n Content: " + contentString);
				            	   Platform.runLater(() -> 
				            	   multicastService.mainPageController.showNotification(titleString, contentString, userNameString));
					    			
					    			
				               }
				               
			               }
					   	}

				} 
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			}
			
		
	}
}
