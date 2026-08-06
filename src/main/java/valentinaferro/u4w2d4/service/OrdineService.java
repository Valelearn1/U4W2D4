package valentinaferro.u4w2d4.service;

import org.springframework.stereotype.Service;
import valentinaferro.u4w2d4.StatoOrdine;
import valentinaferro.u4w2d4.entity.Cliente;
import valentinaferro.u4w2d4.entity.Ordine;
import valentinaferro.u4w2d4.entity.Rider;
import valentinaferro.u4w2d4.exception.OrdineNotFoundException;
import valentinaferro.u4w2d4.repository.OrdineRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class OrdineService {
    private final OrdineRepository ordineRepository;
    private final ClienteService clienteService;
    private final RiderService riderService;

    public OrdineService(OrdineRepository ordineRepository, ClienteService clienteService, RiderService riderService) {
        this.ordineRepository = ordineRepository;
        this.clienteService = clienteService;
        this.riderService = riderService;
    }

    public Ordine getById(UUID id) {
        return ordineRepository.findById(id)
                .orElseThrow(() -> new OrdineNotFoundException(id));
    }

    public List<Ordine> getAll() {
        return ordineRepository.findAll();
    }

    // Non riceve un Ordine già pronto: riceve gli id di Cliente e Rider,
    // li cerca (getById lancia già la relativa NotFoundException se non esistono)
    // e associa le entità già persistite, senza crearle da zero.
    public Ordine create(LocalDate dataOrdine, StatoOrdine stato, UUID clienteId, UUID riderId) {
        Cliente cliente = clienteService.getById(clienteId);
        Rider rider = riderService.getById(riderId);

        Ordine ordine = new Ordine(dataOrdine, stato, cliente, rider);
        return ordineRepository.save(ordine);
    }
}
