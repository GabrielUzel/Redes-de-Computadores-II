package Cliente.util;

import java.io.Serializable;

public class MessageObject implements Serializable {
    private String message;
    private String clientIp;
    private String clientName;

    public MessageObject(String message, String clientIp, String clientName) {
        this.message = message;
        this.clientIp = clientIp;
        this.clientName = clientName;
    }

    public String getMessage() {
        return this.message;
    }

    public String getClient() {
        return this.clientIp;
    }

    public String getClientName() {
        return this.clientName;
    }
}
