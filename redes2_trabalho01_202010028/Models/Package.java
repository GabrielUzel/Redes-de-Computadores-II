package Models;

import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class Package extends Thread {
    public static int packageCounter = 0;
    private int senderId;
    private int receiverId;
    private int[] senderCoordinates;
    private int[] neighborCoordinates;
    private ImageView icon;
    public static int ttl = 50;
    public static boolean arrived = false;

    public Package(int senderId, int receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            iconAnimation();
        });
        packageCounter++;
        System.out.println("Pacotes enviados: " + packageCounter);
    }

    public void iconAnimation() {
        icon.setFitWidth(19);
        icon.setFitHeight(29);
        icon.setVisible(true);
        icon.setLayoutX(senderCoordinates[0]);
        icon.setLayoutY(senderCoordinates[1]);
        icon.setX(0);
        icon.setY(0);

        TranslateTransition animation = new TranslateTransition(Duration.millis(2000));
        animation.setNode(icon);

        animation.setByX(neighborCoordinates[0] - senderCoordinates[0]);
        animation.setByY(neighborCoordinates[1] - senderCoordinates[1]);

        animation.setOnFinished(event -> {
            icon.setVisible(false);
            icon.setLayoutX(senderCoordinates[0]);
            icon.setLayoutY(senderCoordinates[1]);
        });
        
        animation.play();
    }
    
    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setSenderCoordinates(int[] senderCoordinates) {
        this.senderCoordinates = senderCoordinates;
    }

    public void setNeighborCoordinates(int[] neighborCoordinates) {
        this.neighborCoordinates = neighborCoordinates;
    }
    
    public void setIcon(ImageView icon) {
        this.icon = icon;
    }
}
