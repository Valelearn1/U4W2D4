package valentinaferro.u4w2d4.exception;

import java.util.UUID;

public class OrdineNotFoundException extends RuntimeException {

    public OrdineNotFoundException(UUID id) {
        super("Ordine con id " + id + " non trovato");
    }
}
