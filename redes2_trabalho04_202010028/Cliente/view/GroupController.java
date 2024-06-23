package Cliente.view;

import java.net.URL;
import java.util.ResourceBundle;

import Cliente.Client;
import Cliente.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GroupController implements Initializable {
    @FXML private TextField sendMessageTextField;
    @FXML private Label groupLabel;
    public static Label groupLabel_;

    @FXML
    void backToGroupsPage(ActionEvent event) {
        Principal.changeSceneGroups();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupLabel_ = groupLabel;
    }

    @FXML
    void sendMessage(ActionEvent event) {
        String message = sendMessageTextField.getText();
        sendMessageTextField.clear();

        if(message != "") {
            Client.sendMessage("3", "user", groupLabel.getText().substring(groupLabel.getText().length() - 1), message);
        }
    }
}
