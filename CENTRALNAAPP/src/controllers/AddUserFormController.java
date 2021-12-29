package controllers;

import application.ClientUserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.User;

public class AddUserFormController{

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private Button addButton;

    @FXML
    void onClickAddButton(ActionEvent event) {
    	String username = usernameTextField.getText();
    	String password = passwordTextField.getText();
    	String idZSMDP = idTextField.getText();
    	
    	if(application.Main.clientUserService.addUser(username, password, idZSMDP)) {
    		makeAlertWindow("Korisnik je uspjesno dodan","INFORMATION","");
    		for(User user : application.Main.clientUserService.getUsers()) {
    			System.out.println(user);
    		}
    	}else {
    		System.out.println("Neuspjesno dodavanje usera");
    		makeAlertWindow("Dodavanje korisnika nije uspjesno, provjerite da li korisnicko ime vec postoji i da li postoji stanica","WARNING" , "Neuspjesno dodavanje korisnik");
    	}
    }
    
    public void makeAlertWindow(String text, String header, String title) {

        if ("WARNING".equals(title)) {
            makeAlert(new Alert(Alert.AlertType.WARNING), text, header, title);
        }else if("INFORMATION".equals(title)) {
            makeAlert(new Alert(Alert.AlertType.INFORMATION), text, header, title);
        }else {
            makeAlert(new Alert(Alert.AlertType.ERROR), text, header, title);
        }
    }

    private void makeAlert(Alert alert, String text, String header, String title) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }

}
