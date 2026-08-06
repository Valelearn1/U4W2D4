package valentinaferro.u4w2d4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valentinaferro.u4w2d4.entity.Pizza;

import java.util.UUID;

public interface PizzaRepository extends JpaRepository<Pizza, UUID> {
}
