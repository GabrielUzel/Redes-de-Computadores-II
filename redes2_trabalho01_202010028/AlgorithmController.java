import java.net.URL;
import java.util.ResourceBundle;
import Models.*;
import Models.Package;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AlgorithmController implements Initializable {
    @FXML
    private Label optionLabel;

    @FXML
    private Button buttonTeste;

    @FXML
    private Button backToMenu;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private ImageView icon1;

    @FXML
    private ImageView icon2;

    @FXML
    private ImageView icon3;
    
    @FXML
    private ImageView icon4;

    @FXML
    private ImageView icon5;

    @FXML
    private ImageView icon6;

    @FXML
    private ImageView icon7;

    @FXML
    private ImageView icon8;

    @FXML
    private ImageView icon9;

    @FXML
    private Label packageArrivedLabel;

    @FXML
    void backToMenu(ActionEvent event) {
        Principal.changeSceneMenu();
    }

    @FXML
    void startAlgorithm(ActionEvent event) throws InterruptedException {
        if(optionLabel.getText().equals("Option 1")) {
            Node1 node1 = new Node1(1, 46, 379, icon1, packageArrivedLabel);
            Node1 node2 = new Node1(2, 198, 204, icon2, packageArrivedLabel);
            Node1 node3 = new Node1(3, 199, 529, icon3, packageArrivedLabel);
            Node1 node4 = new Node1(4, 350, 379, icon4, packageArrivedLabel);
            Node1 node5 = new Node1(5, 554, 529, icon5, packageArrivedLabel);
            Node1 node6 = new Node1(6, 554, 204, icon6, packageArrivedLabel);
            Node1 node7 = new Node1(7, 554, 379, icon7, packageArrivedLabel);
            Node1 node8 = new Node1(8, 704, 474, icon8, packageArrivedLabel);
            Node1 node9 = new Node1(9, 826, 379, icon9, packageArrivedLabel);

            node1.addNeighbor(node2);
            node1.addNeighbor(node3);
    
            node2.addNeighbor(node1);
            node2.addNeighbor(node4);
            node2.addNeighbor(node6);
    
            node3.addNeighbor(node1);
            node2.addNeighbor(node4);
            node2.addNeighbor(node5);
    
            node4.addNeighbor(node2);
            node4.addNeighbor(node3);
            node4.addNeighbor(node7);
            node4.addNeighbor(node5);
    
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
    
            Package firstPackage = new Package(1, 9);
            node1.sendPackage(firstPackage);
        } else if(optionLabel.getText().equals("Option 2")) {
            Node2 node1 = new Node2(1, 46, 379, icon1, packageArrivedLabel);
            Node2 node2 = new Node2(2, 198, 204, icon2, packageArrivedLabel);
            Node2 node3 = new Node2(3, 199, 529, icon3, packageArrivedLabel);
            Node2 node4 = new Node2(4, 350, 379, icon4, packageArrivedLabel);
            Node2 node5 = new Node2(5, 554, 529, icon5, packageArrivedLabel);
            Node2 node6 = new Node2(6, 554, 204, icon6, packageArrivedLabel);
            Node2 node7 = new Node2(7, 554, 379, icon7, packageArrivedLabel);
            Node2 node8 = new Node2(8, 704, 474, icon8, packageArrivedLabel);
            Node2 node9 = new Node2(9, 826, 379, icon9, packageArrivedLabel);

            node1.addNeighbor(node2);
            node1.addNeighbor(node3);
    
            node2.addNeighbor(node1);
            node2.addNeighbor(node4);
            node2.addNeighbor(node6);
    
            node3.addNeighbor(node1);
            node2.addNeighbor(node4);
            node2.addNeighbor(node5);
    
            node4.addNeighbor(node2);
            node4.addNeighbor(node3);
            node4.addNeighbor(node7);
            node4.addNeighbor(node5);
    
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
    
            Package firstPackage = new Package(1, 9);
            node1.sendPackage(firstPackage);
        } else if(optionLabel.getText().equals("Option 3")) {
            Node3 node1 = new Node3(1, 46, 379, icon1, packageArrivedLabel);
            Node3 node2 = new Node3(2, 198, 204, icon2, packageArrivedLabel);
            Node3 node3 = new Node3(3, 199, 529, icon3, packageArrivedLabel);
            Node3 node4 = new Node3(4, 350, 379, icon4, packageArrivedLabel);
            Node3 node5 = new Node3(5, 554, 529, icon5, packageArrivedLabel);
            Node3 node6 = new Node3(6, 554, 204, icon6, packageArrivedLabel);
            Node3 node7 = new Node3(7, 554, 379, icon7, packageArrivedLabel);
            Node3 node8 = new Node3(8, 704, 474, icon8, packageArrivedLabel);
            Node3 node9 = new Node3(9, 826, 379, icon9, packageArrivedLabel);

            node1.addNeighbor(node2);
            node1.addNeighbor(node3);
    
            node2.addNeighbor(node1);
            node2.addNeighbor(node4);
            node2.addNeighbor(node6);
    
            node3.addNeighbor(node1);
            node2.addNeighbor(node4);
            node2.addNeighbor(node5);
    
            node4.addNeighbor(node2);
            node4.addNeighbor(node3);
            node4.addNeighbor(node7);
            node4.addNeighbor(node5);
    
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
    
            Package firstPackage = new Package(1, 9);
            node1.sendPackage(firstPackage);
        } else {
            Node4 node1 = new Node4(1, 46, 379, icon1, packageArrivedLabel);
            Node4 node2 = new Node4(2, 198, 204, icon2, packageArrivedLabel);
            Node4 node3 = new Node4(3, 199, 529, icon3, packageArrivedLabel);
            Node4 node4 = new Node4(4, 350, 379, icon4, packageArrivedLabel);
            Node4 node5 = new Node4(5, 554, 529, icon5, packageArrivedLabel);
            Node4 node6 = new Node4(6, 554, 204, icon6, packageArrivedLabel);
            Node4 node7 = new Node4(7, 554, 379, icon7, packageArrivedLabel);
            Node4 node8 = new Node4(8, 704, 474, icon8, packageArrivedLabel);
            Node4 node9 = new Node4(9, 826, 379, icon9, packageArrivedLabel);

            node1.addNeighbor(node2);
            node1.addNeighbor(node3);
    
            node2.addNeighbor(node1);
            node2.addNeighbor(node4);
            node2.addNeighbor(node6);
    
            node3.addNeighbor(node1);
            node2.addNeighbor(node4);
            node2.addNeighbor(node5);
    
            node4.addNeighbor(node2);
            node4.addNeighbor(node3);
            node4.addNeighbor(node7);
            node4.addNeighbor(node5);
    
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
    
            Package firstPackage = new Package(1, 9);
            // node1.sendPackage(firstPackage);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        icon1.setImage(Gallery.icon);
        icon2.setImage(Gallery.icon);
        icon3.setImage(Gallery.icon);
        icon4.setImage(Gallery.icon);
        icon5.setImage(Gallery.icon);
        icon6.setImage(Gallery.icon);
        icon7.setImage(Gallery.icon);
        icon8.setImage(Gallery.icon);
        icon9.setImage(Gallery.icon);

        icon1.setVisible(false);
        icon2.setVisible(false);
        icon3.setVisible(false);
        icon4.setVisible(false);
        icon5.setVisible(false);
        icon6.setVisible(false);
        icon7.setVisible(false);
        icon8.setVisible(false);
        icon9.setVisible(false);
    }
}
