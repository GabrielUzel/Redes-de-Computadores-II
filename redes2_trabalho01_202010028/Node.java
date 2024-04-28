import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Node extends Thread {
    private int id;
    private int x; // X cordinate in the window 
    private int y; // Y cordinate in the window
    private int option;
    CopyOnWriteArrayList<Package> packagesReceived = new CopyOnWriteArrayList<>();
    List<Node> neighbors = new ArrayList<>();
    private List<ImageView> icons = new ArrayList<>();
    private Label packageArrivedLabel;
    private Label packagesCounterLabel;

    public Node(int id, int x, int y, List<ImageView> icons, Label packageaArrivedLabel, Label packagesCounterLabel, int option) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.icons = icons;
        this.packageArrivedLabel = packageaArrivedLabel;
        this.packagesCounterLabel = packagesCounterLabel;
        this.option = option;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    @Override
    public void run() {
        try {
            while(true) {
                Platform.runLater(() -> {
                    changeCounterLabel();
                });

                Thread.sleep(2000);

                if(!packagesReceived.isEmpty()) {
                    for(Package currentPackage : packagesReceived) {
                        sendPackage(currentPackage);
                    }
                }
                Thread.sleep(4000);
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void receivePackage(Package newPackage) {
        if(newPackage.getReceiverId() == this.id) {
            Platform.runLater(() -> {
                changeArrivedlabel();
            });

            Package.setArrivedTrue();
        }

        packagesReceived.add(newPackage);
    }

    public void sendPackage(Package packageArrived) throws InterruptedException {
        switch(option) {
            case 1: {
                for(Node neighbor : neighbors) {
                    Package newPackage = new Package(this.getID(), packageArrived.getReceiverId());
                    newPackage.setSenderCoordinates(this.getCoordinates());
                    newPackage.setNeighborCoordinates(neighbor.getCoordinates());
                    newPackage.setIcon(getIcon(this.id, neighbor.id));
                    newPackage.start();
                    neighbor.receivePackage(newPackage);
                }
        
                packagesReceived.remove(packageArrived);
                break;
            }
            case 2: {
                for(Node neighbor : neighbors) {
                    if(packageArrived.getSenderId() != neighbor.getID()) {
                        Package newPackage = new Package(this.getID(), packageArrived.getReceiverId());
                        newPackage.setSenderCoordinates(this.getCoordinates());
                        newPackage.setNeighborCoordinates(neighbor.getCoordinates());
                        newPackage.setIcon(getIcon(this.id, neighbor.id));
                        newPackage.start();
                        neighbor.receivePackage(newPackage);
                    }
                }
        
                packagesReceived.remove(packageArrived);
                break;
            }
            case 3: {
                for(Node neighbor : neighbors) {
                    if(packageArrived.getSenderId() != neighbor.getID()) {
                        Package.reduceTTL();
                
                        if(Package.ttl > 0) {
                            Package newPackage = new Package(this.getID(), packageArrived.getReceiverId());
                            newPackage.setSenderCoordinates(this.getCoordinates());
                            newPackage.setNeighborCoordinates(neighbor.getCoordinates());
                            newPackage.setIcon(getIcon(this.id, neighbor.id));
                            newPackage.start();
                            neighbor.receivePackage(newPackage);
                        }
                    }
                }
        
                packagesReceived.remove(packageArrived);
                break;
            }
            case 4: {
                if(Package.arrived != true) {
                    for(Node neighbor : neighbors) {
                        if(packageArrived.getSenderId() != neighbor.getID()) {
                            Package.reduceTTL();
                    
                            if(Package.ttl > 0) {
                                Package newPackage = new Package(this.getID(), packageArrived.getReceiverId());
                                newPackage.setSenderCoordinates(this.getCoordinates());
                                newPackage.setNeighborCoordinates(neighbor.getCoordinates());
                                newPackage.setIcon(getIcon(this.id, neighbor.id));
                                newPackage.start();
                                neighbor.receivePackage(newPackage);
                            }
                        }
                    }
                }    
                packagesReceived.remove(packageArrived);
                break;
            }
        }
    }

    public int[] getCoordinates() {
        int[] array = {this.x, this.y};
        return array;
    }

    public void changeArrivedlabel() {
        this.packageArrivedLabel.setText("Chegou ao destino");
    }

    public void changeCounterLabel() {
        this.packagesCounterLabel.setText("Pacotes enviados: " + Package.packageCounter);
    }

    public int getID() {
        return this.id;
    }

    public void addPackage(Package firstPackage) {
        packagesReceived.add(firstPackage);
    }

    public ImageView getIcon(int senderId, int receiverId) {
        switch(senderId) {
            case 1: {
                if(receiverId == 2) return icons.get(0);
                return icons.get(1);
            }
            case 2: {
                if(receiverId == 1) return icons.get(2);
                else if(receiverId == 4) return icons.get(3);
                return icons.get(4);
            }
            case 3: {
                if(receiverId == 1) return icons.get(5);
                else if(receiverId == 4) return icons.get(6);
                return icons.get(7);
            }
            case 4: {
                if(receiverId == 2) return icons.get(8);
                else if(receiverId == 3) return icons.get(9);
                else if(receiverId == 5) return icons.get(10);
                return icons.get(11);
            }
            case 5: {
                if(receiverId == 3) return icons.get(12);
                else if(receiverId == 4) return icons.get(13);
                return icons.get(14);
            }
            case 6: {
                if(receiverId == 2) return icons.get(15);
                else if(receiverId == 7) return icons.get(16);
                return icons.get(17);
            }
            case 7: {
                if(receiverId == 4) return icons.get(18);
                else if(receiverId == 6) return icons.get(19);
                return icons.get(20);
            }
            case 8: {
                if(receiverId == 5) return icons.get(21);
                else if(receiverId == 7) return icons.get(22);
                return icons.get(23);
            }
            default: {
                if(receiverId == 6) return icons.get(24);
                return icons.get(25);
            }
        }
    }
}   
