package ma.enset.control.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ma.enset.control.enums.NiveauCouverture;

/**
 * @author $ {USER}
 **/
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("CS")
@ToString
public class ContratSante extends Contrat{
    private NiveauCouverture niveauCouvertur;
    private int nombrePersonnesCouvertes;
}
