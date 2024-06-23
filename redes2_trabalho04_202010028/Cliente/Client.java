package Cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Cliente.util.MessageObject;

public class Client extends Thread {
    private static int port = 5000;
    private Socket client;
    private ObjectOutputStream sendMessage;
    private static String message = null;
    private MessageObject messageObject;

    // Constructors
    public Client(Socket client) {
        this.client = client;
    }
    public Client() {
        
    }

    @Override
    public void run() {
        try {
            client = new Socket("127.0.0.1", port);
            sendMessage = new ObjectOutputStream(client.getOutputStream());
            sendMessage.flush();

            while(true) {
                Thread.sleep(1000);
                if(message != null) {
                    messageObject = new MessageObject(message, String.valueOf(client.getInetAddress()));
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
            message = apdu + "*" + group + ";";
        }

        message = apdu + "*" + group + "*" + messageToSend + ";";
    } // End sendMessage
} // End Client
