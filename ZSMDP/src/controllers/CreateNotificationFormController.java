package controllers;

import java.rmi.RemoteException;
import java.time.LocalTime;

import application.App;
import application.MulticastService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateNotificationFormController {

    @FXML
    private TextField fileNameField;

    @FXML
    private TextArea txtFileContent;

    @FXML
    private Button saveButton;

    @FXML
    void onClickSaveButton(ActionEvent event) {
    	String titleString = fileNameField.getText();
    	byte[] text = txtFileContent.getText().getBytes();
    	if(fileNameField.getText() != null && txtFileContent.getText() != null) {
        	App.multicastService.sendNotification(App.activeUser.getUsername(),titleString, txtFileContent.getText());
    	}

    }
    
    MainPageController controller;
    
    
    

}
