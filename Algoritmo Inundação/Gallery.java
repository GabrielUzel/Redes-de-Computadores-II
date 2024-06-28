/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 20/04/2024
* Ultima alteracao.: 29/04/2024
* Nome.............: Gallery
* Funcao...........: Import images
*************************************************************** */
import javafx.scene.image.Image;

public class Gallery {
    // Define package image
    public static final Image icon = new Image(Gallery.class.getResourceAsStream("img/Package.jpg"));
    // Define message icon image on main menu
    public static final Image showMessageImg = new Image(Gallery.class.getResourceAsStream("img/ExclamationMark.png"));
} // End class Gallery
