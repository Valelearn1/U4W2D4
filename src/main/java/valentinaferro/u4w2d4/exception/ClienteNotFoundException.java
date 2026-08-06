package valentinaferro.u4w2d4.exception;

import java.util.UUID;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException(UUID id) {
        super("Cliente con id " + id + " non trovato");
    }
}
