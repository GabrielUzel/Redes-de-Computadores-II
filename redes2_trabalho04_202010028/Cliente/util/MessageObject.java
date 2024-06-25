/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 19/06/2024
* Ultima alteracao.: 28/06/2024
* Nome.............: MessageObject
* Funcao...........: Organize the structure of the object sent e received by the server
*************************************************************** */

package Cliente.util;

import java.io.Serializable;

public class MessageObject implements Serializable {
    private String message;
    private String clientIp;
    private String clientName;

    // Constructor
    public MessageObject(String message, String clientIp, String clientName) {
        this.message = message;
        this.clientIp = clientIp;
        this.clientName = clientName;
    }

    /* ***************************************************************
    * Metodo: getMessage
    * Funcao: Return the message
    * Parametros: void
    * Retorno: A String
    *************************************************************** */
    public String getMessage() {
        return this.message;
    } // End getMessage

    /* ***************************************************************
    * Metodo: getClientIp
    * Funcao: Return the clientIp
    * Parametros: void
    * Retorno: A String
    *************************************************************** */
    public String getClientIp() {
        return this.clientIp;
    } // End getClientIp

    /* ***************************************************************
    * Metodo: getClientName
    * Funcao: Return the client name
    * Parametros: void
    * Retorno: A String
    *************************************************************** */
    public String getClientName() {
        return this.clientName;
    } // End getClientName
} // End class MessageObject
