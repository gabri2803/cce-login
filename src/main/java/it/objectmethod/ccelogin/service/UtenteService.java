package it.objectmethod.ccelogin.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.ccelogin.controllers.beans.LoggedUsers;
import it.objectmethod.ccelogin.dto.UtenteDTO;
import it.objectmethod.ccelogin.mappers.UtenteMapper;
import it.objectmethod.ccelogin.repository.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	private LoggedUsers loggedUsers;

	@Autowired
	private UtenteRepository utenteRepo;

	@Autowired
	private UtenteMapper utenteMapper;

	public Long login(String username, String password) {
		System.out.println("EFFETTUO IL LOGIN");
		Random rand = new Random();
		Long token = rand.nextLong();
		UtenteDTO utente = utenteMapper.toDto(utenteRepo.findByEmailAndPassword(username, password));
		loggedUsers.getLoggerUserMap().put(token, utente);
		return token;
	}

	public UtenteDTO findUtenteByEmail(String email) {
		UtenteDTO utente = new UtenteDTO();
		utente = utenteMapper.toDto(utenteRepo.findByEmail(email));
		return utente;
	}

	public UtenteDTO findUtenteByEmailAndPassword(String email, String password) {
		UtenteDTO utente = new UtenteDTO();
		utente = utenteMapper.toDto(utenteRepo.findByEmailAndPassword(email, password));
		return utente;
	}
}
