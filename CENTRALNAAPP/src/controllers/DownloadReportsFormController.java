package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.ReportDescriptor;

public class DownloadReportsFormController {
	public static final String PATH_STRING = "resources";
    @FXML
    private ListView<TextField> reportsListView;
    
    
    @FXML
    public void initialize() {
    	System.out.println("Initialize");
    	try {
			ArrayList<ReportDescriptor> arrayList = Main.reportServiceInterface.getInormationAboutAllReports();
			System.out.println("Vratilo je listu");
			if(arrayList == null) {
				System.out.println("Nema izvjestaja");
			}else {
				System.out.println("else" + arrayList.size());
				for(ReportDescriptor reportDescriptor : arrayList) {
					System.out.println("ReportDescriptor");
					TextField textField = new TextField();
	        		textField.setText(reportDescriptor.getInformation());
	        		textField.setOnMouseClicked(e -> {
	        			download(reportDescriptor.getFileName());
	        		});
	        		reportsListView.getItems().add(textField);
	        		
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void download(String filename) {
    	File file = new File(PATH_STRING + File.separator +  filename);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			byte[] bytes = Main.reportServiceInterface.downloadReport(filename);
			FileOutputStream outputStream = new FileOutputStream(file);
			if(bytes == null) {
				System.out.println("Nema bajtova");
			}else {
				outputStream.write(bytes);
				outputStream.flush();
				outputStream.close();
			}
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }

}
