package ma.enset.control.repositories;

import ma.enset.control.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author $ {USER}
 **/
public interface PaiementRepository extends JpaRepository<Paiement,Long> {
}
