package Cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Cliente.util.MessageObject;
import Cliente.view.GroupController;

public class Client extends Thread {
    private String clientName;
    private static int port = 5000;
    private Socket client;
    private static String message = null;
    private MessageObject messageObject;

    // Constructors
    public Client(Socket client) {
        this.client = client;
    }
    public Client(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public void run() {
        try {
            client = new Socket("127.0.0.1", port);
            ObjectOutputStream sendMessage = new ObjectOutputStream(client.getOutputStream());
            sendMessage.flush();

            ObjectInputStream receiveMessage = new ObjectInputStream(client.getInputStream());

            Thread listenMessages = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            MessageObject messageObject = (MessageObject) receiveMessage.readObject();
                            String receivedString = messageObject.getMessage();
                            String clientName = messageObject.getClientName();
                            GroupController.addMessageFromOtherUser(receivedString, clientName);
                        } catch(IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            listenMessages.start();

            while(true) {
                Thread.sleep(1000);
                if(message != null) {
                    messageObject = new MessageObject(message, String.valueOf(client.getInetAddress()), clientName);
                    sendMessage.writeObject(messageObject);
                    sendMessage.flush();
                    message = null;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } // End run

    public static void sendMessage(String apdu, String group, String messageToSend) {        
        if(messageToSend == "") {
            message = apdu + "*" + group;
            return;
        }

        message = apdu + "*" + group + "*" + messageToSend;
    } // End sendMessage
} // End Client
