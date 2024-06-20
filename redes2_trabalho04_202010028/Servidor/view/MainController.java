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
    private static TextArea serverTextArea_;
    
    @FXML
    void startServer(ActionEvent event) {
        Server server = new Server();
        new Thread(server::startServer).start();
    } // End startServer

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
} // End MainController
