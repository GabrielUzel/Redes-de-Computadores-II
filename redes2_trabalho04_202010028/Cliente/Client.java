package Cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    private static int port = 5000;
    private Socket client;

    private ObjectOutputStream sendMessage;
    String message;
    Scanner readTerminal = new Scanner(System.in);

    public Client(Socket client) {
        this.client = client;
    }

    public Client() {

    }

    public void startClient() {
        try {
            client = new Socket("127.0.0.1", port);
            sendMessage = new ObjectOutputStream(client.getOutputStream());
            sendMessage.flush();
            Thread newThread = new Client(client);
            newThread.start();
        } catch(IOException e) {
            e.printStackTrace();
        } 
    } // End startClient

    @Override
    public void run() {
        try {
            while(true) {
                message = sendMessage();
                sendMessage.writeObject(message);
                sendMessage.flush();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } 
    } // End run

    public String sendMessage() {
        return "teste";
    }
} // End Client
