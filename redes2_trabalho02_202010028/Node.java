/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 02/05/2024
* Ultima alteracao.: //
* Nome.............: Node
* Funcao...........: Define the router algorithm
*************************************************************** */
import java.util.HashMap;

public class Node {
    private int id; // Router id
    private int predecessor = 0;
    private int x; // X cordinate in the window 
    private int y; // Y cordinate in the window
    HashMap<Node, Integer> neighbors = new HashMap<>(); // Neighbors of the current router
    private Integer distance = Integer.MAX_VALUE; // Initial infinite distance for destination
 
    // Constructor
    public Node(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
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

    public int getId() {
        return this.id;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(Integer newDistance) {
        this.distance = newDistance;
    }

    public HashMap<Node, Integer> getNeighbors() {
        return this.neighbors;
    }

    public void setPredecessor(int newPredecessor) {
        this.predecessor = newPredecessor;
    }

    public int getPredecessor() {
        return this.predecessor;
    }
} // End class Node
