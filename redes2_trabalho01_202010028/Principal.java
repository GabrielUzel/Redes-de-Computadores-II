/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 20/04/2024
* Ultima alteracao.: 29/04/2024
* Nome.............: Principal
* Funcao...........: Start the interface
*************************************************************** */
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {
    private static Stage stage;
    private static Scene menuScene;
    private static Scene option1Scene;
    private static Scene option2Scene;
    private static Scene option3Scene;
    private static Scene option4Scene;

    /* ***************************************************************
    * Metodo: main
    * Funcao: Execute the program
    * Parametros: args= Arguments to start the program
    * Retorno: void
    *************************************************************** */
    public static void main (String [] args) {
        launch (args);
    } // End main

    /* ***************************************************************
    * Metodo: start
    * Funcao: Open the UI
    * Parametros: currentStage= Stage to open the fxml file
    * Retorno: void
    *************************************************************** */
    @Override
    public void start (Stage currentStage) throws IOException {
        // Controllers declaration to prevent exception
        MenuController controller = new MenuController();
        AlgorithmController controller1 = new AlgorithmController();

        // Define the variable to the current stage to open the correct window
        stage = currentStage;

        // Open files
        Parent menuParent = FXMLLoader.load(getClass().getResource("view/MenuPage.fxml"));
        Parent option1Parent = FXMLLoader.load(getClass().getResource("view/Option1Page.fxml"));
        Parent option2Parent = FXMLLoader.load(getClass().getResource("view/Option2Page.fxml"));
        Parent option3Parent = FXMLLoader.load(getClass().getResource("view/Option3Page.fxml"));
        Parent option4Parent = FXMLLoader.load(getClass().getResource("view/Option4Page.fxml"));

        // Set scenes
        menuScene = new Scene(menuParent);
        option1Scene = new Scene(option1Parent);
        option2Scene = new Scene(option2Parent);
        option3Scene = new Scene(option3Parent);
        option4Scene = new Scene(option4Parent);

        // Show UI
        stage.setTitle("Algoritmo por inundação");
        stage.setScene(menuScene);
        stage.show();
    } // End start

    /* ***************************************************************
    * Metodo: changeSceneMenu
    * Funcao: Change the scene to open the menu page
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public static void changeSceneMenu() {
        stage.setScene(menuScene);
    } // End changeSceneMenu

    /* ***************************************************************
    * Metodo: changeSceneOption1
    * Funcao: Change the scene to open the option 1 page
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public static void changeSceneOption1() {
        stage.setScene(option1Scene);
    } // End changeSceneOption1

    /* ***************************************************************
    * Metodo: changeSceneOption2
    * Funcao: Change the scene to open the option 2 page
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public static void changeSceneOption2() {
        stage.setScene(option2Scene);
    } // End changeSceneOption2

    /* ***************************************************************
    * Metodo: changeSceneOption3
    * Funcao: Change the scene to open the option 3 page
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public static void changeSceneOption3() {
        stage.setScene(option3Scene);
    } // End changeSceneOption3

    /* ***************************************************************
    * Metodo: changeSceneOption4
    * Funcao: Change the scene to open the option 4 page
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public static void changeSceneOption4() {
        stage.setScene(option4Scene);
    } // End changeSceneOption4
} // End class Principal
