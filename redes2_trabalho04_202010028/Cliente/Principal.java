package Cliente;

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
        Parent mainParent = FXMLLoader.load(getClass().getResource("view/ClientPage.fxml"));
        Scene mainScene = new Scene(mainParent);

        // Show UI
        stage.setTitle("Client");
        stage.setScene(mainScene);
        stage.show();        
    } // End start
} // End Principal
