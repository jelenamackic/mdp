package controllers;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.w3c.dom.ls.LSOutput;

import application.App;
import application.ChatClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;
import model.Zsmdp;

public class MainPageController {

    @FXML
    private Button logoutButton;

    @FXML
    private Button viewButton;

    @FXML
    private Button evidentButton;

    @FXML
    private Button sendNotificationButton;

    @FXML
    private Button sendReportButton;

    @FXML
    private ComboBox<String> chatZsmdpComboBox;

    @FXML
    private ComboBox<String> chatActiveUsersComboBox;
    

    
    @FXML
    private TextArea chatTextArea;

    @FXML
    private TextField chatTextField;

    @FXML
    private Button insertFileButton;

    @FXML
    private Button sendMessageButton;

    public HashMap<String, TextArea> chatHashMap = new HashMap<String, TextArea>();
    
    
    @FXML
    void onChoseActiveUser(ActionEvent event) {
    	
    	String receiverString = chatActiveUsersComboBox.getSelectionModel().getSelectedItem();
    	if(receiverString != null) {
    		System.out.println("Otvorice se chat sa tim userom");
    		openChatWithUser(receiverString);
    	}
    	
    	
    }

    @FXML
    void onChoseZSMDP(ActionEvent event) {
    	if(chatZsmdpComboBox.getSelectionModel().getSelectedItem() != null) {
    		User[] users = App.clientUserService.getActiveUsersForStation(chatZsmdpComboBox.getSelectionModel().getSelectedItem());
			if(users != null ) {
				for(User user: users) {
					if(!user.getUsername().equals(App.activeUser.getUsername())) {
						System.out.println("Pronaslo je aktivnog usera");
						chatActiveUsersComboBox.getItems().add(user.getUsername());
					}
					
				}
			}
    	}
    }
    
