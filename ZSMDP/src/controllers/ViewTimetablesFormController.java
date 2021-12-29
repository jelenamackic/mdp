package controllers;

import java.util.ArrayList;

import application.App;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Line;

public class ViewTimetablesFormController {

    @FXML
    private ListView<String> timetableListView;
    
    
    @FXML
    public void initialize() {

    	ArrayList<Line> lines = App.clientLineRestService.getAllLinesForStation(App.activeUser.getIdZSMDP());
    	if(lines != null) {
    		for(Line line: lines) {
    			timetableListView.getItems().add(App.clientLineRestService.readALine(line.getLineID() + ""));
    		}
    		
    		
    	}
    	
    	
    	
    	
    }

}
