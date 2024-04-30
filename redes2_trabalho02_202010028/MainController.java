/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 20/04/2024
* Ultima alteracao.: 29/04/2024
* Nome.............: AlgorithmController
* Funcao...........: Start the algorithm
*************************************************************** */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class MainController implements Initializable {
    // Fxml variables
    @FXML private Slider receiverSlider;
    @FXML private Slider senderSlider;
    @FXML private Label receiverLabel;
    @FXML private Label senderLabel;
    @FXML private Button startButton;
    private int senderSliderValue;
    private int receiverSliderValue;

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
        senderSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldNumber, Number newNumber) {
                senderSliderValue = (int) senderSlider.getValue();
                senderLabel.setText(Integer.toString(senderSliderValue));
            }
        });

        receiverSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldNumber, Number newNumber) {
                receiverSliderValue = (int) receiverSlider.getValue();
                receiverLabel.setText(Integer.toString(receiverSliderValue));
            }
        });
    } // End initialize
} // End class AlgorithmController 
