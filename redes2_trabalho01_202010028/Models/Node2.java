package Models;

import java.util.*;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Node2 {
    private int id;
    private int x; // X cordinate in the window 
    private int y; // Y cordinate in the window
    List<Node2> neighbors = new ArrayList<>();
    private ImageView icon;
    private Label packageArrivedLabel;

    public Node2(int id, int x, int y, ImageView icon, Label packageaArrivedLabel) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.icon = icon;
        this.packageArrivedLabel = packageaArrivedLabel;
    }

    public Node2() {

    }

    public void addNeighbor(Node2 neighbor) {
        neighbors.add(neighbor);
    }

    public void receivePackage(Package newPackage) {
        try {
            if(newPackage.getReceiverId() == this.id) {
                changelabel();
            }
    
            sendPackage(newPackage);
        } catch(InterruptedException e) {
            System.out.println("ERROR");
        }
    }

    public void sendPackage(Package packageReceived) throws InterruptedException {
        for(Node2 neighbor : neighbors) {
            if(packageReceived.getSenderId() != neighbor.getId()) {
                Package newPackage = new Package(this.id, packageReceived.getReceiverId());
                newPackage.setSenderCoordinates(this.getCoordinates());
                newPackage.setNeighborCoordinates(neighbor.getCoordinates());
                newPackage.setIcon(this.icon);
                newPackage.start();
                neighbor.receivePackage(packageReceived);
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
