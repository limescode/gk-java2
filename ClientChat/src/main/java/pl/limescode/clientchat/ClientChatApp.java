package pl.limescode.clientchat;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.limescode.clientchat.controllers.AuthController;
import pl.limescode.clientchat.controllers.ClientController;

import java.io.IOException;

public class ClientChatApp extends Application {

    private Stage chatStage;
    private Stage authStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.chatStage = primaryStage;

        ClientController controller = createChatDialog(primaryStage);
        connectToServer(controller);
        createAuthDialog(primaryStage);

        controller.initializeMessageHandler();
    }

    private void createAuthDialog(Stage primaryStage) throws IOException {
        FXMLLoader authLoader = new FXMLLoader();
        authLoader.setLocation(ClientChatApp.class.getResource("authDialog.fxml"));
        AnchorPane authDialogPanel = authLoader.load();

        authStage = new Stage();
        authStage.initOwner(primaryStage);
        authStage.initModality(Modality.WINDOW_MODAL);

        authStage.setScene(new Scene(authDialogPanel));

        AuthController authController = authLoader.getController();
        authController.setClientChat(this);
        authController.initializeMessageHandler();

        authStage.showAndWait();
    }

    private ClientController createChatDialog(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(ClientChatApp.class.getResource("chat-template.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        this.chatStage.setTitle("Java FX Application");
        this.chatStage.setScene(scene);

        ClientController controller = fxmlLoader.getController();
        controller.userList.getItems().addAll("user1", "user2");

        primaryStage.show();

        return controller;
    }

    private void connectToServer(ClientController clientController) {
        boolean resultConnectedToServer = Network.getInstance().connect();
        if (!resultConnectedToServer) {
            String errorMessage = "???????????????????? ???????????????????? ?????????????? ????????????????????";
            System.err.println(errorMessage);
            showErrorDialog(errorMessage);
        }

        clientController.setApplication(this);

        chatStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Network.getInstance().close();
            }
        });
    }

    public void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("????????????");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getAuthStage() {
        return authStage;
    }

    public Stage getChatStage() {
        return chatStage;
    }
}