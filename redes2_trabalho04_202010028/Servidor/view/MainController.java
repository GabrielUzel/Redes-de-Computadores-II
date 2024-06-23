/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 19/06/2024
* Ultima alteracao.: //
* Nome.............: MainController
* Funcao...........: Start server
*************************************************************** */
package Servidor.view;

import java.net.URL;
import java.util.ResourceBundle;
import Servidor.Server;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class MainController implements Initializable {
    // Fxml variables
    @FXML private TextArea serverTextArea;
    private static TextArea serverTextArea_; // Need a static textArea for other classes add server logs
    
    /* ***************************************************************
    * Metodo: startServer
    * Funcao: Create a server class
    * Parametros: event= Click event
    * Retorno: void
    *************************************************************** */
    @FXML
    void startServer(ActionEvent event) {
        Server server = new Server();
        new Thread(server::startServer).start();
    } // End startServer

    /* ***************************************************************
    * Metodo: addLog
    * Funcao: Add a log to server text area
    * Parametros: log= A String representing the log
    * Retorno: void
    *************************************************************** */
    public static void addLog(String log) {
        Platform.runLater(() -> {
            serverTextArea_.appendText(log + "\n"); // Add log to gui
        });
    } // End addLog

    /* ***************************************************************
    * Metodo: initialize
    * Funcao: Define methods that executes when the current window opens
    * Parametros: location&resources= Fxml arguments
    * Retorno: void
    *************************************************************** */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serverTextArea_ = serverTextArea;
    } // End initialize
} // End class MainController
