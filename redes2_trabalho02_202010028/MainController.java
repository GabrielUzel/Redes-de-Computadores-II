/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 02/05/2024
* Ultima alteracao.: //
* Nome.............: AlgorithmController
* Funcao...........: Start the algorithm
*************************************************************** */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class MainController implements Initializable {
    // Fxml variables
    @FXML private Slider receiverSlider;
    @FXML private Slider senderSlider;
    @FXML private Button startButton;

    /* ***************************************************************
    * Metodo: startAlgorithm
    * Funcao: Define and start threads
    * Parametros: event= Click event
    * Retorno: void
    *************************************************************** */
    @FXML
    void startAlgorithm(ActionEvent event) throws InterruptedException {
        int senderId = (int) senderSlider.getValue();
        int receiverId = (int) receiverSlider.getValue();

        
    } // End startAlgorithm

    /* ***************************************************************
    * Metodo: initialize
    * Funcao: Define methods that executes when the current window opens
    * Parametros: location&resources= Fxml arguments
    * Retorno: void
    *************************************************************** */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    } // End initialize
} // End class AlgorithmController 
