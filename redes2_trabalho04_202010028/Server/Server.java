package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static int port = 5000;
    private static int ip;
    private ServerSocket server;
    String message;
    Scanner readTerminal = new Scanner(System.in);


    public void startServer() {
        ObjectInputStream messageReceived;
        ObjectOutputStream messageToSend;
        
        try {
            server = new ServerSocket(port);
            Socket client = server.accept();
            System.out.println("Cliente conectado do IP " + client.getInetAddress().getHostAddress());

            messageReceived = new ObjectInputStream(client.getInputStream());
            messageToSend = new ObjectOutputStream(client.getOutputStream());
            messageToSend.flush();

            while(true) {
                message = (String) messageReceived.readObject();
                System.out.println("CLIENTE >> " + message);
                messageToSend.writeObject(message);
                messageToSend.flush();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}