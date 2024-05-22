/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 14/05/2024
* Ultima alteracao.: 22/05/2024
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

        for(Structure index : this.getDistanceTable()) {
            if(index.getNodeId() == id) index.setDistance(0);
        }
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
    public void addNeighborsDistance(ArrayList<Node> nodes) {
        for(Map.Entry<Node, Integer> entry : neighbors.entrySet()) { // Iterate over all neighbors
            if(nodeExistsInArray(entry.getKey().getId(), nodes)) { // Verify if the neighbor is a active node
                for(Structure index : distanceTable) { // Iterate over the distance table
                    if(index.getNodeId() == entry.getKey().getId()) { // Get the neighbor in the distance table
                        // Update the distance table
                        index.setDistance(entry.getValue()); 
                        index.setNodeToSendpackage(entry.getKey());
                    } // End if
                } // End for
            } // End if
        } // End for
    } // End addNeighborsDistance

    /* ***************************************************************
    * Metodo: updateDistanceTable
    * Funcao: Update the distance table from a received table
    * Parametros: tableReceived= A table sent by a neighbor, sender= The neighbor who sent the table
    * Retorno: void
    *************************************************************** */
    public void updateDistanceTable(ArrayList<Structure> tableReceived, Node sender) {
        int indexAux = 0; // Assistant index

        for(Structure index : tableReceived) { // Iterate over the distance table 
            // Verify if, in the distance table received there is a shorter path to each node
            if(distanceTable.get(indexAux).getDistance() > index.getDistance() + getNeighborDistanceById(sender.getId())) {
                // Update the node distance table
                distanceTable.get(indexAux).setDistance(index.getDistance() + getNeighborDistanceById(sender.getId()));
                distanceTable.get(indexAux).setNodeToSendpackage(sender);
            } // End if

            indexAux++;
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
    } // End getNeighborDistanceByID

    /* ***************************************************************
    * Metodo: nodeExistsInArray
    * Funcao: Given an id, search for a node with this id
    * Parametros: id= The desiried node id, array= Nodes arrayList
    * Retorno: A boolean
    *************************************************************** */
    public boolean nodeExistsInArray(int id, ArrayList<Node> array) {
        for(Node node : array) {
            if(node.getId() == id) return true;
        } // End for

        return false;
    } // End nodeExistsInArray

    /* ***************************************************************
    * Metodo: correctDistanceTable
    * Funcao: Update the distance table with the correct distance of this node index
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void correctDistanceTable() {
        for(Structure index : this.getDistanceTable()) { // Loop through distance table to find this node index
            if(index.getNodeId() == this.id) {
                // Update the distance table to correct it
                index.setDistance(0);
                index.setNodeToSendpackage(null);
            } // End if
        } // End for
    } // End correctDistanceTable

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
