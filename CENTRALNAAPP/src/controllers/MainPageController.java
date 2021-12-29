package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Timetable;

public class MainPageController {
	
    @FXML
    private Button addUserButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button viewUsersButton;

    @FXML
    private Button createTimetableButton;

    @FXML
    private Button viewTimetableButton;

    @FXML
    private Button deleteLineBUtton;

    @FXML
    private Button viewReportButton;

    @FXML
    private Button downloadReportButton;

    @FXML
    void onClickAddUserButton(ActionEvent event) {
    	showForm(Main.properties.getProperty("addUserForm"));
    }

    @FXML
    void onClickCreateTimetableButton(ActionEvent event) {
    	showForm(Main.properties.getProperty("addLineForm"));
    	
    }

    @FXML
    void onClickDeleteLineButton(ActionEvent event) {
    	showForm(Main.properties.getProperty("deleteLineForm"));
    }

    @FXML
    void onClickDeleteUserButton(ActionEvent event) {
    	
    	showForm(Main.properties.getProperty("deleteUserForm"));
    }

    public static final String PATH_STRING = "resources";
    @FXML
    void onClickDownloadReportButton(ActionEvent event) {
    	showForm(Main.properties.getProperty("downloadReportsForm"));
    }

    @FXML
    void onClickViewReportButton(ActionEvent event) {
    	showForm(Main.properties.getProperty("viewReportsForm"));
    }

    @FXML
    void onClickViewTimetableButton(ActionEvent event) {
    	showForm(Main.properties.getProperty("viewAllLinesForm"));
    }

    @FXML
    void onClickViewUsersButton(ActionEvent event) {
    	showForm(Main.properties.getProperty("viewUsersForm"));
    }
    
    @FXML
    public void initialize() {
    	Main.mainPageController = this;
    }

    public void showForm(String fileName) {
    	try {
            Stage stage = new Stage();
            Pane myPane = FXMLLoader.load(getClass().getResource(fileName));
            Scene myScene = new Scene(myPane);
            stage.setScene(myScene);
            
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
    
    public void showNotification(String title,String content, String sender) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.properties.getProperty("readNotificationForm")));	
		Parent rootParent;
		try {
			rootParent = loader.load();
			ReadNotificationFormController readNotificationFormController = loader.getController();
			Platform.runLater(() -> {
				readNotificationFormController.updateTitle(title);
				readNotificationFormController.updateContent(content);
				readNotificationFormController.updateSender(sender);
				Scene scene = new Scene(rootParent);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
    
}
