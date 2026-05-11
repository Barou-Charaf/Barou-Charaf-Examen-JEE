package ma.enset.control;

import ma.enset.control.entities.*;
import ma.enset.control.enums.StatusContrat;
import ma.enset.control.enums.TypeLogement;
import ma.enset.control.enums.TypePaiement;
import ma.enset.control.repositories.ClientRepository;
import ma.enset.control.repositories.ContratRepository;
import ma.enset.control.repositories.PaiementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlApplication.class, args);
	}
	@Bean
	CommandLineRunner strat(ClientRepository clientRepository, ContratRepository contratRepository, PaiementRepository paiementRepository)
	{
		return args -> {
			System.out.println("testing  the DAO ");
			Client client=Client.builder().name("Charaf Barou").email("cber808@gmail.com").build();
			ContratAutomobile contratCA = ContratAutomobile.builder().dateSouscription(LocalDateTime.now()).datevalidation(LocalDateTime.now().plusMonths(3)).dureeContract(7).marqueVehicule("Rono").numeroImmatriculation("A15616718").status(StatusContrat.EN_COURS).montantCotisation(567D).tauxCouverture(7D).build();
			ContratHabitation contratCH = ContratHabitation.builder().dateSouscription(LocalDateTime.now()).datevalidation(LocalDateTime.now().plusMonths(3)).dureeContract(7).adreselogement("Benni mellal").typeLogement(TypeLogement.MAISON).status(StatusContrat.EN_COURS).montantCotisation(567D).tauxCouverture(7D).superficie(130D).build();
			Paiement paiement = Paiement.builder().datePaiement(LocalDateTime.now()).montant(300D).type(TypePaiement.PAIEMENT_EXCEPTIONNEL).build();
			Paiement paiement1 = Paiement.builder().datePaiement(LocalDateTime.now()).montant(300D).type(TypePaiement.PAIEMENT_ANNUEL).build();
			Paiement paiement2 = Paiement.builder().datePaiement(LocalDateTime.now()).montant(300D).type(TypePaiement.MENSUALITE).build();
			Paiement paiement3 = Paiement.builder().datePaiement(LocalDateTime.now()).montant(300D).type(TypePaiement.PAIEMENT_EXCEPTIONNEL).build();

			clientRepository.save(client);
			paiementRepository.save(paiement);
			paiementRepository.save(paiement1);
			paiementRepository.save(paiement2);
			paiementRepository.save(paiement3);

		};
	}
}
