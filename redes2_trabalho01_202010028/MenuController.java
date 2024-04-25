import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {
    @FXML
    private Button option1Button;

    @FXML
    private Button option2Button;

    @FXML
    private Button option3Button;

    @FXML
    private Button option4Button;

    @FXML
    void openOption1() {
        Principal.changeSceneOption1();
    }
    
    @FXML
    void openOption2() {
        Principal.changeSceneOption2();
    }

    @FXML
    void openOption3() {
        Principal.changeSceneOption3();
    }

    @FXML
    void openOption4() {
        Principal.changeSceneOption4();
    }
}
