package pl.limescode.clientchat.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pl.limescode.clientchat.ClientChatApp;
import pl.limescode.clientchat.Network;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.function.Consumer;

public class ClientController {

    @FXML
    public TextField messageTextArea;

    @FXML
    public Button sendMessageButton;

    @FXML
    public TextArea chatTextArea;

    @FXML
    public ListView userList;

    private ClientChatApp application;

    public void sendMessage() {
        String message = messageTextArea.getText();

        if (message.isEmpty()) {
            messageTextArea.clear();
            return;
        }

        String sender = null;
        if (!userList.getSelectionModel().isEmpty()) {
            sender = userList.getSelectionModel().getSelectedItem().toString();
        }

        try {
            message = sender != null ? String.format(": ", sender, message) : message; // Server: message
            Network.getInstance().sendMessage(message);
        } catch (IOException e) {
            application.showErrorDialog("Ошибка передачи данных по сети");
        }

        appendMessageToChat("Я", message);
    }

    public void appendMessageToChat(String sender, String message) {
        chatTextArea.appendText(DateFormat.getInstance().format(new Date()));
        chatTextArea.appendText(System.lineSeparator());

        if (sender != null) {
            chatTextArea.appendText(sender + ":");
            chatTextArea.appendText(System.lineSeparator());
        }

        chatTextArea.appendText(message);
        chatTextArea.appendText(System.lineSeparator());
        chatTextArea.appendText(System.lineSeparator());
        messageTextArea.requestFocus();
        messageTextArea.clear();
    }

    public void initializeMessageHandler() {
        Network.getInstance().waitMessages(new Consumer<String>() {
            @Override
            public void accept(String message) {
                appendMessageToChat("Server", message);
            }
        });
    }

    public ClientChatApp getApplication() {
        return application;
    }

    public void setApplication(ClientChatApp application) {
        this.application = application;
    }
}