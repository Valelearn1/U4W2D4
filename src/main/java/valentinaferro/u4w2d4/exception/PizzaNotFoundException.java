package valentinaferro.u4w2d4.exception;

import java.util.UUID;

public class PizzaNotFoundException extends RuntimeException {

    public PizzaNotFoundException(UUID id) {
        super("Pizza con id " + id + " non trovata");
    }
}
