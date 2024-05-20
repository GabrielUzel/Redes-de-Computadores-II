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
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    @FXML private CheckBox checkBox1_2;
    @FXML private CheckBox checkBox1_3;
    @FXML private CheckBox checkBox2_4;
    @FXML private CheckBox checkBox2_6;

    private ArrayList<ImageView> edges = new ArrayList<>();
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>(); 

    /* ***************************************************************
    * Metodo: startAlgorithm
    * Funcao: Define and start threads
    * Parametros: event= Click event
    * Retorno: void
    *************************************************************** */
    @FXML
    void startAlgorithm(ActionEvent event) throws InterruptedException {
        warningLabel.setText("Remetente e destinatario precisam ser diferentes");
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

        fillEdgesArray();

        try {
            ArrayList<ImageView> edgesToRemove = new ArrayList<>();

            for(CheckBox current : checkBoxes) {
                if(!current.isSelected()) edgesToRemove.add(edges.get(checkBoxes.indexOf(current)));
            }

            for(ImageView edge : edgesToRemove) {
                edges.remove(edge);
            }
        } catch(Exception e) {
            warningLabel.setText("Ao menos uma aresta deve existir");
            warningLabel.setVisible(true);
            edges.clear();
        } finally {
            setAllImagesInvisible();
        }

        for(ImageView edge : edges) {
            edge.setVisible(true);
        }

        node1.addNeighborsDistance();
        node2.addNeighborsDistance();
        node3.addNeighborsDistance();
        node4.addNeighborsDistance();
        node5.addNeighborsDistance();
        node6.addNeighborsDistance();
        node7.addNeighborsDistance();
        node8.addNeighborsDistance();
        node9.addNeighborsDistance();

        node2.updateDistanceTable(node1.getDistanceTable(), 1);
        for(Structure index : node2.getDistanceTable()) {
            System.out.println(index.getNodeId() + " " + index.getDistance() + " " + index.getNodeToSendPackage());
        }

        edges.clear();
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

        checkBoxes.add(checkBox1_2);
        checkBoxes.add(checkBox1_3);
        checkBoxes.add(checkBox2_4);
        checkBoxes.add(checkBox2_6);
    } // End initialize

    public void fillEdgesArray() {
        edges.add(edge1and2);
        edges.add(edge1and3);
        edges.add(edge2and4);
        edges.add(edge2and6);
        edges.add(edge3and4);
        edges.add(edge3and5);
        edges.add(edge4and5);
        edges.add(edge4and7);
        edges.add(edge5and8);
        edges.add(edge6and7);
        edges.add(edge6and9);
        edges.add(edge7and8);
        edges.add(edge8and9);
    } // End fillEdgesArray

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
} // End class MainController 
