/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 20/04/2024
* Ultima alteracao.: 29/04/2024
* Nome.............: MenuController
* Funcao...........: Change screens on button click
*************************************************************** */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

public class MenuController implements Initializable {
    // Fxml variables
    @FXML private Button option1Button;
    @FXML private Button option2Button;
    @FXML private Button option3Button;
    @FXML private Button option4Button;
    @FXML private Button showMessageButton;

    /* ***************************************************************
    * Metodo: openOption1
    * Funcao: Load option1 page
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @FXML
    void openOption1() {
        Principal.changeSceneOption1();
    } // End openoption1
    
    /* ***************************************************************
    * Metodo: openOption2
    * Funcao: Load option2 page
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @FXML
    void openOption2() {
        Principal.changeSceneOption2();
    } // End openOption2

    /* ***************************************************************
    * Metodo: openOption3
    * Funcao: Load option3 page
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @FXML
    void openOption3() {
        Principal.changeSceneOption3();
    } // End openOption3

    /* ***************************************************************
    * Metodo: openOption4
    * Funcao: Load option4 page
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @FXML
    void openOption4() {
        Principal.changeSceneOption4();
    } // End openOption4

    /* ***************************************************************
    * Metodo: initialize
    * Funcao: Define methods that executes when the current window opens
    * Parametros: location&resources= Fxml arguments
    * Retorno: void
    *************************************************************** */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImageView imageView = new ImageView(Gallery.showMessageImg); // Open message icon image
        imageView.setFitWidth(40); // Set open message width
        imageView.setFitHeight(40); // Set open message height
        showMessageButton.setGraphic(imageView); // Load the image in the button
    } // End initialize

    /* ***************************************************************
    * Metodo: showMessage
    * Funcao: Define the message that describes this project
    * Parametros: event= Click event
    * Retorno: void
    *************************************************************** */
    @FXML
    void showMessage(ActionEvent event) {
        // Define and show a alert
        Alert message = new Alert(AlertType.INFORMATION);
        message.setTitle("IMPORTANTE!!");
        message.setContentText("Esse algoritmo utiliza threads que não param para funcionar, caso deseje trocar de opção você terá que fechar e abrir o programa. Para a opção 4, foi criada uma variável chamada arrived para controlara chegada do pacote ao destino, quando essa variável é igual a true os roteadores irão parar de mandar pacotes.");
        message.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        message.show();
    } // End showMessage
} // End class MenuController
