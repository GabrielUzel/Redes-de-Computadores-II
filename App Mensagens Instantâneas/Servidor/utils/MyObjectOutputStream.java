/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 19/06/2024
* Ultima alteracao.: 28/06/2024
* Nome.............: MyObjectOutputStream
* Funcao...........: Avoid ObjectOutputStream type coid exception
*************************************************************** */

package Servidor.utils;

import java.io.*;
 
public class MyObjectOutputStream extends ObjectOutputStream {
    // Constructors
    public MyObjectOutputStream() throws IOException { 
        super();
    }
    public MyObjectOutputStream(OutputStream object) throws IOException {
        super(object);
    }

    /* ***************************************************************
    * Metodo: writeStreamHeader
    * Funcao: Remove any operations from this function that came from ObjectOutputStream
    * so the server can avoid a type code AC exception
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void writeStreamHeader() throws IOException {
        return;
    } // End writeStreamHeader
} // End class MyObjectOutputStream
