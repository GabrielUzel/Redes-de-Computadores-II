/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 19/06/2024
* Ultima alteracao.: 28/06/2024
* Nome.............: Client
* Funcao...........: Receive and send information
*************************************************************** */

package Cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Cliente.util.MessageObject;
import Cliente.view.GroupController;

public class Client extends Thread {
    private static int port = 7327;
    private String clientName;
    private Socket client;
    private static String message = null;
    private static String groupId = null;
    private MessageObject messageObject;

    // Constructors
    public Client(Socket client) {
        this.client = client;
    }
    public Client(String clientName) {
        this.clientName = clientName;
    }

    /* ***************************************************************
    * Metodo: run
    * Funcao: Thread start
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        try {
            client = new Socket("0.0.0.0", port);
            ObjectOutputStream sendMessage = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream receiveMessage = new ObjectInputStream(client.getInputStream());
            sendMessage.flush();

            // Start a new Thread to listen messages
            Thread listenMessages = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            MessageObject messageObject = (MessageObject) receiveMessage.readObject();
                            String senderIp = messageObject.getClientIp();
                            String receivedString = messageObject.getMessage();
                            String senderName = messageObject.getClientName();
                            String groupId = messageObject.getGroupId();

                            // Verify if the message received was sent by the current user or other user
                            if(senderIp.equals("/" + client.getLocalAddress().getHostAddress())) {
                                GroupController.addMessageFromMe(receivedString, groupId);
                            } else {
                                GroupController.addMessageFromOtherUser(receivedString, senderName, groupId); // Update ui
                            } // Enf if/else
                        } catch(IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } // End try/catch
                    } // End while
                } // End run
            });
            listenMessages.start(); // Start thread

            while(true) {
                Thread.sleep(1000);
                if(message != null) { // If true, user sent a new message
                    if(message.charAt(0) == '1') GroupController.clearMessages(); // Clear messages as the user enters a group

                    messageObject = new MessageObject(message, String.valueOf(client.getLocalAddress().getHostAddress()), clientName, groupId);
                    sendMessage.writeObject(messageObject); // Send message to server
                    sendMessage.flush();
                    message = null;
                }
            } // End while
        } catch(IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // End try/catch
    } // End run

    /* ***************************************************************
    * Metodo: sendMessage
    * Funcao: Organize the string that will be sent to server
    * Parametros: apdu= What apdu will be used, group= The destination, messageToSend= The message
    * Retorno: void
    *************************************************************** */
    public static void sendMessage(String apdu, String group, String messageToSend) {     
        // Update groupdId static variable
        groupId = group;

        // If the apdu is a join or leave, the message wont be needed   
        if(messageToSend == "") {
            message = apdu + "*" + group;
            return;
        } // End if

        message = apdu + "*" + group + "*" + messageToSend;
    } // End sendMessage
} // End Client
