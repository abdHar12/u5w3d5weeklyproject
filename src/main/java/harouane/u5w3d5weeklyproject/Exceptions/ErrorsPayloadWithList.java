package harouane.u5w3d5weeklyproject.Exceptions;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ErrorsPayloadWithList extends ErrorsPayload{
    private List<String> errorsList;

    public ErrorsPayloadWithList(String message, LocalDateTime timestamp, List<String> errorsList) {
        super(message, timestamp);
        this.errorsList = errorsList;
    }
}
