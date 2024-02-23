package harouane.u5w3d5weeklyproject.Exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(int id) {
        super("L'utente con id " + id + " non è stato trovato");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
