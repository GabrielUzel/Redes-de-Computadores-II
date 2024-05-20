import java.util.ArrayList;

public class distanceVectorAlgorithm {
    private ArrayList<Node> nodes = new ArrayList<>();

    public distanceVectorAlgorithm(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public void algorithm() {
        for(Node node : nodes) {
            node.addNeighborsDistance();
        }

        printAllDistanceTables();
    }

    public void printAllDistanceTables() {
        for(Node node : nodes) {
            for(Structure index : node.getDistanceTable()) {
                System.out.println(index.getNodeId() + " " + index.getDistance() + " " + index.getNodeToSendPackage());
            }

            System.out.println("\n");
        }
    }
}
