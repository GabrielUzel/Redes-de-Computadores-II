/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 14/05/2024
* Ultima alteracao.: //2024
* Nome.............: Node
* Funcao...........: Define the router algorithm
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

        distanceTable.add(new Structure(1, Integer.MAX_VALUE - 10, -1));
        distanceTable.add(new Structure(2, Integer.MAX_VALUE - 10, -1));
        distanceTable.add(new Structure(3, Integer.MAX_VALUE - 10, -1));
        distanceTable.add(new Structure(4, Integer.MAX_VALUE - 10, -1));
        distanceTable.add(new Structure(5, Integer.MAX_VALUE - 10, -1));
        distanceTable.add(new Structure(6, Integer.MAX_VALUE - 10, -1));
        distanceTable.add(new Structure(7, Integer.MAX_VALUE - 10, -1));
        distanceTable.add(new Structure(8, Integer.MAX_VALUE - 10, -1));
        distanceTable.add(new Structure(9, Integer.MAX_VALUE - 10, -1));

        for(Structure index : this.getDistanceTable()) {
            if(index.getNodeId() == id) index.setDistance(0);
        }
        // distanceTable.set
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

    public void addNeighborsDistance() {
        for(Map.Entry<Node, Integer> entry : neighbors.entrySet()) {
            for(Structure index : distanceTable) {
                if(index.getNodeId() == entry.getKey().getId()) {
                    index.setDistance(entry.getValue());
                    index.setNodeToSendpackage(entry.getKey().getId());
                }
            }
        }
    }

    public void updateDistanceTable(ArrayList<Structure> tableReceived, int senderId) {
        int indexAux = 0;

        for(Structure index : tableReceived) {
            if(distanceTable.get(indexAux).getDistance() > index.getDistance() + getNeighborDistanceById(senderId)) {
                distanceTable.get(indexAux).setDistance(index.getDistance() + getNeighborDistanceById(senderId));
                distanceTable.get(indexAux).setNodeToSendpackage(senderId);
            }

            indexAux++;
        }
    }

    public int getNeighborDistanceById(int id) {
        for(Map.Entry<Node, Integer> entry : neighbors.entrySet()) {
            if(entry.getKey().getId() == id) return entry.getValue();
        }

        return -1;
    }

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
} // End class Node
