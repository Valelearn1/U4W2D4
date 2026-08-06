package valentinaferro.u4w2d4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valentinaferro.u4w2d4.entity.Cliente;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
