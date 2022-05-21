package pl.limescode.clientchat.controllers;

import pl.limescode.clientchat.ClientChat;
import pl.limescode.clientchat.dialogs.Dialogs;
import pl.limescode.clientchat.model.Network;
import pl.limescode.clientchat.model.ReadMessageListener;
import pl.limescode.command.Command;
import pl.limescode.command.CommandType;
import pl.limescode.command.commands.commands.AuthOkCommandData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AuthController {

    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button authButton;

    public ReadMessageListener readMessageListener;

    @FXML
    public void executeAuth() {
        String login = loginField.getText();
        String password = passwordField.getText();

        if (login == null || password == null || login.isBlank() || login.isBlank()) {
            Dialogs.AuthError.EMPTY_CREDENTIALS.show();
            return;
        }

        if (!isConnectedToServer()) {
            Dialogs.NetworkError.SERVER_CONNECT.show();
        }

        try {
            Network.getInstance().sendAuthMessage(login, password);
        } catch (IOException e) {
            Dialogs.NetworkError.SEND_MESSAGE.show();
            e.printStackTrace();
        }
    }

    public void initializeMessageHandler() {
        readMessageListener = getNetwork().addReadMessageListener(new ReadMessageListener() {
            @Override
            public void processReceivedCommand(Command command) {
                if (command.getType() == CommandType.AUTH_OK) {
                    AuthOkCommandData data = (AuthOkCommandData) command.getData();
                    String userName = data.getUserName();
                    Platform.runLater(() -> {
                        ClientChat.getInstance().switchToMainChatWindow(userName);
                    });
                } else {
                    Platform.runLater(() -> {
                        Dialogs.AuthError.INVALID_CREDENTIALS.show();
                    });
                }
            }
        });
    }

    public boolean isConnectedToServer() {
        Network network = getNetwork();
        return network.isConnected() || network.connect();
    }

    private Network getNetwork() {
        return Network.getInstance();
    }

    public void close() {
        getNetwork().removeReadMessageListener(readMessageListener);
    }
}
