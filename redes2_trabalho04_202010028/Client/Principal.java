import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner readTerminal = new Scanner(System.in);

        Client client = new Client();
        client.startClient();
    }
}
