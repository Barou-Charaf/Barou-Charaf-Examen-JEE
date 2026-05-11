package ma.enset.control.repositories;

import ma.enset.control.entities.Contrat;
import ma.enset.control.entities.ContratAutomobile;
import ma.enset.control.enums.StatusContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat,Long> {
    List<Contrat> findByClientId(Long clientId);

    List<Contrat> findByStatus(StatusContrat status);

    List<Contrat> findByDateSouscriptionBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT c FROM Contrat c LEFT JOIN FETCH c.client WHERE LOWER(CAST(c.id AS string)) LIKE LOWER(CONCAT('%',:keyword,'%'))")
    List<Contrat> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT c FROM Contrat c WHERE c.client.id = :clientId")
    List<Contrat> findContractsByClientId(@Param("clientId") Long clientId);

    @Query("SELECT c FROM ContratAutomobile c WHERE c.numeroImmatriculation LIKE CONCAT('%', :immatriculation, '%')")
    List<ContratAutomobile> searchAutoByImmatriculation(@Param("immatriculation") String immatriculation);

    long countByStatus(StatusContrat status);
}
