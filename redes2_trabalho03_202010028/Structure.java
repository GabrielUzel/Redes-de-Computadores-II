public class Structure implements Cloneable  {
    private int nodeId;
    private int distance;
    private Node nodeToSendPackage;

    public Structure(int nodeId, int distance, Node nodeToSendPackage) {
        this.nodeId = nodeId;
        this.distance = distance;
        this.nodeToSendPackage = nodeToSendPackage;
    }

    @Override
    protected Structure clone() {
        return new Structure(nodeId, distance, nodeToSendPackage);
    }

    public int getNodeId() {
        return this.nodeId;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Node getNodeToSendPackage() {
        return this.nodeToSendPackage;
    }

    public void setNodeToSendpackage(Node nodeToSendPackage) {
        this.nodeToSendPackage = nodeToSendPackage;
    }
}
