import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Dijasktra {
    private int senderId;
    private int receiverId;
    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Integer> bestPath = new ArrayList<>();
    private ArrayList<Integer> visited = new ArrayList<>(Collections.nCopies(9, 0));
    private Node currentNode;

    public Dijasktra(ArrayList<Node> nodes, int senderId, int receiverId) {
        this.nodes = nodes;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public void algorithm() throws WrongInputException {
        if(senderId == receiverId) throw new WrongInputException();

        currentNode = nodes.get(senderId - 1);
        currentNode.setDistance(0);
        currentNode.setPredecessor(-1);
        visited.set(senderId - 1, 1);

        while(currentNode.getId() != receiverId) {
            for(Map.Entry<Node, Integer> entry : currentNode.getNeighbors().entrySet()) {
                if(entry.getKey().getDistance() > currentNode.getDistance() + entry.getValue()) {
                    entry.getKey().setDistance(currentNode.getDistance() + entry.getValue());
                    entry.getKey().setPredecessor(currentNode.getId());
                }
            }
    
            int lowestDistance = Integer.MAX_VALUE;
            int indexOfNode = -1;
            for(Node node : nodes) {
                if(visited.get(nodes.indexOf(node)) == 0) {
                    if(node.getDistance() < lowestDistance) {
                        lowestDistance = node.getDistance();
                        indexOfNode = node.getId() - 1;
                    }
                }
            }
    
            currentNode = nodes.get(indexOfNode);
            visited.set(indexOfNode, 1);
        }

        Node destination = nodes.get(receiverId - 1);
        while(destination.getPredecessor() != -1) {
            bestPath.add(destination.getId());
            destination = nodes.get(destination.getPredecessor() - 1);
        }
        bestPath.add(senderId);

        Collections.reverse(bestPath);
    }

    public ArrayList<Integer> getBestPath() {
        return this.bestPath;
    }
}
