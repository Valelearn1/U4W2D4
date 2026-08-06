package valentinaferro.u4w2d4.exception;

public class PizzaNotFoundException extends RuntimeException {

    public PizzaNotFoundException(Long id) {
        super("Pizza con id " + id + " non trovata");
    }
}
