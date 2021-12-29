package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.ls.LSOutput;

import application.App;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Line;

public class RecordOfPassageFormController {

    @FXML
    private ListView<TextField> lineListView;

    public Stage helpStage = new Stage();
    
    public String lineID;
    
    public void setPassed(String time) {
    	App.clientLineRestService.makeStationPassed(lineID, App.activeUser.getIdZSMDP(), time);
    }
    
    @FXML
    public void initialize() {
    	ArrayList<Line> lines = App.clientLineRestService.getAllLinesForStation(App.activeUser.getIdZSMDP());
    	if(lines != null) {
    		for(Line line: lines) {
        		TextField textField = new TextField();
        		textField.setText(App.clientLineRestService.readALine(line.getLineID() + ""));
        		lineListView.getItems().add(textField);
        		textField.setEditable(false);
        		textField.setOnMouseClicked(e -> {
        			lineID = textField.getText().split(" ")[1];
        			FXMLLoader loader = new FXMLLoader(getClass().getResource(App.properties.getProperty("setTimeForm")));	
	    			Parent rootParent;
					try {
						rootParent = loader.load();
						SetTimeFormController setTimeFormController = loader.getController();
		    			Scene scene = new Scene(rootParent);
		    			helpStage.setScene(scene);
		    			helpStage.show();
		    			setTimeFormController.recordOfPassageFormController = this;
		    			
					} catch (IOException ioe) {
						// TODO Auto-generated catch block
						ioe.printStackTrace();
						Logger.getLogger(App.class.getName()).log(Level.WARNING, ioe.fillInStackTrace().toString());
					}
	    			
        			textField.setEditable(false);
        			
        			
        		});
        	}
    	}
    }
}
