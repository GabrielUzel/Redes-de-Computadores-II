package Servidor.models;

import java.util.ArrayList;

public class Group {
    private int id;
    private ArrayList<User> participants = new ArrayList<>();

    public Group(int id) {
        this.id = id;
    }

    public void addParticipant(User user) {
        participants.add(user);
    }

    public int getId() {
        return this.id;
    }
}
