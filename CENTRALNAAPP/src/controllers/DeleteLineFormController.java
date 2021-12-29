package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Line;

public class DeleteLineFormController {

    @FXML
    private ListView<TextField> lineListView;
    
    
    @FXML
    public void initialize() {
    	Line [] lines = Main.clientLineService.viewAllLines();
    	if(lines != null) {
    		for(Line line: lines) {
        		TextField textField = new TextField();
        		textField.setText(Main.clientLineService.writeLine(line));
        		lineListView.getItems().add(textField);
        		textField.setEditable(false);
        		textField.setOnMouseClicked(e -> {
        			Main.clientLineService.deleteLine(line.getLineID());
        			textField.setText("");
        			textField.setEditable(false);
        		});
        	}
    	}
    	
    }

}
