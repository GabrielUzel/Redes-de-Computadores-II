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
    public static void addMessageFromMe(String message, String groupId) {
        System.out.println(message + " >> Group: " + groupId);
    } // End addMessageFromMe

    /* ***************************************************************
    * Metodo: addMessageFromOherUser
    * Funcao: Update ui with the message justified to left
    * Parametros: message= The string message, userName= Client that sent the message
    * Retorno: void
    *************************************************************** */
    public static void addMessageFromOtherUser(String message, String userName, String groupId) {
        System.out.println(userName + " >> " + message + " >> Group: " + groupId);
    } // End addMessageFromOtherUser

//     public void sendMessage(){
//     String messageToSend = writeMessageTextField.getText();
//     writer.println(username+": " + messageToSend);
//     if (!messageToSend.isEmpty()){
//       HBox hBox = new HBox();
//       hBox.setAlignment(Pos.CENTER_RIGHT);
//       hBox.setPadding(new Insets(5, 10, 5, 10));
//       Text text = new Text(messageToSend);
//       TextFlow textFlow = new TextFlow(text);
//       textFlow.setStyle("-fx-color: rgb(239,242,255);"+
//                         "-fx-background-color: rgb(15,125,242);"+
//                         "-fx-background-radius: 20px;");
//       textFlow.setPadding(new Insets(5, 10, 5, 10));
//       text.setFill(Color.color(0.934,0.945,0.996));        
//       hBox.getChildren().add(textFlow);
//       vbox_messages.getChildren().add(hBox);
//       writeMessageTextField.clear();          
//     }
//   }

//   public static void addServerMessage(String messageFromServer, VBox vBox){
//     HBox hBox = new HBox();
//     hBox.setAlignment(Pos.CENTER_LEFT);
//     hBox.setPadding(new Insets(5, 5, 5, 10));
//     Text text = new Text(messageFromServer);
//     TextFlow textFlow = new TextFlow(text);
//     textFlow.setStyle("-fx-background-color: rgb(233,233,235);"+
//                       "-fx-background-radius: 20px;");
//     textFlow.setPadding(new Insets(5, 10, 5, 10));
//     hBox.getChildren().add(textFlow);
//     Platform.runLater(new Runnable(){
//       @Override
//       public void run(){
//         vBox.getChildren().add(hBox);
//       }
//     });
//   }
} // End class GroupController
