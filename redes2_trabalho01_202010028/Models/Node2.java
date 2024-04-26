package Models;

import java.util.*;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Node2 extends Thread {
    private int id;
    private int x; // X cordinate in the window 
    private int y; // Y cordinate in the window
    private Package firstPackage;
    List<Package> packagesReceived = new ArrayList<>();
    List<Node2> neighbors = new ArrayList<>();
    private ImageView icon;
    private Label packageArrivedLabel;
    private Label packagesCounterLabel;

    public Node2(int id, int x, int y, ImageView icon, Label packageaArrivedLabel, Label packagesCounterLabel) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.icon = icon;
        this.packageArrivedLabel = packageaArrivedLabel;
        this.packagesCounterLabel = packagesCounterLabel;
    }

    public Node2() {

    }

    public void addNeighbor(Node2 neighbor) {
        neighbors.add(neighbor);
    }

    @Override
    public void run() {
        try {
            if(firstPackage != null) {
                sendPackage(firstPackage);
                firstPackage = null;
            } else {
                while(true) {
                    if(!packagesReceived.isEmpty()) {
                        sendPackage(packagesReceived.get(0));
                    }

                    Thread.sleep(3000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void receivePackage(Package newPackage) {
        if(newPackage.getReceiverId() == this.id) {
            if(Package.arrived == false) {
                Platform.runLater(() -> {
                    changeArrivedlabel();
                    changeCounterLabel();
                });
                Package.arrived = true;
            }
        }

        packagesReceived.add(newPackage);
    }

    public void sendPackage(Package packageArrived) throws InterruptedException {
        for(Node2 neighbor : neighbors) {
            if(packageArrived.getSenderId() != neighbor.getID()) {
                Package newPackage = new Package(packageArrived.getSenderId(), packageArrived.getReceiverId());
                newPackage.setSenderCoordinates(this.getCoordinates());
                newPackage.setNeighborCoordinates(neighbor.getCoordinates());
                newPackage.setIcon(this.icon);
                newPackage.start();
                neighbor.receivePackage(newPackage);
            }
        }

        packagesReceived.remove(packageArrived);
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

    public void setPackage(Package firstPackage) {
        this.firstPackage = firstPackage;
    }

    public void getNeighbors() {
        for(Node2 neighbor : neighbors) {
            System.out.print(this.id + " ");
            System.out.println(neighbor);
        }
    }
}   
