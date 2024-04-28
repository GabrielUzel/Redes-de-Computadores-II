import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MenuController implements Initializable {
    @FXML
    private Button option1Button;

    @FXML
    private Button option2Button;

    @FXML
    private Button option3Button;

    @FXML
    private Button option4Button;

    @FXML
    private Button showMessageButton;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImageView imageView = new ImageView(Gallery.showMessageImg);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        showMessageButton.setGraphic(imageView);
    }

    @FXML
    void showMessage(ActionEvent event) {
        Alert message = new Alert(AlertType.INFORMATION);
        message.setTitle("IMPORTANTE!!");
        message.setContentText("Esse algoritmo utiliza threads que não param para funcionar, caso deseje trocar de opção você terá que fechar e abrir o programa. Para a opção 4, foi criada uma variável chamada arrived para controlara chegada do pacote ao destino, quando essa variável é igual a true os roteadores irão parar de mandar pacotes.");
        message.show();
    }
}
