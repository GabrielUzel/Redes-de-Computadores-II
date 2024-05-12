/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 02/05/2024
* Ultima alteracao.: 12/05/2024
* Nome.............: Node
* Funcao...........: Define the router algorithm
*************************************************************** */
import java.util.HashMap;

public class Node {
    private int id; // Router id
    private int predecessor = 0;
    HashMap<Node, Integer> neighbors = new HashMap<>(); // Neighbors of the current router
    private Integer distance = Integer.MAX_VALUE; // Initial infinite distance for destination
 
    // Constructor
    public Node(int id) {
        this.id = id;
    } // End constructor

    /* ***************************************************************
    * Metodo: addNeighbor
    * Funcao: Define neighbors to current router
    * Parametros: neighbor= The new neighbor to be added
    * Retorno: void
    *************************************************************** */
    public void addNeighbor(Node neighbor, int weight) {
        neighbors.put(neighbor, weight);
    } // End addNeighbor

    /* ***************************************************************
    * Metodo: getId
    * Funcao: Get node id
    * Parametros: void
    * Retorno: Current id
    *************************************************************** */
    public int getId() {
        return this.id;
    } // End getId

    /* ***************************************************************
    * Metodo: getDistance
    * Funcao: Get current distance of the current node
    * Parametros: void
    * Retorno: Current distance to destination
    *************************************************************** */
    public int getDistance() {
        return this.distance;
    } // end getDistance

    /* ***************************************************************
    * Metodo: setDistance
    * Funcao: Set a new distance for this node
    * Parametros: newDistance= New distance value
    * Retorno: void
    *************************************************************** */
    public void setDistance(Integer newDistance) {
        this.distance = newDistance;
    } // End setDistance

    /* ***************************************************************
    * Metodo: getNeighbors
    * Funcao: Get current node neighbors
    * Parametros: void
    * Retorno: Hashmap with the neighboirs and edges weights
    *************************************************************** */
    public HashMap<Node, Integer> getNeighbors() {
        return this.neighbors;
    } // End getNeighbor

    /* ***************************************************************
    * Metodo: setPredecessor
    * Funcao: Set the predecessor of the node that will help find the path to source
    * Parametros: newPredecesssor= id of predecessor node
    * Retorno: void
    *************************************************************** */
    public void setPredecessor(int newPredecessor) {
        this.predecessor = newPredecessor;
    } // End setPredecessor

    /* ***************************************************************
    * Metodo: getPredecessor
    * Funcao: Get the predecessor of the node that will help find the path to source
    * Parametros: void
    * Retorno: Predecessor id
    *************************************************************** */
    public int getPredecessor() {
        return this.predecessor;
    } // End getPredecessor
} // End class Node
