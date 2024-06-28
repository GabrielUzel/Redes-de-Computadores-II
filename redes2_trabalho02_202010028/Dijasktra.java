/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 02/05/2024
* Ultima alteracao.: 12/05/2024
* Nome.............: Dijasktra
* Funcao...........: Implement the algorithm
*************************************************************** */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Dijasktra {
    private int senderId;
    private int receiverId;
    private ArrayList<Node> nodes = new ArrayList<>(); // List of all nodes
    private ArrayList<Integer> bestPath = new ArrayList<>(); // Stores integers that represents the besth path
    private ArrayList<Integer> visited = new ArrayList<>(Collections.nCopies(9, 0)); // Nodes visited
    private Node currentNode;

    // Constructor
    public Dijasktra(ArrayList<Node> nodes, int senderId, int receiverId) {
        this.nodes = nodes;
        this.senderId = senderId;
        this.receiverId = receiverId;
    } // End constructor

    /* ***************************************************************
    * Metodo: algorithm
    * Funcao: Execute the algorithm
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void algorithm() throws WrongInputException {
        if(senderId == receiverId) throw new WrongInputException(); // Sender and receiver must be diferent

        currentNode = nodes.get(senderId - 1); // Set the sender node as the current node
        currentNode.setDistance(0); // Distance 0 for sender node
        currentNode.setPredecessor(-1); // Sender node does not have a predecessor
        visited.set(senderId - 1, 1); // Sender node is visited

        while(currentNode.getId() != receiverId) { // Loop until find receiver
            for(Map.Entry<Node, Integer> entry : currentNode.getNeighbors().entrySet()) { // Get neighbors of the current node
                if(entry.getKey().getDistance() > currentNode.getDistance() + entry.getValue()) { // If neighbor node distance > current node distance + neighbor weight
                    entry.getKey().setDistance(currentNode.getDistance() + entry.getValue()); // Change neighbor distance from infinite to current node distance + neighbor weight
                    entry.getKey().setPredecessor(currentNode.getId()); // Set the neighbor predecessor this current node
                } // End if
            } // End for
    
            int lowestDistance = Integer.MAX_VALUE;
            int indexOfNode = -1;
            for(Node node : nodes) { // Loop all nodes to get an unvisited node that has the lowest distance
                if(visited.get(nodes.indexOf(node)) == 0) {
                    if(node.getDistance() < lowestDistance) {
                        lowestDistance = node.getDistance(); // Change the aux variable
                        indexOfNode = node.getId() - 1; // Change the index value to get the correct node
                    } // End if
                } // End if
            } // End for 
    
            currentNode = nodes.get(indexOfNode); // Current node is now the unvisited node that has the lowest distance
            visited.set(indexOfNode, 1); // Set the node as visited
        } // End while

        Node destination = nodes.get(receiverId - 1); // Get the destination node
        while(destination.getPredecessor() != -1) { // Loop until find the sender node
            bestPath.add(destination.getId()); // Add the node id in result array list
            destination = nodes.get(destination.getPredecessor() - 1);
        } // End while
        bestPath.add(senderId); // Add the sender inthe result array list

        Collections.reverse(bestPath); // Reverse best path list
    } // End algotithm

    /* ***************************************************************
    * Metodo: getBestPath
    * Funcao: return the array list with the best path
    * Parametros: void
    * Retorno: Array list of nodes id
    *************************************************************** */
    public ArrayList<Integer> getBestPath() {
        return this.bestPath;
    } // End getBestPath
} // End class Dijasktra
