/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 19/06/2024
* Ultima alteracao.: 28/06/2024
* Nome.............: Group
* Funcao...........: Store a group information
*************************************************************** */

package models;

import java.net.Socket;
import java.util.ArrayList;

public class Group {
    private int id;
    private ArrayList<Socket> participants = new ArrayList<>();

    // Constructor
    public Group(int id) {
        this.id = id;
    }

    /* ***************************************************************
    * Metodo: addParticipant
    * Funcao: Store a participant in the array
    * Parametros: user= A connected client
    * Retorno: void
    *************************************************************** */
    public void addParticipant(Socket user) {
        participants.add(user);
    } // End addParticipant

    /* ***************************************************************
    * Metodo: removeParticipant
    * Funcao: Romve a participant from the array
    * Parametros: user= A connected client
    * Retorno: void
    *************************************************************** */
    public void removeParticipant(Socket user) {
        participants.remove(user);
    } // End removeParticipant

    /* ***************************************************************
    * Metodo: participantExists
    * Funcao: Given a ip, verify if there is a participant with this ip
    * Parametros: participantIp= The ip for searching 
    * Retorno: A boolean
    *************************************************************** */
    public Boolean participantExists(String particpantIp) {
        // Iterate over particpants array
        for(Socket participant : participants) {
            return particpantIp.equals(String.valueOf(participant.getInetAddress())); // Verify if the ips are equal
        } // End for

        return false;
    } // End particpantEcists

    /* ***************************************************************
    * Metodo: getId
    * Funcao: Return group id
    * Parametros: void
    * Retorno: Group id
    *************************************************************** */
    public int getId() {
        return this.id;
    } // End getID

    /* ***************************************************************
    * Metodo: getParticipants
    * Funcao: Return array list of participants
    * Parametros: void
    * Retorno: Array list with connections
    *************************************************************** */
    public ArrayList<Socket> getParticipants() {
        return this.participants;
    } // End getParticipants
} // End class Group
