package it.objectmethod.ccelogin.service;

import java.util.ArrayList;
import java.util.List;
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

	public List<UtenteDTO> findAllUtenti() {
		List<UtenteDTO> utentiList = new ArrayList<>();
		utentiList = utenteMapper.toDto(utenteRepo.findAll());
		return utentiList;
	}

	public String findUtenteRole(String token) {
		Long tokenNum = Long.parseLong(token);
		String role = loggedUsers.getLoggerUserMap().get(tokenNum).getRole();
		if (role.equalsIgnoreCase("admin")) {
			role = "Sei un admin e hai accesso a tutte le funzionalità";
		} else {
			role = "Sei un agente e hai accesso ad alcune funzionalità";
		}
		return role;
	}
}
