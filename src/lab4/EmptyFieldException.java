package lab4;

public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException(int columnIndex) {
        super(String.format("Empty field in col number %d", columnIndex));
    }
}
