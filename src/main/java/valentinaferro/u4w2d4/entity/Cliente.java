package valentinaferro.u4w2d4.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "Cliente")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cliente {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    private String email;
    private String indirizzoConsegna;

    public Cliente(String nome, String cognome, String email, String indirizzoConsegna) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.indirizzoConsegna = indirizzoConsegna;
    }
}
