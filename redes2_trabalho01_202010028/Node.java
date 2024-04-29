/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 20/04/2024
* Ultima alteracao.: 29/04/2024
* Nome.............: Node
* Funcao...........: Define the router algorithm
*************************************************************** */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Node extends Thread {
    private int id; // Router id
    private int x; // X cordinate in the window 
    private int y; // Y cordinate in the window
    private int option; // Option that will be used
    CopyOnWriteArrayList<Package> packagesReceived = new CopyOnWriteArrayList<>(); // Buffer with packages 
    List<Node> neighbors = new ArrayList<>(); // Neighbors of the current router
    private List<ImageView> icons = new ArrayList<>(); // Array with the image views
    private Label packageArrivedLabel; // Label to show if the package arrived on destination
    private Label packagesCounterLabel; // Label to show how many packages were created

    // Constructor
    public Node(int id, int x, int y, List<ImageView> icons, Label packageaArrivedLabel, Label packagesCounterLabel, int option) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.icons = icons;
        this.packageArrivedLabel = packageaArrivedLabel;
        this.packagesCounterLabel = packagesCounterLabel;
        this.option = option;
    } // End constructor

    /* ***************************************************************
    * Metodo: addNeighbor
    * Funcao: Define neighbors to current router
    * Parametros: neighbor= The new neighbor to be added
    * Retorno: void
    *************************************************************** */
    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    } // End addNeighbor

    /* ***************************************************************
    * Metodo: run
    * Funcao: Thread start
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        try {
            while(true) { // Each router executes indefinitely
                Platform.runLater(() -> {
                    changeCounterLabel(); // Update the package counter label
                });

                Thread.sleep(2000); // Sleeps

                if(!packagesReceived.isEmpty()) { // verify if there is a package in the buffer
                    for(Package currentPackage : packagesReceived) {
                        sendPackage(currentPackage); // Send the packages
                    } // End for
                } // End if

                Thread.sleep(4000); // Sleeps
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    } // End run

    /* ***************************************************************
    * Metodo: receivePackage
    * Funcao: Add a new package to buffer
    * Parametros: newPackage= The package received by the router
    * Retorno: void
    *************************************************************** */
    public void receivePackage(Package newPackage) {
        if(newPackage.getReceiverId() == this.id) { // Verify if the current router is the destination
            Platform.runLater(() -> {
                changeArrivedlabel(); // Change label to show that the package arrived at the final destination
            });

            Package.setArrivedTrue(); // Change arrived variable
        } // End if

        packagesReceived.add(newPackage); // Add package to buffer
    } // End receivePackage

    /* ***************************************************************
    * Metodo: sendPackage
    * Funcao: Send the package to neighbors
    * Parametros: packageArrived= The package received by the router
    * Retorno: void
    *************************************************************** */
    public void sendPackage(Package packageArrived) throws InterruptedException {
        switch(option) { // Verify the option value
            case 1: { // Send to all neighbors
                for(Node neighbor : neighbors) {
                    Package newPackage = new Package(this.getID(), packageArrived.getReceiverId()); // Create a new package
                    newPackage.setSenderCoordinates(this.getCoordinates()); // Set the sender coordinates to this router coordinates
                    newPackage.setNeighborCoordinates(neighbor.getCoordinates()); // Set the neighbor coordinates to each neighbor coordinates
                    newPackage.setIcon(getIcon(this.id, neighbor.id)); // Set the icon image view by the search fuction getIcon
                    newPackage.start(); // Start Thread
                    neighbor.receivePackage(newPackage); // Add the package to neighbor's buffer
                } // End for
        
                packagesReceived.remove(packageArrived); // Remove package from buffer
                break;
            }
            case 2: { // Send to all neighbors except the one who sent the package to the current router
                for(Node neighbor : neighbors) {
                    if(packageArrived.getSenderId() != neighbor.getID()) { // Verify if the packages's sender id to not send the package
                        Package newPackage = new Package(this.getID(), packageArrived.getReceiverId()); // Create a new package
                        newPackage.setSenderCoordinates(this.getCoordinates()); // Set the sender coordinates to this router coordinates
                        newPackage.setNeighborCoordinates(neighbor.getCoordinates()); // Set the neighbor coordinates to each neighbor coordinates
                        newPackage.setIcon(getIcon(this.id, neighbor.id)); // Set the icon image view by the search fuction getIcon
                        newPackage.start(); // Start Thread
                        neighbor.receivePackage(newPackage); // Add the package to neighbor's buffer
                    } // End if
                } // End for
        
                packagesReceived.remove(packageArrived); // Remove package from buffer
                break;
            } // End case 2
            case 3: { // Verify the ttl value
                Package.reduceTTL(); // Reduce the package ttl

                if(Package.ttl > 0) { // Verify if the package os still "alive"
                    for(Node neighbor : neighbors) {
                        if(packageArrived.getSenderId() != neighbor.getID()) {
                            Package newPackage = new Package(this.getID(), packageArrived.getReceiverId()); // Create a new package
                            newPackage.setSenderCoordinates(this.getCoordinates()); // Set the sender coordinates to this router coordinates
                            newPackage.setNeighborCoordinates(neighbor.getCoordinates()); // Set the neighbor coordinates to each neighbor coordinates
                            newPackage.setIcon(getIcon(this.id, neighbor.id)); // Set the icon image view by the search fuction getIcon
                            newPackage.start(); // Start Thread
                            neighbor.receivePackage(newPackage); // Add the package to neighbor's buffer
                        } // End if
                    } // End for
                } // End if

                packagesReceived.remove(packageArrived); // Remove package from buffer
                break;
            } // End case 3
            case 4: { // Verify the arrived variable
                if(Package.arrived != true) { // Verify if the package arrived at final destination
                    Package.reduceTTL(); // Reduce the package ttl

                    if(Package.ttl > 0) { // Verify if the package os still "alive"
                        for(Node neighbor : neighbors) {
                            if(packageArrived.getSenderId() != neighbor.getID()) {
                                Package newPackage = new Package(this.getID(), packageArrived.getReceiverId()); // Create a new package
                                newPackage.setSenderCoordinates(this.getCoordinates()); // Set the sender coordinates to this router coordinates
                                newPackage.setNeighborCoordinates(neighbor.getCoordinates()); // Set the neighbor coordinates to each neighbor coordinates
                                newPackage.setIcon(getIcon(this.id, neighbor.id)); // Set the icon image view by the search fuction getIcon
                                newPackage.start(); // Start Thread
                                neighbor.receivePackage(newPackage); // Add the package to neighbor's buffer
                            } // End if
                        } // End for
                    } // End if
                } // End if

                packagesReceived.remove(packageArrived); // Remove package from buffer
                break;
            } // End case 4
        } // End switch
    } // End sendPackage

    /* ***************************************************************
    * Metodo: getCoordinates
    * Funcao: Return current router x and y coordinates
    * Parametros: void
    * Retorno: Array with the coordinates
    *************************************************************** */
    public int[] getCoordinates() {
        int[] array = {this.x, this.y};
        return array;
    } // End getCoordinates

    /* ***************************************************************
    * Metodo: changeArrivedLabel
    * Funcao: Change the label to show that the package arrived at final destination
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void changeArrivedlabel() {
        this.packageArrivedLabel.setText("Chegou ao destino");
    } // End changeArrivedLabel

    /* ***************************************************************
    * Metodo: changeCounterLabel
    * Funcao: Change the label to show how many packages were sent
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void changeCounterLabel() {
        this.packagesCounterLabel.setText("Pacotes enviados: " + Package.packageCounter);
    } // End changeCounterLabel

    /* ***************************************************************
    * Metodo: getId
    * Funcao: Get the router id
    * Parametros: void
    * Retorno: Router id
    *************************************************************** */
    public int getID() {
        return this.id;
    } // End getID

    /* ***************************************************************
    * Metodo: addPackage
    * Funcao: Manually add a package to buffer
    * Parametros: firstPackage= The package to be added to buffer
    * Retorno: void
    *************************************************************** */
    public void addPackage(Package firstPackage) {
        packagesReceived.add(firstPackage);
    } // End addPackage

    /* ***************************************************************
    * Metodo: getIcon
    * Funcao: Get the correct image view to be animated
    * Parametros: senderId= The sender number & receiverId= The receiver number
    * Retorno: The correct imageview
    *************************************************************** */
    public ImageView getIcon(int senderId, int receiverId) {
        // This switch verify the sender and receiver id and return the image view that will translate from
        // the sender coordinates to the receiver coordinates
        switch(senderId) {
            case 1: {
                if(receiverId == 2) return icons.get(0); // Image view from node 1 to node 2
                return icons.get(1); // Image view from node 1 to node 3
            } // End case 1
            case 2: {
                if(receiverId == 1) return icons.get(2); // Image view from node 2 to node 1
                else if(receiverId == 4) return icons.get(3); // Image view from node 2 to node 4
                return icons.get(4); // Image view from node 2 to node 6
            } // End case 2
            case 3: {
                if(receiverId == 1) return icons.get(5); // Image view from node 3 to node 1
                else if(receiverId == 4) return icons.get(6); // Image view from node 3 to node 4
                return icons.get(7); // Image view from node 3 to node 5
            } // End case 3
            case 4: {
                if(receiverId == 2) return icons.get(8); // Image view from node 4 to node 2
                else if(receiverId == 3) return icons.get(9); // Image view from node 4 to node 3
                else if(receiverId == 5) return icons.get(10); // Image view from node 4 to node 5
                return icons.get(11); // Image view from node 4 to node 7
            } // End case 4
            case 5: {
                if(receiverId == 3) return icons.get(12); // Image view from node 5 to node 3
                else if(receiverId == 4) return icons.get(13); // Image view from node 5 to node 4
                return icons.get(14); // Image view from node 5 to node 8
            } // End case 5
            case 6: {
                if(receiverId == 2) return icons.get(15); // Image view from node 6 to node 2
                else if(receiverId == 7) return icons.get(16); // Image view from node 6 to node 7
                return icons.get(17); // Image view from node 6 to node 9
            } // End case 6
            case 7: {
                if(receiverId == 4) return icons.get(18); // Image view from node 7 to node 4
                else if(receiverId == 6) return icons.get(19); // Image view from node 7 to node 6
                return icons.get(20); // Image view from node 7 to node 8
            } // End case 7
            case 8: {
                if(receiverId == 5) return icons.get(21); // Image view from node 8 to node 5
                else if(receiverId == 7) return icons.get(22); // Image view from node 8 to node 7
                return icons.get(23); // Image view from node 8 to node 9
            } // End case 8
            default: {
                if(receiverId == 6) return icons.get(24); // Image view from node 9 to node 6
                return icons.get(25); // Image view from node 9 to node 8
            } // End default
        } // End switch
    } // End getIcon
} // End class Node
