package ar.edu.unlam.tallerweb1.excepciones;

public class EmailExistenteException extends RuntimeException {
    public EmailExistenteException(String s) {
        super(s);
    }
}
