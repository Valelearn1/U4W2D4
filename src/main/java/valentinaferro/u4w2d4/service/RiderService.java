package valentinaferro.u4w2d4.service;

import org.springframework.stereotype.Service;
import valentinaferro.u4w2d4.entity.Rider;
import valentinaferro.u4w2d4.exception.RiderNotFoundException;
import valentinaferro.u4w2d4.repository.RiderRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RiderService {
    private final RiderRepository riderRepository;

    public RiderService(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    public Rider getById(UUID id) {
        return riderRepository.findById(id)
                .orElseThrow(() -> new RiderNotFoundException(id));
    }

    public List<Rider> getAll() {
        return riderRepository.findAll();
    }

    public Rider create(Rider rider) {
        return riderRepository.save(rider);
    }
}
