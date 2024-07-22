/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 19/06/2024
* Ultima alteracao.: 28/06/2024
* Nome.............: Server
* Funcao...........: Receive and send information
*************************************************************** */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import Cliente.util.MessageObject;
import models.*;
import utils.*;

public class Server extends Thread {
    // Server informations
    private static int port = 7327;
    private ServerSocket server;
    private Socket client;

    // Message information
    private MessageObject messageObject; // Object that will contain received messages

    // Objects to store some information
    private static final ArrayList<Socket> clients = new ArrayList<>();
    private static ArrayList<Group> groupChats = new ArrayList<>();

    // Constructors
    public Server(Socket client) {
        this.client = client;
    }
    public Server() {

    } // End constructors

    /* ***************************************************************
    * Metodo: startServer
    * Funcao: Open the server and listen to connections
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void startServer() {        
        try {
            server = new ServerSocket(port);            
            MainController.addLog("Server started:");

            while(true) {
                Socket client = server.accept();
                clients.add(client); // Add the client connection to DB
                MainController.addLog("Cliente conectado do IP " + client.getRemoteSocketAddress().toString());

                // Open a newThread for this client
                Thread newThread = new Server(client);
                newThread.start();
            } // End while
        } catch(IOException e) {
            e.printStackTrace();
        } // End try/catch
    } // End startServer

    /* ***************************************************************
    * Metodo: run
    * Funcao: Thread start
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        try {
            // New objects to receive and send data
            ObjectInputStream messageReceived = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream messageToSend = new ObjectOutputStream(client.getOutputStream());
            messageToSend.flush();

            while(true) {
                // Receive a message and divide it into variables
                messageObject = (MessageObject) messageReceived.readObject();
                String receivedString = messageObject.getMessage();
                String[] stringElement = receivedString.split("\\*");

                String clientIp = "/" + messageObject.getClientIp();
                String clientName = messageObject.getClientName();
                int apduNumber = Integer.valueOf(stringElement[0]);
                String groupId = stringElement[1];

                // Verify which apdu was called
                if(Apdus.getApdu(apduNumber) == "JOIN") {
                    // Verify if, when a user enters a group, that group doesnt exists in DB
                    if(searchGroup(groupId) == null) {
                        // If doesnt exists, create a new group
                        Group groupToBeCreated = new Group(Integer.valueOf(groupId));
                        groupChats.add(groupToBeCreated);
                    } // End if

                    // Verify if the user is participating in the group they entered
                    if(!searchGroup(groupId).participantExists(clientIp)) {
                        // If isnt participating, add user
                        MainController.addLog(clientIp + ": joined the group " + groupId); 
                        searchGroup(groupId).addParticipant(searchClientByIp(clientIp));
                    } // End if
                } // End if

                if(Apdus.getApdu(apduNumber) == "LEAVE") {
                    searchGroup(groupId).removeParticipant(searchClientByIp(clientIp));
                    MainController.addLog(clientIp + ": left the group " + groupId);
                } // End if

                if(Apdus.getApdu(apduNumber) == "SEND") {
                    String message = stringElement[2];
                    MessageObject messageObject = new MessageObject(message, clientIp, clientName, groupId);

                    for(Socket participant : searchGroup(groupId).getParticipants()) {
                        // Send the message for every participant in group
                        MyObjectOutputStream messageSender = new MyObjectOutputStream(participant.getOutputStream());
                        messageSender.writeObject(messageObject);
                        messageSender.flush();
                        messageSender.reset();
                    } // End for

                    MainController.addLog(clientIp + ": sent message to group " + groupId + " >> " + message);
                } // End if
            } // End while
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } // End try/catch
    } // End run

    /* ***************************************************************
    * Metodo: searchGroup
    * Funcao: Given an id, return a group in the DB
    * Parametros: id= The desired group id
    * Retorno: A group class
    *************************************************************** */
    public static Group searchGroup(String id) {
        // Iterate over group DB
        for(Group group : groupChats) {
            if(group.getId() == Integer.valueOf(id)) {
                // Found the group
                return group;
            } // End if
        } // End for

        return null;
    } // End searchGroup

    /* ***************************************************************
    * Metodo: searchClientByIp
    * Funcao: Given an ip, return a client socket in the DB
    * Parametros: ip= The desired client id
    * Retorno: A socket class
    *************************************************************** */
    public Socket searchClientByIp(String ip) {
        // Iterate over client DB
        for(Socket client : clients) {
            if(ip.equals(String.valueOf(client.getInetAddress()))) {
                // Found the client
                return client;
            } // End if
        } // End for

        return null;
    } // End searchClientByIp
} // End class Server