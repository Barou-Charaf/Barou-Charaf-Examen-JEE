package ma.enset.control.repositories;

import ma.enset.control.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findByNameContainingIgnoreCase(String name);

    @Query("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%',:keyword,'%')) OR LOWER(c.email) LIKE LOWER(CONCAT('%',:keyword,'%'))")
    List<Client> searchByKeyword(@Param("keyword") String keyword);
}
