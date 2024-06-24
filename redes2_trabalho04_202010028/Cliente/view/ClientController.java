package Cliente.view;

import java.net.URL;
import java.util.ResourceBundle;
import Cliente.Client;
import Cliente.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ClientController implements Initializable{
    // Fxml variables
    @FXML private Button connectServerButton;
    @FXML private Button group1Button;
    @FXML private Button group2Button;
    @FXML private Button group3Button;
    @FXML private Button group4Button;
    @FXML private Label groupLabel;
    @FXML private Button leaveGroup1;
    @FXML private Button leaveGroup2;
    @FXML private Button leaveGroup3;
    @FXML private Button leaveGroup4;
    @FXML private Button leaveGroup5;
    private static Button leaveGroup1_;
    private static Button leaveGroup2_;
    private static Button leaveGroup3_;
    private static Button leaveGroup4_;
    private static Button leaveGroup5_;
    @FXML private TextField clientNameTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        leaveGroup1_ = leaveGroup1;
        leaveGroup2_ = leaveGroup2;
        leaveGroup3_ = leaveGroup3;
        leaveGroup4_ = leaveGroup4;
        leaveGroup5_ = leaveGroup5;
    }

    @FXML
    void connectToServer(ActionEvent event) {
        String clientName = clientNameTextField.getText();

        if(clientName.equals("")) return;

        Client client = new Client(clientName);
        new Thread(client).start();
        Principal.changeSceneGroups();
    } // End connectToServer

    @FXML
    void enterGroup(ActionEvent event) {
        Client.sendMessage("1", String.valueOf(((Control) event.getSource()).getId().charAt(5)), "");
        GroupController.groupLabel_.setText("Grupo " + ((Control) event.getSource()).getId().charAt(5));
        selectLeaveButton(((Control) event.getSource()).getId().charAt(5) - '0').setVisible(true);
        Principal.changeSceneSelectedGroup();
    } // End enterGroup

    @FXML
    void leaveGroup(ActionEvent event) {
        Client.sendMessage("2", String.valueOf(((Control) event.getSource()).getId().charAt(10)), "");
        selectLeaveButton(((Control) event.getSource()).getId().charAt(10) - '0').setVisible(false);
    }


    public static Button selectLeaveButton(int id) {
        switch(id) {
            case 1: {
                return leaveGroup1_;
            }
            case 2: {
                return leaveGroup2_;
            }
            case 3: {
                return leaveGroup3_;
            }
            case 4: {
                return leaveGroup4_;
            }
            default: {
                return leaveGroup5_;
            }
        }
    }
} // End ClientController
