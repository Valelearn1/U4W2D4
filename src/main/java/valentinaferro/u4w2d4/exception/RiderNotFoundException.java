package valentinaferro.u4w2d4.exception;

import java.util.UUID;

public class RiderNotFoundException extends RuntimeException {

    public RiderNotFoundException(UUID id) {
        super("Rider con id " + id + " non trovato");
    }
}
