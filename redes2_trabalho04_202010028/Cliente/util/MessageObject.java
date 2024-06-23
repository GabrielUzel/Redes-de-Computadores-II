package Cliente.util;

import java.io.Serializable;

public class MessageObject implements Serializable {
    private String message;
    private String clientIp;

    public MessageObject(String message, String clientIp) {
        this.message = message;
        this.clientIp = clientIp;
    }

    public String getMessage() {
        return this.message;
    }

    public String getClient() {
        return this.clientIp;
    }
}
