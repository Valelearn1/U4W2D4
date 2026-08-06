package valentinaferro.u4w2d4.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "Pizze")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Pizza {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private double prezzo;
    private String informazioniNutrizionali;
    private String topping;

    public Pizza(String nome, double prezzo, String informazioniNutrizionali, String topping) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.informazioniNutrizionali = informazioniNutrizionali;
        this.topping = topping;
    }

    @Override
    public String toString() {
        return nome + " - €" + prezzo + " (" + informazioniNutrizionali + ")";
    }
}
