/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 14/05/2024
* Ultima alteracao.: 22/05/2024
* Nome.............: DistanceVectorAlgorithm
* Funcao...........: Start the algorithm
*************************************************************** */
import java.util.ArrayList;
import java.util.Map;

public class DistanceVectorAlgorithm {
    private ArrayList<Node> nodes = new ArrayList<>(); // List of active nodes

    // Constructor
    public DistanceVectorAlgorithm(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    /* ***************************************************************
    * Metodo: algorithm
    * Funcao: Execute the algorithm
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void algorithm() {
        // Update each node distance table with its neighbors distance
        for(Node node : nodes) {
            node.addNeighborsDistance(nodes);
        }

        // Update each node distance table with its neighbors distance table until all tables are completed
        while(!verifyAllTablesAreCompleted()) {
            for(Node node : nodes) {
                for(Map.Entry<Node, Integer> entry : node.getNeighbors().entrySet()) { // Iterate over neighbors list
                    if(nodes.contains(entry.getKey())) { // Verify if the neighbor is a active node
                        node.updateDistanceTable(entry.getKey().getDistanceTable(), entry.getKey());
                    } // End if
                } // End for
            } // End for
        } // End while
    } // End algorithm 

    /* ***************************************************************
    * Metodo: verifyAllTablesAreCompleted
    * Funcao: Verify all distance tables to stop the algorithm
    * Parametros: void
    * Retorno: A boolean
    *************************************************************** */
    public boolean verifyAllTablesAreCompleted() {
        for(Node node : nodes) { // Iterate nodes list
            for(Structure index : node.getDistanceTable()) { // Iterate over current node distance table
                // Verify if there is a infinite ditance on a active node
                if(index.getDistance() == Integer.MAX_VALUE - 10 && nodeExistsInCurrentUpdate(index.getNodeId())) return false;
            }
        }

        return true;
    } // End verifyAllTablesAreCompleted

    /* ***************************************************************
    * Metodo: nodeExistsInCurrentUpdate
    * Funcao: Verify if a node exists in the nodes list given its id
    * Parametros: id= Node id
    * Retorno: A boolean
    *************************************************************** */
    public boolean nodeExistsInCurrentUpdate(int id) {
        for(Node node : nodes) {
            if(node.getId() == id) return true;
        }

        return false;
    } // End nodeExistsInCurrentUpdate
} // End class DistanceVectorAlgorithm
