package it.objectmethod.ccelogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.objectmethod.ccelogin.domain.UtenteEntity;

public interface UtenteRepository extends JpaRepository<UtenteEntity, Integer> {

	UtenteEntity findByEmailAndPassword(String username, String password);

	UtenteEntity findByEmail(String email);

}
