package ma.enset.control.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author $ {USER}
 **/
@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name ;
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Contrat> contrats;
}
