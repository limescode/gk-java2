package pl.limescode.clientchat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.DateFormat;
import java.util.Date;

public class ClientController {

    @FXML
    public TextField messageField;
    @FXML
    public Button sendMessageButton;
    @FXML
    public TextArea messageTextArea;
    @FXML
    public ListView userList;

    public void appendMessageToChat(ActionEvent actionEvent) {
        if (!messageField.getText().isEmpty()) {
            messageTextArea.appendText(DateFormat.getDateTimeInstance().format(new Date()) + " ");
            messageTextArea.appendText(System.lineSeparator());
            if (!userList.getSelectionModel().isEmpty()) {
                String sender = userList.getSelectionModel().getSelectedItem().toString();
                messageTextArea.appendText(sender + ": ");
            }
            else {
                messageTextArea.appendText("me: ");
            }
            messageTextArea.appendText(messageField.getText().trim() + System.lineSeparator());
            requestFocuc();
            messageField.clear();
        }
    }

    private void requestFocuc() {
        Platform.runLater(() -> messageField.requestFocus());
    }
}