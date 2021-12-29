package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.User;

public class ViewUsersFormController {

    @FXML
    private ListView<TextField> viewUsersListView;
    
    
    @FXML
    public void initialize() {
    	User users [] = application.Main.clientUserService.getUsers();
    	for(User user : users) {
    		TextField textField = new TextField();
    		textField.setText(user.getUsername());
    		viewUsersListView.getItems().add(textField);
    		System.out.println("USERRRRRR " + user);
    		textField.setEditable(false);
    	}
    }

}