package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import org.w3c.dom.ls.LSOutput;

import controllers.MainPageController;
import javafx.application.Platform;
import model.User;

public class ChatClientThread extends Thread{
	private ObjectInputStream objectInputStream;
	private MainPageController mainPageController;
	private ArrayList<String> activeUsers = new ArrayList<String>();
	private ChatClient chatClient;
	public ChatClient getChatClient() {
		return chatClient;
	}


	public void setChatClient(ChatClient chatClient) {
		this.chatClient = chatClient;
	}


	public ChatClientThread(ObjectInputStream objectInputStream, MainPageController mainPageController,ChatClient chatClient) {
		this.objectInputStream = objectInputStream;
		this.mainPageController = mainPageController;
		this.chatClient = chatClient;
		// TODO Auto-generated constructor stub
	}
	
	
	public void run() {
		while(true) {
			try {
				// read the message form the input datastream
				if(!chatClient.getSocket().isClosed()) {
					String msg = (String) objectInputStream.readObject();
					// print the message
					String[] partsOfMessage = msg.split(App.properties.getProperty("messageSeparator"));
					
					//MESSAGE_RESPONSE#senderUsername#messageText
					//receive message
					if(partsOfMessage[0].equals("MESSAGE_RESPONSE")) {
						String senderUsername = partsOfMessage[1];
						String mess = partsOfMessage[2];
						
						//mainpageControllerInstance.updateTextInChat(senderUsername + ": " + mess + "\n");
						System.out.println(senderUsername + "888888: " + mess);
					Platform.runLater(() ->	mainPageController.receiveMessage(mess, senderUsername));
						
						
					}
					//receive file
					//"FILE_RESPONSE#" + senderString + "#" + filenameString + "#" + fileContent
					else if(partsOfMessage[0].equals("FILE_RESPONSE")) {
						String senderUsername = partsOfMessage[1];
						String fileName = partsOfMessage[2];
						byte[] decodedContent = Base64.getDecoder().decode(partsOfMessage[3].getBytes(StandardCharsets.UTF_8));
						
						String receiverUsername = App.activeUser.getUsername();
						System.out.println(senderUsername + ": " + "Poslao je fajl " + fileName + new String(decodedContent));;
						
						File directoryFile = new File("chatFiles" + File.separator + receiverUsername + File.separator + senderUsername);
						if(!directoryFile.exists()) {
							System.out.println("Nije dir moramo ga napraviti");
							directoryFile.mkdirs();
							System.out.println("DirectoryIsMade " + directoryFile.getAbsolutePath());
						}
						File receivedFile = new File(directoryFile.getAbsolutePath() + File.separator + fileName);
						if(!receivedFile.exists()) {
							receivedFile.createNewFile();
						}
						
						
						System.out.print("FAJL JEEEEE " + receivedFile + " a sadrzaj treba da bude: " + decodedContent.toString());
						
						try {
							//Files.write(receivedFile.toPath(), Base64.getDecoder().decode(fileContent.getBytes()));
							//Files.write(receivedFile.toPath(),fileContent.getBytes(StandardCharsets.UTF_8),StandardOpenOption.CREATE);
							FileOutputStream outputStream = new FileOutputStream(receivedFile);
							
								outputStream.write(decodedContent);
								outputStream.flush();
								outputStream.close();
								Platform.runLater(() ->	mainPageController.receiveMessage(receivedFile.getName(), senderUsername));
								
						}
						catch(Exception e) {
							Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
						}
						
					//	mainpageControllerInstance.updateTextInChat(senderUsername + ": Poslao je fajl " + fileName + "\n");
						
					}
					else if("LOGIN_RESPONSE".equals(partsOfMessage[0])) {
						System.out.println("*-*-*-*-*-*-*-*-novi korisnik se pojavio " + partsOfMessage[1]);	
						activeUsers.add(partsOfMessage[1]);
						//mainpageControllerInstance.updateTextInChat("Novi KORISNIK");
					}
					else if(partsOfMessage[0].equals("ONLINE_USERS")) {
						
						System.out.println("ONLINE users...");
						
					/*	for(int i = 1;i<partsOfMessage.length;i++) {
							if(!onlineUsers.contains(partsOfMessage[i]))
								onlineUsers.add(partsOfMessage[i]);
							System.out.println("Online je " + partsOfMessage[i]);
						} */
						//send on main form
						//mainpageControllerInstance.onlineUsers = onlineUsers;
						
					}
					else if(partsOfMessage[0].equals("LOGOUT_RESPONSE")) {
						System.out.println(App.activeUser.getUsername() + ": Odjavaljuje se korisnik: " + partsOfMessage[1]);
						String logoutUser = partsOfMessage[1];
						activeUsers.remove(logoutUser);
					/*	System.out.println("-----------------LOGOUT " + onlineUsers.size());
						onlineUsers.remove(logoutUser);
						System.out.println("-----------------LOGOUT AFTER " + onlineUsers.size()); */
					}
					
					
					
					
					System.out.print("> ");
					
					Thread.sleep(1000);				}
				}
				
			catch(IOException e) {
				//display(notif + "Server has closed the connection: " + e + notif);
				e.printStackTrace();
				Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
				break;
			}
			catch(Exception e2) {
				e2.printStackTrace();
				Logger.getLogger(App.class.getName()).log(Level.WARNING, e2.fillInStackTrace().toString());
			}
		}
	}
}
