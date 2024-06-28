/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 14/05/2024
* Ultima alteracao.: 23/05/2024
* Nome.............: Node
* Funcao...........: Define router functions
*************************************************************** */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node {
    private int id; // Router id
    private HashMap<Node, Integer> neighbors = new HashMap<>(); // Neighbors of the current router
    private ArrayList<Structure> distanceTable = new ArrayList<>();
 
    // Constructor
    public Node(int id) {
        this.id = id;

        distanceTable.add(new Structure(1, Integer.MAX_VALUE - 10, null));
        distanceTable.add(new Structure(2, Integer.MAX_VALUE - 10, null));
        distanceTable.add(new Structure(3, Integer.MAX_VALUE - 10, null));
        distanceTable.add(new Structure(4, Integer.MAX_VALUE - 10, null));
        distanceTable.add(new Structure(5, Integer.MAX_VALUE - 10, null));
        distanceTable.add(new Structure(6, Integer.MAX_VALUE - 10, null));
        distanceTable.add(new Structure(7, Integer.MAX_VALUE - 10, null));
        distanceTable.add(new Structure(8, Integer.MAX_VALUE - 10, null));
        distanceTable.add(new Structure(9, Integer.MAX_VALUE - 10, null));

        // Set first node distance as 0  
        for(Structure index : this.getDistanceTable()) {
            if(index.getNodeId() == id) index.setDistance(0);
        } // End for
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
    * Metodo: addNeighborsDistance
    * Funcao: Update the distance table with neighbors distance
    * Parametros: nodes= ArrayList of active nodes
    * Retorno: void
    *************************************************************** */
    public void addNeighborsDistance() {
        for(Map.Entry<Node, Integer> entry : neighbors.entrySet()) { // Iterate over all neighbors
            for(Structure index : distanceTable) { // Iterate over the distance table
                if(index.getNodeId() == entry.getKey().getId()) { // Get the neighbor in the distance table
                    // Update the distance table
                    index.setDistance(entry.getValue()); 
                    index.setNodeToSendpackage(entry.getKey());
                } // End if
            } // End for
        } // End for
    } // End addNeighborsDistance

    /* ***************************************************************
    * Metodo: updateDistanceTable
    * Funcao: Update the distance table from a received table
    * Parametros: tableReceived= A table sent by a neighbor, sender= The neighbor who sent the table
    * Retorno: void
    *************************************************************** */
    public void updateDistanceTable() {
        for(Map.Entry<Node, Integer> entry : getNeighbors().entrySet()) { // Iterate over all neighbors
            int indexAux = 0; // Auxiliar index

            for(Structure index : entry.getKey().getDistanceTable()) { // Iterate over neighbor distance table
                // Verify if, in the distance table received, there is a shorter path to each node
                if(index.getDistance() + entry.getValue() < distanceTable.get(indexAux).getDistance()) {
                    // Update the node distance table
                    distanceTable.get(indexAux).setDistance(index.getDistance() + entry.getValue());
                    distanceTable.get(indexAux).setNodeToSendpackage(entry.getKey());
                } // End if

                indexAux++;
            } // End for
        } // End for
    } // End updateDistanceTable

    /* ***************************************************************
    * Metodo: getNeighborDistanceById
    * Funcao: Return a node distance in the graph given its id
    * Parametros: id= The desiried node id
    * Retorno: A distance
    *************************************************************** */
    public int getNeighborDistanceById(int id) {
        for(Map.Entry<Node, Integer> entry : neighbors.entrySet()) {
            if(entry.getKey().getId() == id) return entry.getValue();
        } // End for 

        return -1;
    } // End getNeighborDistanceById

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
    * Metodo: getNeighbors
    * Funcao: Get current node neighbors
    * Parametros: void
    * Retorno: Hashmap with the neighboirs and edges weights
    *************************************************************** */
    public HashMap<Node, Integer> getNeighbors() {
        return this.neighbors;
    } // End getNeighbor

    /* ***************************************************************
    * Metodo: distanceTable
    * Funcao: Get the distance table of the node that will help find the path to source
    * Parametros: void
    * Retorno: Predecessor id
    *************************************************************** */
    public ArrayList<Structure> getDistanceTable() {
        return this.distanceTable;
    } // End getPredecessor

    /* ***************************************************************
    * Metodo: distanceTable
    * Funcao: Update the distance table
    * Parametros: dsitanceTable= A new distance table
    * Retorno: void
    *************************************************************** */
    public void setDistanceTable(ArrayList<Structure> distanceTable) {
        this.distanceTable = distanceTable;
    } // End setDistanceTable
} // End class Node
