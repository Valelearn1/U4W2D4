package valentinaferro.u4w2d4.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import valentinaferro.u4w2d4.entity.Pizza;
import valentinaferro.u4w2d4.exception.InvalidPizzaException;
import valentinaferro.u4w2d4.exception.PizzaNotFoundException;
import valentinaferro.u4w2d4.service.PizzaService;

import java.util.UUID;

// CommandLineRunner separato dal main: Spring esegue automaticamente ogni Bean di
// questo tipo all'avvio. Qui verifichiamo "a mano" che ogni metodo del Service
// funzioni, stampando i risultati - inclusi i casi in cui un controllo interviene
// davvero (id inesistente, dati non validi).
@Component
public class PizzaRunner implements CommandLineRunner {

    private final PizzaService pizzaService;

    public PizzaRunner(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n=== TEST PizzaService ===");

        // 1) CREATE valido
        Pizza margherita = pizzaService.create(
                new Pizza("Margherita", 6.0, "800 kcal", "pomodoro,mozzarella,basilico"));
        System.out.println("Creata: " + margherita);

        // 2) CREATE non valido -> il controllo del Service deve intervenire
        try {
            pizzaService.create(new Pizza("", -5.0, "info", "topping"));
        } catch (InvalidPizzaException e) {
            System.out.println("Creazione bloccata correttamente: " + e.getMessage());
        }

        // 3) GET ALL
        System.out.println("Tutte le pizze: " + pizzaService.getAll());

        // 4) GET BY ID valido
        Pizza trovata = pizzaService.getById(margherita.getId());
        System.out.println("Trovata per id: " + trovata);

        // 5) GET BY ID inesistente -> il controllo del Service deve intervenire
        try {
            pizzaService.getById(UUID.randomUUID());
        } catch (PizzaNotFoundException e) {
            System.out.println("Ricerca bloccata correttamente: " + e.getMessage());
        }

        // 6) UPDATE valido
        Pizza aggiornata = pizzaService.update(margherita.getId(),
                new Pizza("Margherita Extra", 7.5, "850 kcal", "pomodoro,mozzarella,basilico,origano"));
        System.out.println("Aggiornata: " + aggiornata);

        // 7) UPDATE su id inesistente -> il controllo del Service deve intervenire
        try {
            pizzaService.update(UUID.randomUUID(), new Pizza("Fantasma", 5.0, "info", "topping"));
        } catch (PizzaNotFoundException e) {
            System.out.println("Update bloccato correttamente: " + e.getMessage());
        }

        // 8) DELETE valido
        pizzaService.delete(aggiornata.getId());
        System.out.println("Eliminata pizza con id " + aggiornata.getId());

        // 9) DELETE su id inesistente -> il controllo del Service deve intervenire
        try {
            pizzaService.delete(UUID.randomUUID());
        } catch (PizzaNotFoundException e) {
            System.out.println("Delete bloccata correttamente: " + e.getMessage());
        }

        // 10) Stato finale
        System.out.println("Pizze rimaste: " + pizzaService.getAll());
    }
}
