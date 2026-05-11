package ma.enset.control.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ma.enset.control.enums.StatusContrat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author $ {USER}
 **/
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Contrat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime  dateSouscription;
    private LocalDateTime datevalidation;
    private Double montantCotisation;
    private int dureeContract;
    private Double tauxCouverture;
    private StatusContrat status;
    @OneToMany(mappedBy = "contrat")
    private List<Paiement> paiments;
    @ManyToOne
    private Client client;
}
