package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SetTimeFormController {

    @FXML
    private TextField timeTextField;

    @FXML
    private Button addButton;

    public AddLineFormController addLineFormController;
    
    public String stringFromAddLineController;
    public String stationIdString;
    public String timeString;
    
    @FXML
    void onClickAddButton(ActionEvent event) {
    	if(timeTextField.getText() != null) {
    		addLineFormController.addStringMethod(stationIdString, timeTextField.getText());
    		System.out.println("Doslo je dovde");
    	}
    }
    
   
    
}
