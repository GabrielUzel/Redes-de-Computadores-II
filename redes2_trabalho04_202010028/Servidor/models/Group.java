package Servidor.models;

import java.net.Socket;
import java.util.ArrayList;

public class Group {
    private int id;
    private ArrayList<Socket> participants = new ArrayList<>();

    public Group(int id) {
        this.id = id;
    }

    public void addParticipant(Socket user) {
        participants.add(user);
    }

    public int getId() {
        return this.id;
    }

    public Boolean participantExists(String ip) {
        for(Socket participant : participants) {
            return ip.equals(String.valueOf(participant.getInetAddress()));
        }

        return false;
    }
}
