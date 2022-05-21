package pl.limescode.lesson7.chat;

import pl.limescode.lesson7.chat.auth.AuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyServer {

    private AuthService authService;
    private final List<ClientHandler> clients = new ArrayList<>();

    private final Set<String> logins = new HashSet<>();

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server has been started");
            authService = new AuthService();
            while (true) {
                waitAndProcessClientConnection(serverSocket);
            }

        } catch (IOException e) {
            System.err.println("Failed to bind port " + port);
        }
    }

    private void waitAndProcessClientConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for new client connection");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client has been connected");
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

    public void broadcastMessage(String message, ClientHandler sender) throws IOException {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        logins.add(clientHandler.getLogin());
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        logins.remove((clientHandler.getLogin()));
        clients.remove(clientHandler);
    }

    public AuthService getAuthService() {
        return authService;
    }

    public Set<String> getLogins() {
        return logins;
    }

}
