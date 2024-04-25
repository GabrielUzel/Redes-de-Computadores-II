import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Option2Controller {
    
    @FXML
    void backToMenu(ActionEvent event) {
        Principal.changeSceneMenu();
    }
}
