package Server.models;

import java.util.ArrayList;

public class Group {
    private int id;
    private String name;
    private ArrayList<User> participants = new ArrayList<>();
    private ArrayList<String> messages = new ArrayList<>();

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ArrayList<String> getMessages() {
        return this.messages;
    }
}
