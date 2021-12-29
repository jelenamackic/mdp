package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.imgscalr.Main;

import application.App;
import application.ChatClient;
import application.InterruptableUDPThread;
import application.MulticastService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void onClickLoginButton(ActionEvent event) {

    	if(usernameField.getText() != null && passwordField.getText() != null) {
    		User user = App.clientUserService.login(usernameField.getText(), passwordField.getText());
    		if(user != null) {
    			App.multicastService = new MulticastService();
    			
    			App.activeUser = user;
    			titleString = user.getIdZSMDP();
        		App.stage.close();
        		
        		showMainPage("mainStageForm");
        	
	    	}else {
	    		System.out.println("Nisu ispravni podaci");
	    		
	    	}
    	}
    		
    	
    }
    
    public String titleString;
    public void showMainPage(String fileName) {
    	try {
            App.stage = new Stage();
            Pane myPane = FXMLLoader.load(getClass().getResource(App.properties.getProperty(fileName)));
            Scene myScene = new Scene(myPane);
            App.stage.setScene(myScene);
            App.stage.setTitle(titleString);
            App.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
        }
    }

}
