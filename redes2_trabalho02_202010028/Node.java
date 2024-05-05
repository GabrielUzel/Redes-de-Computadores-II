/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 02/05/2024
* Ultima alteracao.: //
* Nome.............: Node
* Funcao...........: Define the router algorithm
*************************************************************** */
import java.util.ArrayList;
import java.util.List;

public class Node extends Thread {
    private int id; // Router id
    private int x; // X cordinate in the window 
    private int y; // Y cordinate in the window
    List<Node> neighbors = new ArrayList<>(); // Neighbors of the current router
 
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

    } // End run
} // End class Node
