package valentinaferro.u4w2d4.service;

import org.springframework.stereotype.Service;
import valentinaferro.u4w2d4.entity.Cliente;
import valentinaferro.u4w2d4.exception.ClienteNotFoundException;
import valentinaferro.u4w2d4.repository.ClienteRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente getById(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
