package ma.enset.control.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author $ {USER}
 **/
@Entity
@AllArgsConstructor  @NoArgsConstructor @Data @SuperBuilder
@DiscriminatorValue("CA")
@ToString
public class ContratAutomobile extends Contrat {
    private String numeroImmatriculation;
    private String marqueVehicule;
    private String modeleVehicule;
}
