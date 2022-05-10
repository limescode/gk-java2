package pl.limescode.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final String host;
    private final int port;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) throws IOException {
        Client echoClient = new Client("127.0.0.1", 8081);
        echoClient.openConnection();
    }

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void openConnection() throws IOException {
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Client connected to " + host + ":" + port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            //received from server
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String strFromServer = in.readUTF();
                            if (strFromServer.equalsIgnoreCase("/end")) {
                                System.out.println("Server disconnected");
                                System.exit(0);
                            }
                            System.out.println("From server: " + strFromServer);
                        }
                    } catch (IOException e) {
                        System.err.println("Connection is down");
                        System.exit(0);
                    }
                }
            }).start();

            //sent to server
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String strToServer = scanner.nextLine();
                out.writeUTF(strToServer);
                if (strToServer.equalsIgnoreCase("/end")) {
                    System.out.println("Disconnected from server");
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
