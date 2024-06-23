package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import Cliente.util.MessageObject;
import Servidor.models.*;
import Servidor.utils.Apdus;
import Servidor.view.MainController;

public class Server extends Thread {
    private static int port = 5000;
    private ServerSocket server;
    private Socket client;
    private MessageObject messageObject;
    private static final ArrayList<Socket> clients = new ArrayList<>();
    private static ArrayList<Group> groupChats = new ArrayList<>();

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
                clients.add(client);
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
                messageObject = (MessageObject) messageReceived.readObject();
                String receivedString = messageObject.getMessage();
                String[] stringElement = receivedString.split("\\*");
                int apduNumber = Integer.valueOf(stringElement[0]);
                String user = messageObject.getClient();
                int group = Integer.valueOf(stringElement[1]);

                if(Apdus.getApdu(apduNumber) == "JOIN") {
                    if(!groupExists(group)) {
                        Group groupToBeCreated = new Group(group);
                        groupChats.add(groupToBeCreated);
                    }

                    if(!userIsInGroup(group, user)) {
                        MainController.addLog(user + " joined the group " + group); 
                        addParticipantToGroup(group, client);
                    }
                } 

                if(Apdus.getApdu(apduNumber) == "LEAVE") {
                    MainController.addLog(user + "left the group " + group);
                }

                if(Apdus.getApdu(apduNumber) == "SEND") {
                    String message = stringElement[2];
                    MainController.addLog(user + " in group " + group + " >> " + message);
                }
                
                messageToSend.flush();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Boolean groupExists(int id) {
        for(Group group : groupChats) {
            if(group.getId() == id) {
                return true;
            }
        }

        return false;
    }

    public Group searchGroup(int id) {
        for(Group group : groupChats) {
            if(group.getId() == id) {
                return group;
            }
        }

        return null;
    }

    public void addParticipantToGroup(int groupId, Socket client) {
        for(Group group : groupChats) {
            if(group.getId() == groupId) {
                group.addParticipant(client);
            }
        }
    }

    public Boolean userIsInGroup(int id, String userIp) {
        for(Group group : groupChats) {
            if(group.getId() == id) {
                return group.participantExists(userIp);
            }
        }

        return false;
    }
}