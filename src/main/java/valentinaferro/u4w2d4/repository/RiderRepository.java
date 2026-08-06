package valentinaferro.u4w2d4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valentinaferro.u4w2d4.entity.Rider;

import java.util.UUID;

public interface RiderRepository extends JpaRepository<Rider, UUID> {
}
