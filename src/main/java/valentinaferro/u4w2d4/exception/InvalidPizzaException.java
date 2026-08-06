package valentinaferro.u4w2d4.exception;

// Eccezione dedicata ai dati non validi (nome vuoto, prezzo negativo, ecc.),
// distinta da PizzaNotFoundException perché la causa è diversa: qui il problema
// non è "l'id non esiste" ma "i dati passati non rispettano le regole minime".
public class InvalidPizzaException extends RuntimeException {

    public InvalidPizzaException(String message) {
        super(message);
    }
}
