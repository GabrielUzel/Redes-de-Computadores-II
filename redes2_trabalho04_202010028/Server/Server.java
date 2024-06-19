package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import Server.models.Group;

public class Server extends Thread {
    private static int port = 5000;
    private ServerSocket server;
    private Socket client;
    String message;
    Scanner readTerminal = new Scanner(System.in);

    private ArrayList<Group> groupChats = new ArrayList<>();

    public Server(Socket client) {
        this.client = client;
    }

    public Server() {

    }

    public void startServer() {        
        try {
            server = new ServerSocket(port);

            while(true) {
                Socket client = server.accept();
                System.out.println("Cliente conectado do IP " + client.getInetAddress().getHostAddress());
                Thread newThread = new Server(client);
                newThread.start();

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            ObjectInputStream messageReceived = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream messageToSend = new ObjectOutputStream(client.getOutputStream());
            messageToSend.flush();

            while(true) {
                message = (String) messageReceived.readObject();
                System.out.println("CLIENTE" + client.getInetAddress().getHostAddress() + ">> " + message);
                messageToSend.flush();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}