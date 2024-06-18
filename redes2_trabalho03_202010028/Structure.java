/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 14/05/2024
* Ultima alteracao.: 23/05/2024
* Nome.............: Structure
* Funcao...........: Define the distance table triad of info
*************************************************************** */
public class Structure implements Cloneable  {
    private int nodeId; 
    private int distance; // Distance to the node
    private Node nodeToSendPackage; // Node to send the package that will arrived at the specified node

    // Constructor
    public Structure(int nodeId, int distance, Node nodeToSendPackage) {
        this.nodeId = nodeId;
        this.distance = distance;
        this.nodeToSendPackage = nodeToSendPackage;
    }

    /* ***************************************************************
    * Metodo: clone
    * Funcao: Return a clone of this class
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    protected Structure clone() {
        // This function will be used to send a clone of this function to avoid acessing the same memory space
        return new Structure(nodeId, distance, nodeToSendPackage);
    } // End clone

    /* ***************************************************************
    * Metodo: getNodeId
    * Funcao: Return the node id
    * Parametros: void
    * Retorno: The node id
    *************************************************************** */
    public int getNodeId() {
        return this.nodeId;
    } // End getNodeId

    /* ***************************************************************
    * Metodo: getDistance
    * Funcao: Return the node distance
    * Parametros: void
    * Retorno: The node distance
    *************************************************************** */
    public int getDistance() {
        return this.distance;
    } // End getDistance

    /* ***************************************************************
    * Metodo: setDistance
    * Funcao: Update node distance
    * Parametros: distance= New distace
    * Retorno: void
    *************************************************************** */
    public void setDistance(int distance) {
        this.distance = distance;
    } // End setDistance

    /* ***************************************************************
    * Metodo: getNodeToSendpackage
    * Funcao: Return the node which the package will be sent to arrive at destination
    * Parametros: void
    * Retorno: The node id
    *************************************************************** */
    public Node getNodeToSendPackage() {
        return this.nodeToSendPackage;
    } // End getNodeToSendPackage

    /* ***************************************************************
    * Metodo: setNodeToSendpackage
    * Funcao: Update the node which the package will be sent to arrive at destination
    * Parametros: distance= New node
    * Retorno: void
    *************************************************************** */
    public void setNodeToSendpackage(Node nodeToSendPackage) {
        this.nodeToSendPackage = nodeToSendPackage;
    } // End setNodeToSendPackage
} // End class Structure
