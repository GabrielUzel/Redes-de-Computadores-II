import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static int port = 5000;
    private Socket client;

    private ObjectOutputStream sendMessage;
    String message;
    Scanner readTerminal = new Scanner(System.in);

    public void startClient() {
        try {
            client = new Socket("127.0.0.1", port);
            sendMessage = new ObjectOutputStream(client.getOutputStream());
            sendMessage.flush();

            while(true) {
                System.out.print("..: ");
                message = readTerminal.nextLine();
                sendMessage.writeObject(message);
                sendMessage.flush();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } 
    }
}
