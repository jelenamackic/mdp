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
    
    public RecordOfPassageFormController recordOfPassageFormController;
    
    

    @FXML
    void onClickAddButton(ActionEvent event) {
    	if(timeTextField.getText() != null) {
    		recordOfPassageFormController.setPassed(timeTextField.getText());
    		recordOfPassageFormController.helpStage.close();
    	}
    	
    }

}
