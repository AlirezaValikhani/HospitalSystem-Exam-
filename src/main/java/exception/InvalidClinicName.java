package exception;

public class InvalidClinicName extends RuntimeException{
    public InvalidClinicName() {
    }

    public InvalidClinicName(String message) {
        super(message);
    }
}
