package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.ls.LSOutput;


public class ChatThread extends Thread{
	private ChatServer chatServer;
	private String username;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	private Socket socket;
	public boolean running = true;
	public ChatThread() {
		super();
	}
	
	public ChatThread(ChatServer chatServer,Socket socket) {
		super();
		this.chatServer = chatServer;
		this.socket = socket;
		try {
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			this.username = (String) objectInputStream.readObject();
			System.out.println("Korisnikov username je: " + this.username);
		} catch (IOException e) {
			
			e.printStackTrace();
			Logger.getLogger(ChatServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			Logger.getLogger(ChatServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
	}
	public ChatThread(String username, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream,
			Socket socket) {
		super();
		this.username = username;
		this.objectOutputStream = objectOutputStream;
		this.objectInputStream = objectInputStream;
		this.socket = socket;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public ObjectOutputStream getObjectOutputStream() {
		return objectOutputStream;
	}
	public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
		this.objectOutputStream = objectOutputStream;
	}
	public ObjectInputStream getObjectInputStream() {
		return objectInputStream;
	}
	public void setObjectInputStream(ObjectInputStream objectInputStream) {
		this.objectInputStream = objectInputStream;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(objectInputStream, objectOutputStream, socket, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatThread other = (ChatThread) obj;
		return Objects.equals(objectInputStream, other.objectInputStream)
				&& Objects.equals(objectOutputStream, other.objectOutputStream) && Objects.equals(socket, other.socket)
				&& Objects.equals(username, other.username);
	}
	
	
	@Override
	public String toString() {
		return "ChatThread [username=" + username + ", objectOutputStream=" + objectOutputStream
				+ ", objectInputStream=" + objectInputStream + ", socket=" + socket + "]";
	}
	
	
	
	public void run() {
		
		while(running) {
			try {
				System.out.println("RUNNING");
				if(objectInputStream != null ) {
					System.out.println("108- objectInputStream != null");
					String messageString = (String)objectInputStream.readObject();
					String [] messagePart = messageString.split(ChatServer.properties.getProperty("messageSeparator"));
					processMessage(messagePart);
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Logger.getLogger(ChatServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Logger.getLogger(ChatServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			}
			
			
		}
		chatServer.remove(this);
		close();
	}
	
	
	public boolean writeMessage(String message) {
		if(!socket.isConnected()) {
			close();
			return false;
		}
		try {
			objectOutputStream.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Greska pri slanju: " + username);
			e.printStackTrace();
			System.out.println("CAUSE:" + e.getCause());
			Logger.getLogger(ChatServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			return false;
		}
		return true;
	}
	
	//TEXT#senderUsername#receiverUsername#messageContent
	//FILE#senderUsername#receiverUsername#fileName#fileContentInBytes
	public boolean processMessage(String[] messageParts) {
		if(messageParts.length < 4 && messageParts.length > 5) {
			return false;
		}
		
		switch (messageParts[0]) {
		case "TEXT":
		{
			System.out.println("TEXT");
			if(messageParts.length != 4) {
				System.out.println("Num of argument is not acceptable for text message");
				return false;
			}
			System.out.println("It is text message");
			String senderString = messageParts[1];
			String receiverString = messageParts[2];
			String contentString = messageParts[3];
			
			boolean isSent = chatServer.sendMessage("UNICAST",this.username ,receiverString,"MESSAGE_RESPONSE" + ChatServer.properties.getProperty("messageSeparator") + senderString + ChatServer.properties.getProperty("messageSeparator") + contentString);
			if(isSent) {
				System.out.println("Poruka je poslana");
			}else {
				System.out.println("Poruka nije poslana");
				writeMessage("ERROR, username of receiver is wrong");
			}
			
			break;
		}case "FILE":
		{
			System.out.println("FILE");
			if(messageParts.length != 5) {
				System.out.println("Num of argument is not acceptable for file message");
			}
			String senderString = messageParts[1];
			String receiverString = messageParts[2];
			String filenameString = messageParts[3];
			String fileContent = messageParts[4];
			System.out.println("It is file message");
			boolean isSent = chatServer.sendMessage("UNICAST",this.username, receiverString,"FILE_RESPONSE"+ ChatServer.properties.getProperty("messageSeparator") + senderString + ChatServer.properties.getProperty("messageSeparator") + filenameString + ChatServer.properties.getProperty("messageSeparator") + fileContent);
			if(isSent) {
				System.out.println("Poruka je poslana");
			}else {
				System.out.println("Poruka nije poslana");
				writeMessage("ERROR, username of receiver is wrong");
			}
			break;
		}case "LOGIN":
		{
			System.out.println("LOGIN");
			chatServer.sendMessage("BROADCAST", this.username, null, "LOGIN_RESPONSE" + ChatServer.properties.getProperty("messageSeparator") + username);
			break;
		}case "LOGOUT" :
		{
			System.out.println("User " + username + " se odjavljuje");
			System.out.println("LOGOUT_RESPONSE");
			chatServer.sendMessage("BROADCAST",this.username, null, "LOGOUT_RESPONSE" + ChatServer.properties.getProperty("messageSeparator") + username);
			chatServer.remove(this);
			running = false;
			
		}
		}
		
		
		return true;
	}
	
	
	public void close() {
		try {
			System.out.println("Da li je u close");
			if(objectOutputStream != null) {
				objectOutputStream.flush();
				objectOutputStream.close();
			}
			if(objectInputStream != null) {
				objectInputStream.close();
			}
			if(socket != null) {
				socket.close();
			}
		}catch (Exception e) {
			Logger.getLogger(ChatServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			e.printStackTrace();
		}
	}
}
