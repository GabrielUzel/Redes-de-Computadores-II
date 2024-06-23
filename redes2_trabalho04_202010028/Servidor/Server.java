package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import Servidor.models.*;
import Servidor.utils.Apdus;
import Servidor.view.MainController;

public class Server extends Thread {
    private static int port = 5000;
    private ServerSocket server;
    private Socket client;
    String receivedString;
    private static ArrayList<Group> groupChats = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();

    public Server(Socket client) {
        this.client = client;
    }

    public Server() {

    }

    public void startServer() {        
        try {
            server = new ServerSocket(port, 50, InetAddress.getByName("127.0.0.1"));
            MainController.addLog("Server started on: " + server.getLocalSocketAddress());

            while(true) {
                Socket client = server.accept();
                MainController.addLog("Cliente conectado do IP " + client.getRemoteSocketAddress().toString());
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
                System.out.println(receivedString);
                int apduNumber = receivedString.charAt(0) - '0';
                

                if(Apdus.getApdu(apduNumber) == "JOIN") {
                    System.out.println("JOIN nessa porra");
                } 

                if(Apdus.getApdu(apduNumber) == "LEAVE") {
                    System.out.println("LEAVE nessa porra");
                }

                if(Apdus.getApdu(apduNumber) == "SEND") {
                    MainController.addLog("");
                    System.out.println("SEND nessa porra");
                }
                
                messageToSend.flush();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addGroup(int groupNumber) {
        groupChats.add(new Group(groupNumber));
    }
}