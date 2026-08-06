package valentinaferro.u4w2d4.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "Rider")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Rider {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    private String mezzoConsegne;

    public Rider(String nome, String cognome, String mezzoConsegne) {
        this.nome = nome;
        this.cognome = cognome;
        this.mezzoConsegne = mezzoConsegne;
    }
}
