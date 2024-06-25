/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 19/06/2024
* Ultima alteracao.: 28/06/2024
* Nome.............: ClientController
* Funcao...........: Controls the chat user interface
*************************************************************** */

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

    /* ***************************************************************
    * Metodo: initialize
    * Funcao: Define methods that executes when the current window opens
    * Parametros: location&resources= Fxml arguments
    * Retorno: void
    *************************************************************** */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupLabel_ = groupLabel;
    } // End initialize

    /* ***************************************************************
    * Metodo: backToGroupsPage
    * Funcao: Return the the groups list page
    * Parametros: event= A click event
    * Retorno: void
    *************************************************************** */
    @FXML
    void backToGroupsPage(ActionEvent event) {
        Principal.changeSceneGroups();
    } // End backToGroupsPage

    /* ***************************************************************
    * Metodo: sendMessage
    * Funcao: Send a message to server
    * Parametros: event= A click event
    * Retorno: void
    *************************************************************** */
    @FXML
    void sendMessage(ActionEvent event) {
        String message = sendMessageTextField.getText();
        sendMessageTextField.clear();

        // Verify if there is a message to be sent
        if(message != "") {
            Client.sendMessage("3", groupLabel.getText().substring(groupLabel.getText().length() - 1), message);
        } // End if
    } // End sendMessage

    /* ***************************************************************
    * Metodo: addMessageFromMe
    * Funcao: Update ui with the message justified to right
    * Parametros: message= The string message
    * Retorno: void
    *************************************************************** */
    public static void addMessageFromMe(String message) {
        System.out.println(message);
    } // End addMessageFromMe

    /* ***************************************************************
    * Metodo: addMessageFromOherUser
    * Funcao: Update ui with the message justified to left
    * Parametros: message= The string message, userName= Client that sent the message
    * Retorno: void
    *************************************************************** */
    public static void addMessageFromOtherUser(String message, String userName) {
        System.out.println(userName + " >> " + message);
    } // End addMessageFromOtherUser
} // End class GroupController
