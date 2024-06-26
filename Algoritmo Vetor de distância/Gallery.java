/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 23/05/2024
* Ultima alteracao.: 22/05/2024
* Nome.............: Gallery
* Funcao...........: Import Images
*************************************************************** */
import javafx.scene.image.Image;

public class Gallery {
    public static final Image[] redImages = {
        new Image(Gallery.class.getResourceAsStream("img/Edge1and2Red.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge1and3Red.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge2and4Red.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge2and6Red.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge3and4Red.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge3and5Red.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge4and5Red.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge4and7Red.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge5and8Red.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge6and7Red.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge6and9Red.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge7and8Red.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge8and9Red.png"))
    };

    public static final Image[] initialImages = {
        new Image(Gallery.class.getResourceAsStream("img/Edge1and2.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge1and3.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge2and4.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge2and6.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge3and4.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge3and5.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge4and5.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge4and7.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge5and8.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge6and7.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge6and9.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge7and8.png")),
        new Image(Gallery.class.getResourceAsStream("img/Edge8and9.png"))
    };

    /* ***************************************************************
    * Metodo: getImage
    * Funcao: Return the correct image from the red images array
    * Parametros: index= Index of the desired image
    * Retorno: A javafx Image
    *************************************************************** */
    public static Image getImage(int index) {
        return redImages[index];
    } // End getImage

    /* ***************************************************************
    * Metodo: getInitialImage
    * Funcao: Return the correct image from the initial images array
    * Parametros: index= Index of the desired image
    * Retorno: A javafx Image
    *************************************************************** */
    public static Image getInitialImage(int index) {
        return initialImages[index];
    } // End getInitialImage
} // End class Gallery