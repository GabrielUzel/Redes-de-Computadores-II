/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 19/06/2024
* Ultima alteracao.: 28/06/2024
* Nome.............: Principal
* Funcao...........: Start the server interface
*************************************************************** */
package Servidor;

import java.io.IOException;
import Servidor.view.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {
    /* ***************************************************************
    * Metodo: main
    * Funcao: Execute the program
    * Parametros: args= Arguments to start the program
    * Retorno: void
    *************************************************************** */
    public static void main(String [] args) {
        launch(args);
    } // End main

    /* ***************************************************************
    * Metodo: start
    * Funcao: Open the UI
    * Parametros: currentStage= Stage to open the fxml file
    * Retorno: void
    *************************************************************** */
    @Override
    public void start(Stage stage) throws IOException {
        // Controller declaration to prevent exception
        MainController controller = new MainController();

        // Open file and set scene
        Parent mainParent = FXMLLoader.load(getClass().getResource("view/ServerPage.fxml"));
        Scene mainScene = new Scene(mainParent);

        // Show UI
        stage.setTitle("Server");
        stage.setScene(mainScene);
        stage.show();        
    } // End start
} // End class Principal