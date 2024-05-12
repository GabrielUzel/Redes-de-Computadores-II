/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 02/05/2024
* Ultima alteracao.: 12/05/2024
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
import javafx.scene.image.ImageView;

public class MainController implements Initializable {
    // Fxml variables
    @FXML private Slider receiverSlider;
    @FXML private Slider senderSlider;
    @FXML private Button startButton;
    @FXML private Label warningLabel;
    
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

    /* ***************************************************************
    * Metodo: startAlgorithm
    * Funcao: Define and start threads
    * Parametros: event= Click event
    * Retorno: void
    *************************************************************** */
    @FXML
    void startAlgorithm(ActionEvent event) throws InterruptedException {
        setAllImagesInvisible();
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
        } // End try catch

        showPath(dijasktra.getBestPath());
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
        setAllImagesInvisible();
    } // End initialize

    /* ***************************************************************
    * Metodo: showPath
    * Funcao: Update ui to show the best path
    * Parametros: bestpath= array list that represents the best path
    * Retorno: void
    *************************************************************** */
    public void showPath(ArrayList<Integer> bestPath) {
        int j = 1; // Second element of the two by two pair edge

        for(int i = 0; i < bestPath.size(); i++) {
            if(j == bestPath.size()) break; // When arrives the destination, exit for loop

            getEdge(bestPath.get(i), bestPath.get(j)).setVisible(true); // Set the current edge image view visible
            j++;
        } // End for
    } // End showPath

    /* ***************************************************************
    * Metodo: getEdge
    * Funcao: Get the edge image view given the nodes
    * Parametros: nodes number
    * Retorno: The correct image view
    *************************************************************** */
    public ImageView getEdge(int node1, int node2) {
        if((node1 == 1 && node2 == 2) || (node1 == 2 && node2 == 1)) return edge1and2;
        if((node1 == 1 && node2 == 3) || (node1 == 3 && node2 == 1)) return edge1and3;
        if((node1 == 2 && node2 == 4) || (node1 == 4 && node2 == 2)) return edge2and4;
        if((node1 == 2 && node2 == 6) || (node1 == 6 && node2 == 2)) return edge2and6;
        if((node1 == 3 && node2 == 4) || (node1 == 4 && node2 == 3)) return edge3and4;
        if((node1 == 3 && node2 == 5) || (node1 == 5 && node2 == 3)) return edge3and5;
        if((node1 == 4 && node2 == 5) || (node1 == 5 && node2 == 4)) return edge4and5;
        if((node1 == 4 && node2 == 7) || (node1 == 7 && node2 == 4)) return edge4and7;
        if((node1 == 5 && node2 == 8) || (node1 == 8 && node2 == 5)) return edge5and8;
        if((node1 == 6 && node2 == 7) || (node1 == 7 && node2 == 6)) return edge6and7;
        if((node1 == 6 && node2 == 9) || (node1 == 9 && node2 == 6)) return edge6and9;
        if((node1 == 7 && node2 == 8) || (node1 == 8 && node2 == 7)) return edge7and8;

        return edge8and9;
    } // End getEdge

    /* ***************************************************************
    * Metodo: setAllImagesInvisible
    * Funcao: Set all edges invisible at the begining of the algorithm
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void setAllImagesInvisible() {
        edge1and2.setVisible(false);
        edge1and3.setVisible(false);
        edge2and4.setVisible(false);
        edge2and6.setVisible(false);
        edge3and4.setVisible(false);
        edge3and5.setVisible(false);
        edge4and5.setVisible(false);
        edge4and7.setVisible(false);
        edge5and8.setVisible(false);
        edge6and7.setVisible(false);
        edge6and9.setVisible(false);
        edge7and8.setVisible(false);
        edge8and9.setVisible(false);
    } // End setAllImagesInvisible
} // End class AlgorithmController 
