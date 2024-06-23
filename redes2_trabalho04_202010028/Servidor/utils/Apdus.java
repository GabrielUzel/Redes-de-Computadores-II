/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 19/06/2024
* Ultima alteracao.: //
* Nome.............: Apdus
* Funcao...........: Store the apdus that will be used by server
*************************************************************** */
package Servidor.utils;

public enum Apdus {
    // Apdus values on string
    JOIN(1), LEAVE(2), SEND(3);
    private final int value;

    // Constructor
    Apdus(int value) {
        this.value = value;
    }

    /* ***************************************************************
    * Metodo: getValue
    * Funcao: Return the string value
    * Parametros: void
    * Retorno: A int from 1 to 3
    *************************************************************** */
    public int getValue() {
        return value;
    } // End getValue

    /* ***************************************************************
    * Metodo: getApdu
    * Funcao: Return the apdu string given its value
    * Parametros: value= The disired value
    * Retorno: A string representing the apdu
    *************************************************************** */
    public static String getApdu(int value) {
        for(Apdus apdu : Apdus.values()) {
            if(apdu.getValue() == value) {
                return apdu.name();
            } // End if
        } // End for 

        throw new IllegalArgumentException("Invalid value");
    } // End getApdu
} // End class apdus
