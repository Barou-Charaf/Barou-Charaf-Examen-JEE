package ma.enset.control.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.enset.control.enums.TypePaiement;

import java.time.LocalDateTime;

/**
 * @author $ {USER}
 **/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Paiement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime datePaiement;
    private Double montant;
    private TypePaiement type;
    @ManyToOne
    private Contrat contrat;
}
