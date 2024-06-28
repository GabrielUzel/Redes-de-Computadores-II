/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 20/04/2024
* Ultima alteracao.: 29/04/2024
* Nome.............: Package
* Funcao...........: Animate the package on IU
*************************************************************** */
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class Package extends Thread {
    public static int packageCounter = 0; // Variable to count how many packages were sent
    private int senderId;
    private int receiverId;
    private int[] senderCoordinates; // Sender router coordinates in the window
    private int[] neighborCoordinates; // Neighbor router coordinates in the window
    private ImageView icon; // Package image view
    public static int ttl = 50; // Time to live for the packages
    public static boolean arrived = false; // Variable to show that the package arrived at the final destination

    // Constructor
    public Package(int senderId, int receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    } // End constructor

    /* ***************************************************************
    * Metodo: run
    * Funcao: Thread start
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        iconAnimation(); // Animate the package
        packageCounter++; // Update package counter
        System.out.println("Pacotes enviados: " + packageCounter);
    } // End run 

    /* ***************************************************************
    * Metodo: iconAnimation
    * Funcao: Move the package image through the window
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void iconAnimation() {
        Platform.runLater(() -> {
            icon.setFitWidth(19); // Set icon width
            icon.setFitHeight(29); // Set icon width
            icon.setVisible(true); // Make the image view visible

            // Set the image view the right initial coordinates
            icon.setLayoutX(senderCoordinates[0]); 
            icon.setLayoutY(senderCoordinates[1]);

            TranslateTransition animation = new TranslateTransition(Duration.millis(2000));
            animation.setNode(icon); // Set the animation image
            animation.setCycleCount(1); // The animation occours one single time
            // Set the image view final coordinates
            animation.setByX(neighborCoordinates[0] - senderCoordinates[0]); 
            animation.setByY(neighborCoordinates[1] - senderCoordinates[1]);

            // At the end of the animation, set the icon invisible and return to the initial coordinates
            animation.setOnFinished(event -> {
                icon.setVisible(false);
                    icon.setTranslateX(0);
                    icon.setTranslateY(0);
            });

            animation.play(); // Start animation
        });
    } // End iconAnimation
    
    /* ***************************************************************
    * Metodo: getSenderId
    * Funcao: Return sender id
    * Parametros: void
    * Retorno: senderId variable
    *************************************************************** */
    public int getSenderId() {
        return senderId;
    } // End getSenderId

    /* ***************************************************************
    * Metodo: getReceiverId
    * Funcao: Return receiver id
    * Parametros: void
    * Retorno: receiverId variable
    *************************************************************** */
    public int getReceiverId() {
        return receiverId;
    } // End getReceiverId

    /* ***************************************************************
    * Metodo: setSenderCoordinates
    * Funcao: Set senderCoordinates variable
    * Parametros: Array with the x and y coordinates
    * Retorno: void
    *************************************************************** */
    public void setSenderCoordinates(int[] senderCoordinates) {
        this.senderCoordinates = senderCoordinates;
    } // End setSenderCoordinates

    /* ***************************************************************
    * Metodo: setNeighborCoordinates
    * Funcao: Set senderCoordinates variable
    * Parametros: Array with the x and y coordinates
    * Retorno: void
    *************************************************************** */ 
    public void setNeighborCoordinates(int[] neighborCoordinates) {
        this.neighborCoordinates = neighborCoordinates;
    } // End setNeighborCoordinates

    /* ***************************************************************
    * Metodo: setIcon
    * Funcao: Set icon variable
    * Parametros: A imagew view of the package
    * Retorno: void
    *************************************************************** */
    public void setIcon(ImageView icon) {
        this.icon = icon;
    } // End setIcon

    /* ***************************************************************
    * Metodo: reduceTTL
    * Funcao: Reduce in 1 the ttl
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public static void reduceTTL() {
        ttl--;
    } // End reduceTTL

    /* ***************************************************************
    * Metodo: setArrivedTrue
    * Funcao: Set arrived variable to true
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public static void setArrivedTrue() {
        arrived = true;
    } // End setArrivedTrue
} // End class Package
