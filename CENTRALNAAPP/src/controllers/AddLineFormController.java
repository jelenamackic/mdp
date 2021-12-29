package controllers;

import java.awt.TextArea;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.w3c.dom.ls.LSOutput;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Timetable;
import model.User;
import model.Zsmdp;

public class AddLineFormController {

    @FXML
    private Button addLineButton;
    

    @FXML
    private ListView<TextField> stationsListView;

    @FXML
    private TextField lineIdTextField;

    @FXML
    void onClickAddLineButton(ActionEvent event) {
    	if(lineIdTextField.getText() != null) {
    		Iterator hmIterator = stationsHashMap.entrySet().iterator();
    		ArrayList<Timetable> arrayList = new ArrayList<Timetable>();
    		while(hmIterator.hasNext()) {
    			Map.Entry mapElement = (Map.Entry)hmIterator.next();
    			
    			arrayList.add(Main.clientLineService.geTimetable((String)mapElement.getKey(), (String)mapElement.getValue()));
    			
    			
    		}
    		
    		Main.clientLineService.addLine(Integer.parseInt(lineIdTextField.getText()),arrayList.toArray(new Timetable[arrayList.size()]), arrayList.size());
    	}
    	
    	
    }
    public Stage stage = new Stage();
    private HashMap<String,String> stationsHashMap  = new HashMap<String, String>();
    public void  addStringMethod(String stationID, String time) {
    	stationsHashMap.put(stationID, time);
    	System.out.println("Uspjesno dodano stajaliste u hm");
    }
    public String timeString;
    @FXML
    public void initialize() {
    	Zsmdp [] stationsZsmdps = Main.clientZsmdpService.getAllZsmdps();
    	if(stationsZsmdps != null) {
        	for(Zsmdp zsmdp : stationsZsmdps) {
    		TextField textField = new TextField();
    		textField.setText(zsmdp.getIdZSMDP() + "");
    		stationsListView.getItems().add(textField);
    		textField.setOnMouseClicked(e -> {
    			
    			textField.setEditable(false);
    			try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.properties.getProperty("setTimeForm")));	
	    			Parent rootParent = loader.load();
	    			SetTimeFormController setTimeFormController = loader.getController();
	    			Scene scene = new Scene(rootParent);
	    			stage.setScene(scene);
	    			stage.show();
	    			setTimeFormController.addLineFormController = this;
	    			setTimeFormController.stationIdString = textField.getText();
	    			addLineButton.setDisable(false);
    			} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			
    			stationsHashMap.put(textField.getText(), timeString);
    		});
    	} 
    	}
    }
}
