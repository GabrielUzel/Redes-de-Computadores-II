public class Structure {
    private int nodeId;
    private int distance;
    private int nodeToSendPackage;

    public Structure(int nodeId, int distance, int nodeToSendPackage) {
        this.nodeId = nodeId;
        this.distance = distance;
        this.nodeToSendPackage = nodeToSendPackage;
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

    public int getNodeToSendPackage() {
        return this.nodeToSendPackage;
    }

    public void setNodeToSendpackage(int nodeToSendPackage) {
        this.nodeToSendPackage = nodeToSendPackage;
    }
}
