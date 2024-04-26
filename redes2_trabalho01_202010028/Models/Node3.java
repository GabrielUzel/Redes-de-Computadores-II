package Models;

import java.util.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Node3 {
    private int id;
    private int x; // X cordinate in the window 
    private int y; // Y cordinate in the window
    List<Node3> neighbors = new ArrayList<>();
    private ImageView icon;
    private Label packageArrivedLabel;

    public Node3(int id, int x, int y, ImageView icon, Label packageaArrivedLabel) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.icon = icon;
        this.packageArrivedLabel = packageaArrivedLabel;
    }

    public Node3() {

    }

    public void addNeighbor(Node3 neighbor) {
        neighbors.add(neighbor);
    }

    public void receivePackage(Package newPackage) {
        if(newPackage.getReceiverId() == this.id) {
            changelabel();
        }

        sendPackage(newPackage);
    }

    public void sendPackage(Package packageReceived) {
        for(Node3 neighbor : neighbors) {
            if(packageReceived.getSenderId() != neighbor.getId()) {
                Package.ttl--;
                
                if(Package.ttl != 0) {
                    Package newPackage = new Package(this.id, packageReceived.getReceiverId());
                    newPackage.setSenderCoordinates(this.getCoordinates());
                    newPackage.setNeighborCoordinates(neighbor.getCoordinates());
                    newPackage.setIcon(this.icon);
                    newPackage.start();
                    neighbor.receivePackage(packageReceived);
                } 
            }
        }
    }

    public int[] getCoordinates() {
        int[] array = {this.x, this.y};
        return array;
    }

    public void changelabel() {
        this.packageArrivedLabel.setText("Chegou ao destino");
    }

    public int getId() {
        return this.id;
    }
}   
