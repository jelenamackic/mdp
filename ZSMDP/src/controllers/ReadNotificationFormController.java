package controllers;

import application.MulticastService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReadNotificationFormController {

    @FXML
    private TextArea contentTextArea;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField senderTextField;

    public void updateTitle(String title) {
    	titleTextField.setText(title);
    	titleTextField.setEditable(false);
    }
    public void updateContent(String content) {
    	contentTextArea.setText(content);
    	contentTextArea.setEditable(false);
    }
    public void updateSender(String sender) {
    	senderTextField.setText(sender);
    	senderTextField.setEditable(false);
    }
}
