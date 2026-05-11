package ma.enset.control.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ma.enset.control.enums.TypeLogement;

/**
 * @author $ {USER}
 **/
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("CH")
public class ContratHabitation extends Contrat {
  private TypeLogement typeLogement;
  private String adreselogement;
  private Double superficie;
}
