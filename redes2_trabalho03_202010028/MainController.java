/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 14/05/2024
* Ultima alteracao.: //
* Nome.............: MainController
* Funcao...........: Start the algorithm
*************************************************************** */
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class MainController implements Initializable {
    // Fxml variables
    @FXML private ImageView edge1and2;
    @FXML private ImageView edge1and3;
    @FXML private ImageView edge2and4;
    @FXML private ImageView edge2and6;
    @FXML private ImageView edge3and4;
    @FXML private ImageView edge3and5;
    @FXML private ImageView edge4and5;
    @FXML private ImageView edge4and7;
    @FXML private ImageView edge5and8;
    @FXML private ImageView edge6and7;
    @FXML private ImageView edge6and9;
    @FXML private ImageView edge7and8;
    @FXML private ImageView edge8and9;
    @FXML private Slider receiverSlider;
    @FXML private Slider senderSlider;
    @FXML private Button startButton;
    @FXML private Label warningLabel;

    /* ***************************************************************
    * Metodo: startAlgorithm
    * Funcao: Define and start threads
    * Parametros: event= Click event
    * Retorno: void
    *************************************************************** */
    @FXML
    void startAlgorithm(ActionEvent event) throws InterruptedException {
        warningLabel.setVisible(false);

        int senderId = (int) senderSlider.getValue();
        int receiverId = (int) receiverSlider.getValue();

        // Declaration of the routers
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
    } // End startAlgorithm

    /* ***************************************************************
    * Metodo: initialize
    * Funcao: Define methods that executes when the current window opens
    * Parametros: location&resources= Fxml arguments
    * Retorno: void
    *************************************************************** */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        warningLabel.setVisible(false);
    } // End initialize
} // End class MainController 
