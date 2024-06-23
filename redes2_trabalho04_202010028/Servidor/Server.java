/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 19/06/2024
* Ultima alteracao.: //
* Nome.............: Server
* Funcao...........: Receive and send information
*************************************************************** */
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
    private MessageObject messageObject; // Object that will contain received messages
    private static final ArrayList<Socket> clients = new ArrayList<>();
    private static ArrayList<Group> groupChats = new ArrayList<>();

    // Constructors
    public Server(Socket client) {
        this.client = client;
    }
    public Server() {

    }

    /* ***************************************************************
    * Metodo: startServer
    * Funcao: Open the server and listen to connections
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void startServer() {        
        try {
            server = new ServerSocket(port, 50, InetAddress.getByName("127.0.0.1"));
            MainController.addLog("Server started on: " + server.getLocalSocketAddress());

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

                String user = messageObject.getClient();
                int apduNumber = Integer.valueOf(stringElement[0]);
                int groupId = Integer.valueOf(stringElement[1]);

                // Verify which apdu was called
                if(Apdus.getApdu(apduNumber) == "JOIN") {
                    // Verify if, when a user enters a group, that group doesnt exists in DB
                    if(searchGroup(groupId) == null) {
                        // If doesnt exists, create a new group
                        Group groupToBeCreated = new Group(groupId);
                        groupChats.add(groupToBeCreated);
                    } // End if

                    // Verify if the user is participating in the group they entered
                    if(!searchGroup(groupId).participantExists(user)) {
                        // If isnt participating, add user
                        MainController.addLog(user + " joined the group " + groupId); 
                        searchGroup(groupId).addParticipant(client);
                    }
                } // End if

                if(Apdus.getApdu(apduNumber) == "LEAVE") {
                    MainController.addLog(user + "left the group " + groupId);
                } // End if

                if(Apdus.getApdu(apduNumber) == "SEND") {
                    String message = stringElement[2];
                    MainController.addLog(user + " in group " + groupId + " >> " + message);
                } // End if
                
                messageToSend.flush(); // Clear the object
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
    public Group searchGroup(int id) {
        // Iterate over group DB
        for(Group group : groupChats) {
            if(group.getId() == id) {
                // Found the group
                return group;
            } // End if
        } // End for

        return null;
    } // End searchGroup
} // End class Server