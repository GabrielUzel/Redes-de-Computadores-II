package Cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread {
    private static int port = 5000;
    private Socket client;
    private ObjectOutputStream sendMessage;
    private static String message = null;

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
                    sendMessage.writeObject(message);
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

    public static void sendMessage(String apdu, String user, String group, String messageToSend) {
        if(messageToSend == "") {
            message = apdu + "*" + user + "*" + group + ";";
        }

        message = apdu + "*" + user + "*" + group + "*" + messageToSend + ";";
    }
} // End Client
