package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import org.omg.CORBA.PUBLIC_MEMBER;

public class ChatServer {
	private int port;
	private ArrayList<ChatThread> activeChatClients = new ArrayList<ChatThread>();

	public static final String LOG_FILE = "chatLogs.log";
	public static final String PROP_FILE = "chatProp.properties";
	public final static Logger LOGGER = Logger.getLogger(ChatServer.class.getName());
	public static Handler handler;
	public static Properties properties;
	
	
	public ChatServer(int port) {
		super();
		this.port = port;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}


	public ArrayList<ChatThread> getActiveChatClients() {
		return activeChatClients;
	}


	public void setActiveChatClients(ArrayList<ChatThread> activeChatClients) {
		this.activeChatClients = activeChatClients;
	}


	public void startServer() {
		boolean runningServer = true;
		
		try {
			SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
			ServerSocket serverSocket = sslServerSocketFactory.createServerSocket(port);
			while(runningServer) {
				System.out.println("Server je pokrenut");
				SSLSocket socket = (SSLSocket) serverSocket.accept();
				if(!runningServer) {
					break;
				}
				ChatThread chatThread = new ChatThread(this,socket);
				activeChatClients.add(chatThread);
				chatThread.start();
				
			}
			
			serverSocket.close();
			for(ChatThread chatThread : activeChatClients) {
				chatThread.close();
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Logger.getLogger(ChatServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		
		
	}
	
	
	public synchronized boolean sendMessage(String typeOfMessage,String senderString,String receiverString, String messageString) {
		System.out.println("Poruka je namijenjena za: " + receiverString);
		switch (typeOfMessage) {
		case "BROADCAST":{
			System.out.println("RIjec je o Broadcastu");
			for(ChatThread chatThread : activeChatClients) {
				if(!chatThread.getUsername().equals(senderString)) {
					System.out.println("Poruku o odjavljivanju prima: " + chatThread.getUsername());
					if(!chatThread.writeMessage(messageString)) {
						remove(chatThread);
						System.out.println("Receiver is disconnected");
					}
				}
				
			}
			return true;
		}
		case "UNICAST":{
			ChatThread receiverChatThread = getChatThread(receiverString);
			if(receiverChatThread == null) {
				System.out.println("Nije moguce poslati poruku korisniku: " + receiverString);
				return false;
			}
			if(!receiverChatThread.writeMessage(messageString)) {
				remove(receiverChatThread);
				System.out.println("Receiver is disconnected");
				
				return false;
			}
			return true;
		}
		default:
			return false;
		}
	}
	
	
	public ChatThread getChatThread(String usernameString) {
		for(ChatThread chatThread : activeChatClients) {
			if(chatThread.getUsername().equals(usernameString)) {
				return chatThread;
			}
		}
		return null;
	}
	synchronized void remove(ChatThread  chatThread) {
		System.out.println("Remove method");
		String disconnectedClient = "";
		// scan the array list until we found the Id
		for(int i = 0; i < activeChatClients.size(); ++i) {
			ChatThread ct = activeChatClients.get(i);
			// if found remove it
			if(ct.equals(chatThread)) {
				ct.close();
				disconnectedClient = ct.getUsername();
				activeChatClients.remove(i);
				break;
			}
		}
		sendMessage("BROADCAST",chatThread.getUsername(), null, chatThread.getUsername() + " je napustion chat");
	}
	public static void main(String args[]) {
		FileReader reader;
		try {
			handler = new FileHandler(LOG_FILE);
			LOGGER.addHandler(handler);
			LOGGER.setUseParentHandlers(false);
		} catch (IOException e) {
			Logger.getLogger(ChatServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		try {
			
			reader = new FileReader(PROP_FILE);
			properties=new Properties();  
			properties.load(reader);  
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Logger.getLogger(ChatServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (IOException e) {
			e.printStackTrace();
			Logger.getLogger(ChatServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}  
		System.setProperty("javax.net.ssl.keyStore", properties.getProperty("KEY_STORE_PATH_STRING"));
		System.setProperty("javax.net.ssl.keyStorePassword", properties.getProperty("KEY_STORE_PASSWORD_STRING"));
		int port = 1500;
		ChatServer chatServer = new ChatServer(Integer.parseInt(properties.getProperty("chatServerPort")));
		chatServer.startServer();
	}
}
