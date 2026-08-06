package valentinaferro.u4w2d4.service;

import org.springframework.stereotype.Service;
import valentinaferro.u4w2d4.entity.Pizza;
import valentinaferro.u4w2d4.exception.InvalidPizzaException;
import valentinaferro.u4w2d4.exception.PizzaNotFoundException;
import valentinaferro.u4w2d4.repository.PizzaRepository;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public Pizza getById(Long id) {
        // findById restituisce un Optional<Pizza> (può essere vuoto se l'id non esiste).
        // orElseThrow: se l'Optional contiene un valore lo restituisce, altrimenti
        // esegue la lambda e lancia l'eccezione custom "non trovata" - equivale a un
        // if(isPresent) return... else throw..., ma senza rischiare un NullPointerException.
        return pizzaRepository.findById(id)
                .orElseThrow(() -> new PizzaNotFoundException(id));
    }

    public List<Pizza> getAll() {
        return pizzaRepository.findAll();
    }

    public Pizza create(Pizza pizza) {
        // Il Service non si limita a inoltrare al repository: valida prima di salvare.
        validate(pizza);
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Long id, Pizza datiAggiornati) {
        // Riuso getById: se l'id non esiste, lancia già PizzaNotFoundException da solo.
        validate(datiAggiornati);
        Pizza pizzaEsistente = getById(id); // se la validazione non va, non prende l'id


        pizzaEsistente.setNome(datiAggiornati.getNome());
        pizzaEsistente.setPrezzo(datiAggiornati.getPrezzo());
        pizzaEsistente.setInformazioniNutrizionali(datiAggiornati.getInformazioniNutrizionali());
        pizzaEsistente.setTopping(datiAggiornati.getTopping());

        return pizzaRepository.save(pizzaEsistente);
    }

    public void delete(Long id) {
        // Anche qui: se l'id non esiste, getById lancia PizzaNotFoundException
        // prima ancora di arrivare alla delete vera e propria.
        Pizza pizza = getById(id);
        pizzaRepository.delete(pizza);
    }

    // Metodo privato riusato sia da create che da update: unico posto dove vivono
    // le regole "cosa rende una pizza valida", così non le duplico in due punti.
    private void validate(Pizza pizza) {
        if (pizza.getNome() == null || pizza.getNome().isBlank()) {
            throw new InvalidPizzaException("Il nome della pizza non può essere vuoto");
        }
        if (pizza.getPrezzo() <= 0) {
            throw new InvalidPizzaException("Il prezzo della pizza deve essere positivo");
        }
    }
}
