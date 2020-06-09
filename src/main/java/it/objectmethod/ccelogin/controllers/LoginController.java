package it.objectmethod.ccelogin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ccelogin.dto.UtenteDTO;
import it.objectmethod.ccelogin.service.UtenteService;

@RestController
public class LoginController {

	@Autowired
	private UtenteService utenteService;

	@RequestMapping("/login")
	public ResponseEntity<Long> login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		ResponseEntity<Long> resp = null;
		try {
			Long token = utenteService.login(username, password);
			resp = new ResponseEntity<>(token, HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return resp;
	}

	@RequestMapping("/findByEmail")
	public ResponseEntity<UtenteDTO> findUtenteByEmail(@RequestParam("email") String email) {
		ResponseEntity<UtenteDTO> resp = null;
		try {
			UtenteDTO utente = utenteService.findUtenteByEmail(email);
			resp = new ResponseEntity<>(utente, HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return resp;
	}

	@RequestMapping("/role")
	public ResponseEntity<String> agent(@RequestParam("email") String email,
			@RequestParam("password") String password) {
		ResponseEntity<String> resp = null;
		try {
			UtenteDTO utente = utenteService.findUtenteByEmailAndPassword(email, password);
			String role = utente.getRole();
			if (role.equalsIgnoreCase("admin")) {
				role = "Sei un admin e hai accesso a tutte le funzionalità";
			} else {
				role = "Sei un agente e hai accesso ad alcune funzionalità";
			}
			resp = new ResponseEntity<>(role, HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
}
