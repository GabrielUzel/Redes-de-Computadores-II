package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import Server.models.Group;
import Server.utils.Apdus;

public class Server extends Thread {
    private static int port = 5000;
    private ServerSocket server;
    private Socket client;
    Scanner readTerminal = new Scanner(System.in);
    String receivedString;
    String apdu;
    String user;
    String group;
    String message;

    private static ArrayList<Group> groupChats = new ArrayList<>();

    public Server(Socket client) {
        this.client = client;
    }

    public Server() {

    }

    public void startServer() {        
        // área de testes
            // int teste = 2;
            // System.out.println(Apdus.getApdu(teste));
        // área de testes
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
                receivedString = (String) messageReceived.readObject();
                int apduNumber = receivedString.charAt(0) - '0';

                if(Apdus.getApdu(apduNumber) == "JOIN") {

                } 

                if(Apdus.getApdu(apduNumber) == "LEAVE") {
                    
                }

                if(Apdus.getApdu(apduNumber) == "SEND") {
                    
                }
                
                messageToSend.flush();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}