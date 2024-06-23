package Cliente.view;

import Cliente.Client;
import Cliente.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;

public class ClientController {
    // Fxml variables
    @FXML private Button connectServerButton;
    @FXML private Button group1Button;
    @FXML private Button group2Button;
    @FXML private Button group3Button;
    @FXML private Button group4Button;
    @FXML private Label groupLabel;

    @FXML
    void connectToServer(ActionEvent event) {
        Client client = new Client();
        new Thread(client).start();
        Principal.changeSceneGroups();
    } // End connectToServer

    @FXML
    void enterGroup(ActionEvent event) {
        Client.sendMessage("1", String.valueOf(((Control) event.getSource()).getId().charAt(5)), "");
        GroupController.groupLabel_.setText("Grupo " + ((Control) event.getSource()).getId().charAt(5));
        Principal.changeSceneSelectedGroup();
    }
} // End ClientController
