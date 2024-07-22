/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 19/06/2024
* Ultima alteracao.: 28/06/2024
* Nome.............: ClientController
* Funcao...........: Controls the chat user interface
*************************************************************** */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GroupController implements Initializable {
    @FXML private TextField sendMessageTextField;
    @FXML private Label groupLabel;
    public static Label groupLabel_;
    @FXML private VBox chat;
    private static VBox chat_;

    /* ***************************************************************
    * Metodo: initialize
    * Funcao: Define methods that executes when the current window opens
    * Parametros: location&resources= Fxml arguments
    * Retorno: void
    *************************************************************** */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupLabel_ = groupLabel;
        chat.getStyleClass().add("message-chat");
        chat_ = chat;
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
    public static void addMessageFromMe(String message, String groupId) {
        Platform.runLater(() -> {
            if(groupId.equals(groupLabel_.getText().substring(groupLabel_.getText().length() - 1))) {
                Label newMessage = new Label(message);
                newMessage.getStyleClass().add("message-sent");
    
                HBox newMessageBox = new HBox();
                newMessageBox.getStyleClass().add("message-sent-box");
                newMessageBox.getChildren().add(newMessage);
                chat_.getChildren().add(newMessageBox);
            } // End if
        });
    } // End addMessageFromMe

    /* ***************************************************************
    * Metodo: addMessageFromOherUser
    * Funcao: Update ui with the message justified to left
    * Parametros: message= The string message, userName= Client that sent the message
    * Retorno: void
    *************************************************************** */
    public static void addMessageFromOtherUser(String message, String userName, String groupId) {
        Platform.runLater(() -> {
            if(groupId.equals(groupLabel_.getText().substring(groupLabel_.getText().length() - 1))) {
                Label newMessage = new Label(userName + ": " + message);
                newMessage.getStyleClass().add("message-received");

                HBox newMessageBox = new HBox();
                newMessageBox.getStyleClass().add("message-received-box");
                newMessageBox.getChildren().add(newMessage);
                chat_.getChildren().add(newMessageBox);
            } // End if
        });
    } // End addMessageFromOtherUser

    /* ***************************************************************
    * Metodo: clearMessages
    * Funcao: remove all messages from chat
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public static void clearMessages() {
        Platform.runLater(() -> {
            chat_.getChildren().clear();
        });
    } // End clearMessages
} // End class GroupController
