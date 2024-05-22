import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class ShowPath {
    private int senderId;
    private int receiverId;
    private ArrayList<Node> nodes;
    private ArrayList<Integer> bestPath = new ArrayList<>();
    private ArrayList<ImageView> edges;

    public ShowPath(int senderId, int receiverId, ArrayList<Node> nodes, ArrayList<ImageView> edges) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.nodes = nodes;
        this.edges = edges;
    }

    public void algorithm() throws WrongInputException {
        if(senderId == receiverId) throw new WrongInputException("Remetente e destinatário devem ser diferentes"); // Sender and receiver must be diferent

        Node senderNode = null;
        Node receiverNode = null;

        for(Node node : nodes) {
            if(node.getId() == senderId) senderNode = node;
            if(node.getId() == receiverId) receiverNode = node;
        }

        if(senderNode == null || receiverNode == null) throw new WrongInputException("É impossível enviar esse pacote");

        int currentNode = senderId;
        while(currentNode != receiverId) {
            bestPath.add(currentNode);

            for(Structure index : senderNode.getDistanceTable()) {
                if(receiverNode.getId() == index.getNodeId()) {
                    senderNode = index.getNodeToSendPackage();
                    currentNode = senderNode.getId();
                }
            }
        }

        bestPath.add(receiverId);

        int j = 1; // Second element of the two by two pair edge

        for(int i = 0; i < bestPath.size(); i++) {
            if(j == bestPath.size()) break; // When arrives the destination, exit for loop

            getEdge(bestPath.get(i), bestPath.get(j)).setVisible(false); // Set the current edge image view visible
            j++;
        } // End for
    }

    /* ***************************************************************
    * Metodo: getEdge
    * Funcao: Get the edge image view given the nodes
    * Parametros: nodes number
    * Retorno: The correct image view
    *************************************************************** */
    public ImageView getEdge(int node1, int node2) {
        if((node1 == 1 && node2 == 2) || (node1 == 2 && node2 == 1)) return edges.get(0);
        if((node1 == 1 && node2 == 3) || (node1 == 3 && node2 == 1)) return edges.get(1);
        if((node1 == 2 && node2 == 4) || (node1 == 4 && node2 == 2)) return edges.get(2);
        if((node1 == 2 && node2 == 6) || (node1 == 6 && node2 == 2)) return edges.get(3);
        if((node1 == 3 && node2 == 4) || (node1 == 4 && node2 == 3)) return edges.get(4);
        if((node1 == 3 && node2 == 5) || (node1 == 5 && node2 == 3)) return edges.get(5);
        if((node1 == 4 && node2 == 5) || (node1 == 5 && node2 == 4)) return edges.get(6);
        if((node1 == 4 && node2 == 7) || (node1 == 7 && node2 == 4)) return edges.get(7);
        if((node1 == 5 && node2 == 8) || (node1 == 8 && node2 == 5)) return edges.get(8);
        if((node1 == 6 && node2 == 7) || (node1 == 7 && node2 == 6)) return edges.get(9);
        if((node1 == 6 && node2 == 9) || (node1 == 9 && node2 == 6)) return edges.get(10);
        if((node1 == 7 && node2 == 8) || (node1 == 8 && node2 == 7)) return edges.get(11);

        return edges.get(12);
    } // End getEdge
}
