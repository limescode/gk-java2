package pl.limescode.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private final int port;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) throws IOException {
        Server echoServer = new Server(8081);
        echoServer.run();
    }

    public Server(int port) {
        this.port = port;
    }

    public void run() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected to the server");
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            //received from client
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String strFromClient = in.readUTF();
                            if (strFromClient.equalsIgnoreCase("/end")) {
                                System.out.println("Client disconnected");
                                System.exit(0);
                            }
                            System.out.println("From client: " + strFromClient);
                        }
                    } catch (IOException e) {
                        System.err.println("Connection is down");
                        System.exit(0);
                    }
                }
            }).start();

            //sent to client
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String strToClient = scanner.nextLine();
                out.writeUTF(strToClient);
                if (strToClient.equalsIgnoreCase("/end")) {
                    break;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            in.close();
            out.close();
        }
    }
}
