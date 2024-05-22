/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 14/05/2024
* Ultima alteracao.: //
* Nome.............: MainController
* Funcao...........: Start the algorithm
*************************************************************** */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    @FXML private CheckBox checkBox1_2_6;
    @FXML private CheckBox checkBox1_3_2;
    @FXML private CheckBox checkBox2_4_5;
    @FXML private CheckBox checkBox2_6_4;
    @FXML private CheckBox checkBox3_4_2;
    @FXML private CheckBox checkBox3_5_1;
    @FXML private CheckBox checkBox4_5_8;
    @FXML private CheckBox checkBox4_7_9;
    @FXML private CheckBox checkBox5_8_4;
    @FXML private CheckBox checkBox6_7_5;
    @FXML private CheckBox checkBox6_9_3;
    @FXML private CheckBox checkBox7_8_9;
    @FXML private CheckBox checkBox8_9_1;
    @FXML private Slider receiverSlider;
    @FXML private Slider senderSlider;
    @FXML private Button startButton;
    @FXML private Button sendPackageButton;
    @FXML private Label warningLabel;

    private ArrayList<ImageView> edges = new ArrayList<>();
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>(); 
    private ArrayList<Node> nodes = new ArrayList<>();

    // Declaration of the routers
    private Node node1 = new Node(1);
    private Node node2 = new Node(2);
    private Node node3 = new Node(3);
    private Node node4 = new Node(4);
    private Node node5 = new Node(5);
    private Node node6 = new Node(6);
    private Node node7 = new Node(7);
    private Node node8 = new Node(8);
    private Node node9 = new Node(9);

    /* ***************************************************************
    * Metodo: startAlgorithm
    * Funcao: Define and start threads
    * Parametros: event= Click event
    * Retorno: void
    *************************************************************** */
    @FXML
    void startAlgorithm(ActionEvent event) throws InterruptedException, FileNotFoundException, IOException {
        warningLabel.setVisible(false);
        nodes.clear();
        resetAllDistanceTables();
        setAllImagesVisible();
        String filePath = "backboneToUse.txt";

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
        ArrayList<ImageView> edgesToSetInvisible = new ArrayList<>();
        for(CheckBox current : checkBoxes) {
            if(current.isSelected()) {
                String lineAux = current.getId().replaceAll("[^0-9]", "");
                int currentNode1Id = Integer.parseInt(lineAux.substring(0, 1));
                int currentNode2Id = Integer.parseInt(lineAux.substring(1, 2));
                int currentWeight = Integer.parseInt(lineAux.substring(2, 3));

                String content = currentNode1Id + "" + currentNode2Id + "" + currentWeight;
                writer.write(content);
                writer.newLine();
            } else {
                edgesToSetInvisible.add(edges.get(checkBoxes.indexOf(current)));
            }
        }
        writer.close();
        
        // Read the updated file and add the corrects neighbors
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            int currentNode1Id = Integer.parseInt(line.substring(0, 1));
            int currentNode2Id = Integer.parseInt(line.substring(1, 2));
            int currentWeight= Integer.parseInt(line.substring(2, 3));

            Node currentNode1 = getNodeById(currentNode1Id);
            Node currentNode2 = getNodeById(currentNode2Id);
            
            currentNode1.addNeighbor(currentNode2, currentWeight);
            currentNode2.addNeighbor(currentNode1, currentWeight);

            if(!nodes.contains(currentNode1)) nodes.add(currentNode1);
            if(!nodes.contains(currentNode2)) nodes.add(currentNode2);
        }
        reader.close();

        // Update ui with correct edges
        try {
            for(ImageView edge : edgesToSetInvisible) {
                if(edgesToSetInvisible.contains(edge)) {
                    edge.setVisible(false);
                }
            }
    
            if(edges.isEmpty()) throw new Exception();
        }  catch(Exception e) {
            warningLabel.setText("Ao menos uma aresta deve existir");
            warningLabel.setVisible(true);
        }

        distanceVectorAlgorithm distanceVector = new distanceVectorAlgorithm(nodes);
        distanceVector.algorithm();

        sendPackageButton.setDisable(false);
    } // End startAlgorithm

    @FXML
    void showPath(ActionEvent event) throws InterruptedException {
        warningLabel.setVisible(false);
        int senderId = (int) senderSlider.getValue();
        int receiverId = (int) receiverSlider.getValue();

        ShowPath bestPath = new ShowPath(senderId, receiverId, nodes, edges);
        try {
            bestPath.algorithm();
        } catch(WrongInputException e) {
            warningLabel.setVisible(true);
            warningLabel.setText(e.getMessage());
        } // End try catch
    } // End showPath

    /* ***************************************************************
    * Metodo: initialize
    * Funcao: Define methods that executes when the current window opens
    * Parametros: location&resources= Fxml arguments
    * Retorno: void
    *************************************************************** */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        warningLabel.setVisible(false);
        sendPackageButton.setDisable(true);

        checkBoxes.add(checkBox1_2_6);
        checkBoxes.add(checkBox1_3_2);
        checkBoxes.add(checkBox2_4_5);
        checkBoxes.add(checkBox2_6_4);
        checkBoxes.add(checkBox3_4_2);
        checkBoxes.add(checkBox3_5_1);
        checkBoxes.add(checkBox4_5_8);
        checkBoxes.add(checkBox4_7_9);
        checkBoxes.add(checkBox5_8_4);
        checkBoxes.add(checkBox6_7_5);
        checkBoxes.add(checkBox6_9_3);
        checkBoxes.add(checkBox7_8_9);
        checkBoxes.add(checkBox8_9_1);

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
    } // End initialize

    public void setAllImagesVisible() {
        edge1and2.setVisible(true);
        edge1and3.setVisible(true);
        edge2and4.setVisible(true);
        edge2and6.setVisible(true);
        edge3and4.setVisible(true);
        edge3and5.setVisible(true);
        edge4and5.setVisible(true);
        edge4and7.setVisible(true);
        edge5and8.setVisible(true);
        edge6and7.setVisible(true);
        edge6and9.setVisible(true);
        edge7and8.setVisible(true);
        edge8and9.setVisible(true);
    } // End setAllImagesInvisible

    public Node getNodeById(int id) {
        switch(id) {
            case 1: return node1;
            case 2: return node2;
            case 3: return node3;
            case 4: return node4;
            case 5: return node5;
            case 6: return node6;
            case 7: return node7;
            case 8: return node8;
            default: return node9;
        }
    }

    public void resetAllDistanceTables() {
        ArrayList<Structure> arrayAux = new ArrayList<>();

        arrayAux.add(new Structure(1, Integer.MAX_VALUE - 10, null));
        arrayAux.add(new Structure(2, Integer.MAX_VALUE - 10, null));
        arrayAux.add(new Structure(3, Integer.MAX_VALUE - 10, null));
        arrayAux.add(new Structure(4, Integer.MAX_VALUE - 10, null));
        arrayAux.add(new Structure(5, Integer.MAX_VALUE - 10, null));
        arrayAux.add(new Structure(6, Integer.MAX_VALUE - 10, null));
        arrayAux.add(new Structure(7, Integer.MAX_VALUE - 10, null));
        arrayAux.add(new Structure(8, Integer.MAX_VALUE - 10, null));
        arrayAux.add(new Structure(9, Integer.MAX_VALUE - 10, null));

        ArrayList<Structure> forNode1 = new ArrayList<>();
        ArrayList<Structure> forNode2 = new ArrayList<>();
        ArrayList<Structure> forNode3 = new ArrayList<>();
        ArrayList<Structure> forNode4 = new ArrayList<>();
        ArrayList<Structure> forNode5 = new ArrayList<>();
        ArrayList<Structure> forNode6 = new ArrayList<>();
        ArrayList<Structure> forNode7 = new ArrayList<>();
        ArrayList<Structure> forNode8 = new ArrayList<>();
        ArrayList<Structure> forNode9 = new ArrayList<>();

        for(Structure item : arrayAux) {
            forNode1.add(item.clone());
            forNode2.add(item.clone());
            forNode3.add(item.clone());
            forNode4.add(item.clone());
            forNode5.add(item.clone());
            forNode6.add(item.clone());
            forNode7.add(item.clone());
            forNode8.add(item.clone());
            forNode9.add(item.clone());
        }

        node1.setDistanceTable(forNode1);
        node2.setDistanceTable(forNode2);
        node3.setDistanceTable(forNode3);
        node4.setDistanceTable(forNode4);
        node5.setDistanceTable(forNode5);
        node6.setDistanceTable(forNode6);
        node7.setDistanceTable(forNode7);
        node8.setDistanceTable(forNode8);
        node9.setDistanceTable(forNode9);

        node1.correctDistanceTable();
        node2.correctDistanceTable();
        node3.correctDistanceTable();
        node4.correctDistanceTable();
        node5.correctDistanceTable();
        node6.correctDistanceTable();
        node7.correctDistanceTable();
        node8.correctDistanceTable();
        node9.correctDistanceTable();
    }
} // End class MainController 
