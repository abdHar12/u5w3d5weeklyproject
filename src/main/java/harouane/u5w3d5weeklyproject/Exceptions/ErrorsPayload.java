package harouane.u5w3d5weeklyproject.Exceptions;

import java.time.LocalDateTime;

public class ErrorsPayload{
    private String message;
    private LocalDateTime timestamp;

    public ErrorsPayload(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
