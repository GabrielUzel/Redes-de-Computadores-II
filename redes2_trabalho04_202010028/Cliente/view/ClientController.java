package Cliente.view;

import Cliente.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ClientController {
    @FXML
    void startServer(ActionEvent event) {
        Client client = new Client();
        client.startClient();
    } // End startServer


    @FXML
    void connectToServer(ActionEvent event) {
        Client client = new Client();
        new Thread(client::startClient).start();
    } // End connectToServer
} // End ClientController
