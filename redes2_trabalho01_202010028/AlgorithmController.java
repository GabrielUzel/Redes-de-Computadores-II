/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 20/04/2024
* Ultima alteracao.: 29/04/2024
* Nome.............: AlgorithmController
* Funcao...........: Start the algorithm
*************************************************************** */
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AlgorithmController implements Initializable {
    // Fxml variables
    @FXML private Label optionLabel;
    @FXML private Button buttonTeste;
    @FXML private Button backToMenu;
    @FXML private Button startButton;
    @FXML private Button stopButton;
    @FXML private ImageView icon1to2, icon1to3;
    @FXML private ImageView icon2to1, icon2to4, icon2to6;
    @FXML private ImageView icon3to1, icon3to4, icon3to5;
    @FXML private ImageView icon4to2, icon4to3, icon4to5, icon4to7;
    @FXML private ImageView icon5to3, icon5to4, icon5to8;
    @FXML private ImageView icon6to2, icon6to7, icon6to9;
    @FXML private ImageView icon7to4, icon7to6, icon7to8;
    @FXML private ImageView icon8to5, icon8to7, icon8to9;
    @FXML private ImageView icon9to6, icon9to8;
    @FXML private Label packageArrivedLabel;
    @FXML private Label packagesCounterLabel;

    /* ***************************************************************
    * Metodo: backToMenu
    * Funcao: Return to the menu page
    * Parametros: event= Click event
    * Retorno: void
    *************************************************************** */
    @FXML
    void backToMenu(ActionEvent event) {
        Principal.changeSceneMenu();
    } // End backToMenu

    /* ***************************************************************
    * Metodo: startAlgorithm
    * Funcao: Define and start threads
    * Parametros: event= Click event
    * Retorno: void
    *************************************************************** */
    @FXML
    void startAlgorithm(ActionEvent event) throws InterruptedException {
        // Array with the Images views that will be used to represent the packages
        List<ImageView> icons = new ArrayList<>(); 

        // Add the image views to the array
        icons.add(icon1to2);
        icons.add(icon1to3);
        icons.add(icon2to1);
        icons.add(icon2to4);
        icons.add(icon2to6);
        icons.add(icon3to1);
        icons.add(icon3to4);
        icons.add(icon3to5);
        icons.add(icon4to2);
        icons.add(icon4to3);
        icons.add(icon4to5);
        icons.add(icon4to7);
        icons.add(icon5to3);
        icons.add(icon5to4);
        icons.add(icon5to8);
        icons.add(icon6to2);
        icons.add(icon6to7);
        icons.add(icon6to9);
        icons.add(icon7to4);
        icons.add(icon7to6);
        icons.add(icon7to8);
        icons.add(icon8to5);
        icons.add(icon8to7);
        icons.add(icon8to9);
        icons.add(icon9to6);
        icons.add(icon9to8);

        // Declare a variable that will be used to define what algorithm was selected
        int optionLabelValue = 0;

        if(optionLabel.getText().equals("Option 1")) optionLabelValue = 1; // Option 1 was selected
        if(optionLabel.getText().equals("Option 2")) optionLabelValue = 2; // Option 2 was selected
        if(optionLabel.getText().equals("Option 3")) optionLabelValue = 3; // Option 3 was selected
        if(optionLabel.getText().equals("Option 4")) optionLabelValue = 4; // Option 4 was selected

        // Declaration of the routers
        Node node1 = new Node(1, 46, 379, icons, packageArrivedLabel, packagesCounterLabel, optionLabelValue);
        Node node2 = new Node(2, 198, 204, icons, packageArrivedLabel, packagesCounterLabel, optionLabelValue);
        Node node3 = new Node(3, 198, 529, icons, packageArrivedLabel, packagesCounterLabel, optionLabelValue);
        Node node4 = new Node(4, 350, 379, icons, packageArrivedLabel, packagesCounterLabel, optionLabelValue);
        Node node5 = new Node(5, 554, 529, icons, packageArrivedLabel, packagesCounterLabel, optionLabelValue);
        Node node6 = new Node(6, 554, 204, icons, packageArrivedLabel, packagesCounterLabel, optionLabelValue);
        Node node7 = new Node(7, 554, 379, icons, packageArrivedLabel, packagesCounterLabel, optionLabelValue);
        Node node8 = new Node(8, 704, 474, icons, packageArrivedLabel, packagesCounterLabel, optionLabelValue);
        Node node9 = new Node(9, 826, 379, icons, packageArrivedLabel, packagesCounterLabel, optionLabelValue);

        // Define each routers neighbours
        node1.addNeighbor(node2);
        node1.addNeighbor(node3);

        node2.addNeighbor(node1);
        node2.addNeighbor(node4);
        node2.addNeighbor(node6);

        node3.addNeighbor(node1);
        node3.addNeighbor(node4);
        node3.addNeighbor(node5);

        node4.addNeighbor(node2);
        node4.addNeighbor(node3);
        node4.addNeighbor(node5);
        node4.addNeighbor(node7);

        node5.addNeighbor(node3);
        node5.addNeighbor(node4);
        node5.addNeighbor(node8);

        node6.addNeighbor(node2);
        node6.addNeighbor(node7);
        node6.addNeighbor(node9);

        node7.addNeighbor(node4);
        node7.addNeighbor(node6);
        node7.addNeighbor(node8);

        node8.addNeighbor(node5);
        node8.addNeighbor(node7);
        node8.addNeighbor(node9);

        node9.addNeighbor(node6);
        node9.addNeighbor(node8);

        // First package declaration that will start the algorithm
        Package firstPackage = new Package(1, 9);
        node1.addPackage(firstPackage);

        // Threads start
        node1.start();
        node2.start();
        node3.start();
        node4.start();
        node5.start();
        node6.start();
        node7.start();
        node8.start();
        node9.start();
    } // End startAlgorithm

    /* ***************************************************************
    * Metodo: initialize
    * Funcao: Define methods that executes when the current window opens
    * Parametros: location&resources= Fxml arguments
    * Retorno: void
    *************************************************************** */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set the package image to each image view
        icon1to2.setImage(Gallery.icon);
        icon1to3.setImage(Gallery.icon);
        icon2to1.setImage(Gallery.icon);
        icon2to4.setImage(Gallery.icon);
        icon2to6.setImage(Gallery.icon);
        icon3to1.setImage(Gallery.icon);
        icon3to4.setImage(Gallery.icon);
        icon3to5.setImage(Gallery.icon);
        icon4to2.setImage(Gallery.icon);
        icon4to3.setImage(Gallery.icon);
        icon4to5.setImage(Gallery.icon);
        icon4to7.setImage(Gallery.icon);
        icon5to3.setImage(Gallery.icon);
        icon5to4.setImage(Gallery.icon);
        icon5to8.setImage(Gallery.icon);
        icon6to2.setImage(Gallery.icon);
        icon6to7.setImage(Gallery.icon);
        icon6to9.setImage(Gallery.icon);
        icon7to4.setImage(Gallery.icon);
        icon7to6.setImage(Gallery.icon);
        icon7to8.setImage(Gallery.icon);
        icon8to5.setImage(Gallery.icon);
        icon8to7.setImage(Gallery.icon);
        icon8to9.setImage(Gallery.icon);
        icon9to6.setImage(Gallery.icon);
        icon9to8.setImage(Gallery.icon);

        // Make the image view invisible
        icon1to2.setVisible(false);
        icon1to3.setVisible(false);
        icon2to1.setVisible(false);
        icon2to4.setVisible(false);
        icon2to6.setVisible(false);
        icon3to1.setVisible(false);
        icon3to4.setVisible(false);
        icon3to5.setVisible(false);
        icon4to2.setVisible(false);
        icon4to3.setVisible(false);
        icon4to5.setVisible(false);
        icon4to7.setVisible(false);
        icon5to3.setVisible(false);
        icon5to4.setVisible(false);
        icon5to8.setVisible(false);
        icon6to2.setVisible(false);
        icon6to7.setVisible(false);
        icon6to9.setVisible(false);
        icon7to4.setVisible(false);
        icon7to6.setVisible(false);
        icon7to8.setVisible(false);
        icon8to5.setVisible(false);
        icon8to7.setVisible(false);
        icon8to9.setVisible(false);
        icon9to6.setVisible(false);
        icon9to8.setVisible(false);
    } // End initialize
} // End class AlgorithmController 
