package controllers;



import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.plaf.FontUIResource;

import org.w3c.dom.ls.LSOutput;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.ReportDescriptor;

public class ViewReportsFormController {

    @FXML
    private ListView<TextField> reportsListView;
    
    
    @FXML
    public void initialize() {
    	
    	File dir = new File("resources");
		File[] directoryListing = dir.listFiles();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if (directoryListing != null) {
			for(File file: directoryListing) {
				TextField textField = new TextField();
        		textField.setText(file.getName());
        		textField.setOnMouseClicked(e -> {
        			readReport(file.getName());
        		});
        		reportsListView.getItems().add(textField);
			}
		}
    	
    }
    
    
    public void readReport(String filename) {
    	//download(filename);
		if(Desktop.isDesktopSupported()) {
			System.out.println("is supported");
			try {
				File file = new File("resources" + File.separator + filename);
				System.out.println("FILE ABSOLUTE: " + file.getAbsolutePath());
				Desktop.getDesktop().open(file);
			
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
    }
  
   

}