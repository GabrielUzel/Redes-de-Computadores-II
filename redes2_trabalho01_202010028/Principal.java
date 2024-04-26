import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {
    public static void main (String [] args) {
        launch (args);
    }

    private static Stage stage;
    private static Scene menuScene;
    private static Scene option1Scene;
    private static Scene option2Scene;
    private static Scene option3Scene;
    private static Scene option4Scene;

    @Override
    public void start (Stage currentStage) throws IOException {
        MenuController controller = new MenuController();
        AlgorithmController controller1 = new AlgorithmController();
        stage = currentStage;

        Parent menuParent = FXMLLoader.load(getClass().getResource("Pages/MenuPage.fxml"));
        Parent option1Parent = FXMLLoader.load(getClass().getResource("Pages/Option1Page.fxml"));
        Parent option2Parent = FXMLLoader.load(getClass().getResource("Pages/Option2Page.fxml"));
        Parent option3Parent = FXMLLoader.load(getClass().getResource("Pages/Option3Page.fxml"));
        Parent option4Parent = FXMLLoader.load(getClass().getResource("Pages/Option4Page.fxml"));

        menuScene = new Scene(menuParent);
        option1Scene = new Scene(option1Parent);
        option2Scene = new Scene(option2Parent);
        option3Scene = new Scene(option3Parent);
        option4Scene = new Scene(option4Parent);

        stage.setTitle("Algoritmo por inundação");
        stage.setScene(menuScene);
        stage.show();
    }

    public static void changeSceneMenu() {
        stage.setScene(menuScene);
    }

    public static void changeSceneOption1() {
        stage.setScene(option1Scene);
    }

    public static void changeSceneOption2() {
        stage.setScene(option2Scene);
    }

    public static void changeSceneOption3() {
        stage.setScene(option3Scene);
    }

    public static void changeSceneOption4() {
        stage.setScene(option4Scene);
    }
}
