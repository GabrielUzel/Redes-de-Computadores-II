/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 02/05/2024
* Ultima alteracao.: //
* Nome.............: AlgorithmController
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

public class MainController implements Initializable {
    // Fxml variables
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
        Node node1 = new Node(1, 46, 379);
        Node node2 = new Node(2, 198, 204);
        Node node3 = new Node(3, 198, 529);
        Node node4 = new Node(4, 350, 379);
        Node node5 = new Node(5, 554, 529);
        Node node6 = new Node(6, 554, 204);
        Node node7 = new Node(7, 554, 379);
        Node node8 = new Node(8, 704, 474);
        Node node9 = new Node(9, 826, 379);

        // Define each routers neighbours
        node1.addNeighbor(node2, 6);
        node1.addNeighbor(node3, 2);

        node2.addNeighbor(node1, 6);
        node2.addNeighbor(node4, 5);
        node2.addNeighbor(node6, 4);

        node3.addNeighbor(node1, 2);
        node3.addNeighbor(node4, 2);
        node3.addNeighbor(node5, 1);

        node4.addNeighbor(node2, 5);
        node4.addNeighbor(node3, 2);
        node4.addNeighbor(node5, 8);
        node4.addNeighbor(node7, 9);

        node5.addNeighbor(node3, 1);
        node5.addNeighbor(node4, 8);
        node5.addNeighbor(node8, 4);

        node6.addNeighbor(node2, 4);
        node6.addNeighbor(node7, 5);
        node6.addNeighbor(node9, 3);

        node7.addNeighbor(node4, 9);
        node7.addNeighbor(node6, 5);
        node7.addNeighbor(node8, 9);

        node8.addNeighbor(node5, 4);
        node8.addNeighbor(node7, 9);
        node8.addNeighbor(node9, 1);

        node9.addNeighbor(node6, 3);
        node9.addNeighbor(node8, 1);

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);
        nodes.add(node6);
        nodes.add(node7);
        nodes.add(node8);
        nodes.add(node9);

        Dijasktra dijasktra = new Dijasktra(nodes, senderId, receiverId);
        try {
            dijasktra.algorithm();
        } catch(WrongInputException e) {
            warningLabel.setVisible(true);
        }

        for(Integer node : dijasktra.getBestPath()) {
            System.out.println(node);
        }
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
} // End class AlgorithmController 