    @FXML
    void onClickInsertFileButton(ActionEvent event) {
    	String receiverString = chatActiveUsersComboBox.getSelectionModel().getSelectedItem();
    	if(receiverString != null) {
    		Stage fileChooserStage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose resource file");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("FILES:", "*.txt"));
            fileChooser.setInitialDirectory(new File(App.properties.getProperty("CHAT_FILES")));
            File selectedFile = fileChooser.showOpenDialog(fileChooserStage);
            if(selectedFile != null) {
            	System.out.println("Izabran je fajl: " + selectedFile.getName());
            	///read file content
            	try {
    				byte [] fileContentString = Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath()));
    				System.out.println("FILE CONTENT: " + new String(fileContentString));
    				//FILE#senderUsername#receiverUsername#fileName#fileContentInBytes
    				chatHashMap.get(receiverString).appendText("me: " + selectedFile.getName() + "\n\n");
            		chatTextArea.setText(chatHashMap.get(receiverString).getText());
    				App.chatClientService.sendMessage("FILE"+ App.properties.getProperty("messageSeparator") + App.activeUser.getUsername() + App.properties.getProperty("messageSeparator") + receiverString + App.properties.getProperty("messageSeparator") + selectedFile.getName() + App.properties.getProperty("messageSeparator") + Base64.getEncoder().encodeToString(fileContentString));
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
    			}
            }
    	}
    	
    }
    
    @FXML
    void onClickSendMessageButton(ActionEvent event) {
    	String receiverString = chatActiveUsersComboBox.getSelectionModel().getSelectedItem();
    	if(receiverString != null) {
    		//open chat with this user
    		System.out.println("Izabrani korisnik za dopisivanje je: " + receiverString);
    		String contentString = chatTextField.getText();
        	if(contentString != null) {
        		System.out.println("Napisano je: " + contentString);
        		App.chatClientService.sendMessage("TEXT"+ App.properties.getProperty("messageSeparator") + App.activeUser.getUsername() + App.properties.getProperty("messageSeparator") + receiverString + App.properties.getProperty("messageSeparator") + contentString);
        		
        		chatHashMap.get(receiverString).appendText("me: " + contentString + "\n\n");
        		chatTextArea.setText(chatHashMap.get(receiverString).getText());
        		//openChatWithUser(receiverString, "me: " + contentString);
        	}else {
        		System.out.println("Nista nije napisano");
        	}
    	}
    	
    }
    
    public void receiveMessage(String messageContent, String receiver) {
    	chatActiveUsersComboBox.setValue(receiver);
    	System.out.println("Message is received " + messageContent + ".....");
    	openChatWithUser(receiver);
    	chatHashMap.get(receiver).appendText(receiver +  ": " + messageContent + "\n\n");
    	chatTextArea.setText(chatHashMap.get(receiver).getText());
    }
    
    public void openChatWithUser(String receiver) {
    	if(chatHashMap.containsKey(receiver)) {
    		System.out.println("Postoji cet sa korisnikom: " + receiver);
    		chatTextArea.appendText(chatHashMap.get(receiver).getText());
    	}else {
    		System.out.println("Ovo je prvi cet sa korisnikom: " + receiver);
    		chatHashMap.put(receiver, new TextArea());
    	}
    	//chatTextArea.setText(chatHashMap.get(receiver).getText());
    }
    
    


    @FXML
    void onClickEvidentButton(ActionEvent event) {
    	showStage(mainPageHelpStage, "recordOfPassageForm");
    }

    @FXML
    void onClickLogoutButton(ActionEvent event) {
    	App.clientUserService.logout(App.activeUser);
    	App.chatClientService.sendMessage("LOGOUT");
    	showStage(App.stage, "loginForm");
    }

    @FXML
    void onClickSendNotificationButton(ActionEvent event) {
    	showStage(mainPageHelpStage, "createNotificationForm");
    }

    @FXML
    void onClickSendReportButton(ActionEvent event) {
    	Stage fileChooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose resource file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("FILES:", "*.pdf"));
        fileChooser.setInitialDirectory(new File("resources"));
        File selectedFile = fileChooser.showOpenDialog(fileChooserStage);
        if(selectedFile != null) {
        	 try {
             	
     			FileInputStream fileInputStream = new FileInputStream(selectedFile);
     			long size = fileInputStream.getChannel().size();
     			byte bytes[] = new byte[(int) size];
     			fileInputStream.read(bytes);
     			App.reportServiceInterface.uploadReport(App.activeUser.getUsername(), App.activeUser.getIdZSMDP(), selectedFile.getName(), LocalTime.now(), size, bytes);
     			
     		} catch (FileNotFoundException e) {
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

    @FXML
    void onClickViewButton(ActionEvent event) {
    	showStage(mainPageHelpStage, "viewTimetableForm");
    }
    
    public Stage mainPageHelpStage = new Stage();
    
    public void showStage(Stage stage,String fileName) {
    	try {
            
            Pane myPane = FXMLLoader.load(getClass().getResource(App.properties.getProperty(fileName)));
            Scene myScene = new Scene(myPane);
            stage.setScene(myScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
        }
    }

    @FXML
    public void initialize() {
    	App.multicastService.mainPageController = this;
    	App.chatClientService = new ChatClient(App.properties.getProperty("CHAT_CLIENT_HOST"), Integer.parseInt(App.properties.getProperty("CHAT_CLIENT_PORT")),this);
    	App.chatClientService.start();
    	Zsmdp [] zsmdps = App.clientZsmdpService.getAllZsmdps();

    	if(zsmdps != null) {

    		for(Zsmdp zsmdp : zsmdps) {

    			chatZsmdpComboBox.getItems().add(zsmdp.getIdZSMDP());
    			
    		}
    	
    	}

    	
    }
    
    
    public void showNotification(String title,String content, String sender) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(App.properties.getProperty("readNotificationForm")));	
		Parent rootParent;
		try {
			rootParent = loader.load();
			ReadNotificationFormController readNotificationFormController = loader.getController();
			Platform.runLater(() -> {
				readNotificationFormController.updateTitle(title);
				readNotificationFormController.updateContent(content);
				readNotificationFormController.updateSender(sender);
				Scene scene = new Scene(rootParent);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
			});
		} catch (IOException e) {

			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		
    }
    
    
    
    
 
}
