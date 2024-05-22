import java.util.ArrayList;
import java.util.Map;

public class distanceVectorAlgorithm {
    private ArrayList<Node> nodes = new ArrayList<>();

    public distanceVectorAlgorithm(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public void algorithm() {
        for(Node node : nodes) {
            node.addNeighborsDistance(nodes);
        }

        while(!verifyAllTablesAreCompleted()) {
            for(Node node : nodes) {
                for(Map.Entry<Node, Integer> entry : node.getNeighbors().entrySet()) {
                    if(nodes.contains(entry.getKey())) {
                        node.updateDistanceTable(entry.getKey().getDistanceTable(), entry.getKey());
                    }
                }
            }
        }

        printAllDistanceTables();
    }

    public void printAllDistanceTables() {
        for(Node node : nodes) {
            for(Structure index : node.getDistanceTable()) {
                if(index.getNodeToSendPackage() == null) {
                    System.out.println(index.getNodeId() + " " + index.getDistance() + " null");
                } else {
                    System.out.println(index.getNodeId() + " " + index.getDistance() + " " + index.getNodeToSendPackage().getId());
                }
            }

            System.out.println("\n");
        }
    }

    public boolean verifyAllTablesAreCompleted() {
        for(Node node : nodes) {
            for(Structure index : node.getDistanceTable()) {
                if(index.getDistance() == Integer.MAX_VALUE - 10 && nodeExistsInCurrentUpdate(index.getNodeId())) return false;
            }
        }

        return true;
    }

    public boolean nodeExistsInCurrentUpdate(int id) {
        for(Node node : nodes) {
            if(node.getId() == id) return true;
        }

        return false;
    }
}
