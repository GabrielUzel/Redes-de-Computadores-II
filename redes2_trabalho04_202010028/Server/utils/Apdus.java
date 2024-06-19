package Server.utils;

public enum Apdus {
    JOIN(1), LEAVE(2), SEND(3);

    private final int value;

    Apdus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getApdu(int value) {
        for (Apdus apdu : Apdus.values()) {
            if(apdu.getValue() == value) {
                return apdu.name();
            }
        }

        throw new IllegalArgumentException("Invalid value");
    }
}
