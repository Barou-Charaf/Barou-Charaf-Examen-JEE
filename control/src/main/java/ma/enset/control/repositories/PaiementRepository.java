package ma.enset.control.repositories;

import ma.enset.control.entities.Paiement;
import ma.enset.control.enums.TypePaiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement,Long> {
    List<Paiement> findByContratId(Long contratId);

    List<Paiement> findByType(TypePaiement type);

    List<Paiement> findByDatePaiementBetween(LocalDateTime start, LocalDateTime end);

    long countByContratId(Long contratId);
}
