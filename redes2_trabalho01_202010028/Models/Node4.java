package Models;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Node4 {
    private int id;
    private int x; // X cordinate in the window 
    private int y; // Y cordinate in the window
    List<Node4> neighbors = new ArrayList<>();
    private ImageView icon;
    private Label packageArrivedLabel;

    public Node4(int id, int x, int y, ImageView icon, Label packageaArrivedLabel) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.icon = icon;
        this.packageArrivedLabel = packageaArrivedLabel;
    }

    public Node4() {

    }

    public void addNeighbor(Node4 neighbor) {
        neighbors.add(neighbor);
    }
}
