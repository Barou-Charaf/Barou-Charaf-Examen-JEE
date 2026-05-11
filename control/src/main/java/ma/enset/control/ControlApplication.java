package ma.enset.control;

import ma.enset.control.entities.*;
import ma.enset.control.enums.*;
import ma.enset.control.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class ControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControlApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(
            ClientRepository clientRepository,
            ContratRepository contratRepository,
            PaiementRepository paiementRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            if (userRepository.count() > 0) return;

            User admin = User.builder()
                    .username("admin")
                    .email("admin@control.ma")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ROLE_ADMIN)
                    .enabled(true)
                    .build();

            User employe = User.builder()
                    .username("employe")
                    .email("employe@control.ma")
                    .password(passwordEncoder.encode("employe123"))
                    .role(Role.ROLE_EMPLOYE)
                    .enabled(true)
                    .build();

            User clientUser = User.builder()
                    .username("client")
                    .email("client@control.ma")
                    .password(passwordEncoder.encode("client123"))
                    .role(Role.ROLE_CLIENT)
                    .enabled(true)
                    .build();

            userRepository.save(admin);
            userRepository.save(employe);
            userRepository.save(clientUser);

            Client client1 = Client.builder().name("Charaf Barou").email("cber808@gmail.com").build();
            Client client2 = Client.builder().name("Sara Amrani").email("sara.amrani@example.com").build();
            clientRepository.save(client1);
            clientRepository.save(client2);

            ContratAutomobile contratCA = ContratAutomobile.builder()
                    .dateSouscription(LocalDateTime.now())
                    .datevalidation(LocalDateTime.now().plusMonths(3))
                    .dureeContract(12)
                    .marqueVehicule("Renault")
                    .numeroImmatriculation("A15616718")
                    .status(StatusContrat.EN_COURS)
                    .montantCotisation(5600D)
                    .tauxCouverture(70D)
                    .client(client1)
                    .build();

            ContratHabitation contratCH = ContratHabitation.builder()
                    .dateSouscription(LocalDateTime.now())
                    .datevalidation(LocalDateTime.now().plusMonths(3))
                    .dureeContract(24)
                    .adreselogement("Benni Mellal")
                    .typeLogement(TypeLogement.MAISON)
                    .status(StatusContrat.EN_COURS)
                    .montantCotisation(12000D)
                    .tauxCouverture(80D)
                    .superficie(130D)
                    .client(client1)
                    .build();

            ContratSante contratCS = ContratSante.builder()
                    .dateSouscription(LocalDateTime.now())
                    .datevalidation(LocalDateTime.now().plusMonths(6))
                    .dureeContract(12)
                    .niveauCouvertur(NiveauCouverture.PREMIUM)
                    .nombrePersonnesCouvertes(3)
                    .status(StatusContrat.VALID)
                    .montantCotisation(8000D)
                    .tauxCouverture(90D)
                    .client(client2)
                    .build();

            contratRepository.save(contratCA);
            contratRepository.save(contratCH);
            contratRepository.save(contratCS);

            Paiement p1 = Paiement.builder()
                    .datePaiement(LocalDateTime.now())
                    .montant(5600D)
                    .type(TypePaiement.PAIEMENT_ANNUEL)
                    .contrat(contratCA)
                    .build();

            Paiement p2 = Paiement.builder()
                    .datePaiement(LocalDateTime.now())
                    .montant(1000D)
                    .type(TypePaiement.MENSUALITE)
                    .contrat(contratCH)
                    .build();

            Paiement p3 = Paiement.builder()
                    .datePaiement(LocalDateTime.now().minusDays(30))
                    .montant(8000D)
                    .type(TypePaiement.PAIEMENT_ANNUEL)
                    .contrat(contratCS)
                    .build();

            Paiement p4 = Paiement.builder()
                    .datePaiement(LocalDateTime.now().minusDays(15))
                    .montant(500D)
                    .type(TypePaiement.PAIEMENT_EXCEPTIONNEL)
                    .contrat(contratCH)
                    .build();

            paiementRepository.save(p1);
            paiementRepository.save(p2);
            paiementRepository.save(p3);
            paiementRepository.save(p4);
        };
    }
}
