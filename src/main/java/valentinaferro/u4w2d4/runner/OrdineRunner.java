package valentinaferro.u4w2d4.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import valentinaferro.u4w2d4.StatoOrdine;
import valentinaferro.u4w2d4.entity.Cliente;
import valentinaferro.u4w2d4.entity.Ordine;
import valentinaferro.u4w2d4.entity.Rider;
import valentinaferro.u4w2d4.exception.ClienteNotFoundException;
import valentinaferro.u4w2d4.exception.OrdineNotFoundException;
import valentinaferro.u4w2d4.exception.RiderNotFoundException;
import valentinaferro.u4w2d4.service.ClienteService;
import valentinaferro.u4w2d4.service.OrdineService;
import valentinaferro.u4w2d4.service.RiderService;

import java.time.LocalDate;
import java.util.UUID;

// Come PizzaRunner: eseguito automaticamente all'avvio, verifica "a mano" che
// ClienteService/RiderService/OrdineService funzionino insieme - inclusi i casi
// in cui un id inesistente deve bloccare la creazione dell'ordine.
@Component
public class OrdineRunner implements CommandLineRunner {

    private final ClienteService clienteService;
    private final RiderService riderService;
    private final OrdineService ordineService;

    public OrdineRunner(ClienteService clienteService, RiderService riderService, OrdineService ordineService) {
        this.clienteService = clienteService;
        this.riderService = riderService;
        this.ordineService = ordineService;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n=== TEST OrdineService ===");

        // 1) Cliente e Rider di partenza, già persistiti
        Cliente cliente = clienteService.create(
                new Cliente("Mario", "Rossi", "mario.rossi@email.it", "Via Roma 1"));
        System.out.println("Creato cliente: " + cliente);

        Rider rider = riderService.create(new Rider("Luca", "Bianchi", "Scooter"));
        System.out.println("Creato rider: " + rider);

        // 2) CREATE ordine valido -> cerca cliente e rider tramite id, non li crea da zero
        Ordine ordine = ordineService.create(LocalDate.now(), StatoOrdine.IN_PREPARAZIONE,
                cliente.getId(), rider.getId());
        System.out.println("Creato ordine: " + ordine);

        // 3) GET ALL
        System.out.println("Tutti i clienti: " + clienteService.getAll());
        System.out.println("Tutti i rider: " + riderService.getAll());
        System.out.println("Tutti gli ordini: " + ordineService.getAll());

        // 4) GET BY ID valido
        Ordine trovato = ordineService.getById(ordine.getId());
        System.out.println("Ordine trovato per id: " + trovato);

        // 5) GET BY ID inesistente -> il controllo del Service deve intervenire
        try {
            ordineService.getById(UUID.randomUUID());
        } catch (OrdineNotFoundException e) {
            System.out.println("Ricerca bloccata correttamente: " + e.getMessage());
        }

        // 6) CREATE con cliente inesistente -> il controllo deve intervenire
        try {
            ordineService.create(LocalDate.now(), StatoOrdine.IN_PREPARAZIONE, UUID.randomUUID(), rider.getId());
        } catch (ClienteNotFoundException e) {
            System.out.println("Creazione bloccata correttamente: " + e.getMessage());
        }

        // 7) CREATE con rider inesistente -> il controllo deve intervenire
        try {
            ordineService.create(LocalDate.now(), StatoOrdine.IN_PREPARAZIONE, cliente.getId(), UUID.randomUUID());
        } catch (RiderNotFoundException e) {
            System.out.println("Creazione bloccata correttamente: " + e.getMessage());
        }
    }
}
