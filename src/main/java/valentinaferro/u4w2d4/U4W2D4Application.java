package valentinaferro.u4w2d4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication è l'annotazione "magica" che fa partire tutto Spring Boot:
// dice all'applicazione di fare la scansione automatica dei pacchetti (component
// scan), attivare l'auto-configurazione, ecc. È già presente di default nel
// progetto generato da Spring Initializr.
@SpringBootApplication
public class U4W2D4Application {

    public static void main(String[] args) {
        // Questo avvia il "contenitore" di Spring (ApplicationContext): da qui in poi
        // Spring legge la classe BeanConfig, crea tutti i Bean (toppings, pizze,
        // bevande, menu) e li tiene pronti nel contesto dell'applicazione
        SpringApplication.run(U4W2D4Application.class, args);
    }
}
