package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import controllers.MainPageController;

public class ChatClient {
	private MainPageController mainPageController;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	private SSLSocket socket;
	private String serverName;
	private int port;
	
	
	public ChatClient (String serverName, int port,MainPageController mainPageController) {
		super();
		this.serverName = serverName;
		this.port = port;
		this.mainPageController = mainPageController;
	
		
	}
	
	public SSLSocket getSocket() {
		return socket;
	}
	public boolean start() {
		try {
			System.setProperty("javax.net.ssl.trustStore",App.properties.getProperty("KEY_STORE_PATH"));
			System.setProperty("javax.net.ssl.trustStorePassword",App.properties.getProperty("KEY_STORE_PASSWORD"));
			SSLSocketFactory sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			socket = (SSLSocket) sslSocketFactory.createSocket(serverName,port);
			if(socket == null) {
				System.out.println("SOCKET JE NULL");
			}else {
				System.out.println("socket je razlicit od null");
			}
			try {
				objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				objectInputStream = new ObjectInputStream(socket.getInputStream());
			} catch (Exception e) {
				Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			}
			
			
			new ChatClientThread(objectInputStream,mainPageController,this).start();
			objectOutputStream.writeObject(App.activeUser.getUsername());
			return true;
		} catch (IOException e) {
			// TODO: handle exception
			disconnect();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			
			return false;
		}
		
		
	}
	
	public void sendMessage(String msg) {
		try {
			System.out.println("Poslali smo poruku: " + msg);
			objectOutputStream.writeObject(msg);
		}
		catch(IOException e) {
			System.out.println("Exception in sendMessage");
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
	}
	
	private void disconnect() {
		
		System.out.println("DISKONEKTUJE SE " + App.activeUser.getUsername());
		try { 
			if(objectInputStream != null) {
				objectInputStream.close();
			}
			
		}
		catch(Exception e) {
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		try {
			if(objectOutputStream != null) {
				objectOutputStream.flush();
				objectOutputStream.close();
			}
			
		}
		catch(Exception e) {
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
        try{
			if(socket != null) socket.close();
			
			
		}
		catch(Exception e) {
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
			
	}
	
}
