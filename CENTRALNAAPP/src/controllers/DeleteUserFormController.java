package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.User;

public class DeleteUserFormController {

    @FXML
    private ListView<TextField> userListView;


    @FXML
    public void initialize() {
    	User users [] = application.Main.clientUserService.getUsers();
    	for(User user : users) {
    		TextField textField = new TextField();
    		textField.setText(user.getUsername());
    		userListView.getItems().add(textField);
    		textField.setOnMouseClicked(e -> {
    			if(application.Main.clientUserService.deleteUser(textField.getText())) {
    				textField.setText("");
        			textField.setEditable(false);
    			}else {
    				//show alert
    			}
    			
    		});
    	}
    }
    
   
    
}
