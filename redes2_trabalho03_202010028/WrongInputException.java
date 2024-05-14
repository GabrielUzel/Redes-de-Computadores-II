/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 14/05/2024
* Ultima alteracao.: //
* Nome.............: WrongInputException
* Funcao...........: Show error if user selects same node to be sender and receiver
*************************************************************** */
public class WrongInputException extends Exception {
    // Constructors
    public WrongInputException(String message) {
        super(message);
    }
    
    public WrongInputException() {

    }
} // End class WrongInputException
