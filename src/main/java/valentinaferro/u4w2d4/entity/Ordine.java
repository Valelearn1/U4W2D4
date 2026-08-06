package valentinaferro.u4w2d4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import valentinaferro.u4w2d4.StatoOrdine;

@Entity
@Table(name = "Ordine")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ordine {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false)
    private LocalDate dataOrdine;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoOrdine stato;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "rider_id")
    private Rider rider;

    public Ordine(Cliente cliente, Rider rider) {
        this.cliente = cliente;
        this.rider = rider;
        this.dataOrdine = LocalDate.now();
        this.stato = StatoOrdine.IN_PREPARAZIONE;
    }


}
