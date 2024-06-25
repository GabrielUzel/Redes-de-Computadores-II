/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 19/06/2024
* Ultima alteracao.: 28/06/2024
* Nome.............: Principal
* Funcao...........: Start the client interface
*************************************************************** */
package Cliente;

import java.io.IOException;
import Cliente.view.ClientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {
    private static Stage stage;
    private static Scene mainScene;
    private static Scene groupsScene;
    private static Scene selectedGroupScene;

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
    public void start(Stage currentStage) throws IOException {
        // Controller declaration to prevent exception
        ClientController controller = new ClientController();

        // Define the variable to the current stage to open the correct window
        stage = currentStage;

        // Open file and set scene
        Parent mainParent = FXMLLoader.load(getClass().getResource("view/ClientPage.fxml"));
        Parent groupsParent = FXMLLoader.load(getClass().getResource("view/GroupsPage.fxml"));
        Parent selectedGroupParent = FXMLLoader.load(getClass().getResource("view/SelectedGroupPage.fxml"));

        // Set scenes
        mainScene = new Scene(mainParent);
        groupsScene = new Scene(groupsParent);
        selectedGroupScene = new Scene(selectedGroupParent);

        // Show UI
        stage.setTitle("Client");
        stage.setScene(mainScene);
        stage.show();        
    } // End start

    /* ***************************************************************
    * Metodo: changeSceneGroups
    * Funcao: Change the scene to open the groups page
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public static void changeSceneGroups() {
        stage.setScene(groupsScene);
    } // End changeSceneGroups

    /* ***************************************************************
    * Metodo: changeSceneSelectedGroup
    * Funcao: Change the scene to open the selected group page
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public static void changeSceneSelectedGroup() {
        stage.setScene(selectedGroupScene);
    } // End changeSceneGroups
} // End Principal
