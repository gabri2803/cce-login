package it.objectmethod.ccelogin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
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

	@RequestMapping("/findAll")
	public ResponseEntity<List<UtenteDTO>> findAllUtenti() {
		ResponseEntity<List<UtenteDTO>> resp = null;
		try {
			List<UtenteDTO> utentiList = utenteService.findAllUtenti();
			resp = new ResponseEntity<>(utentiList, HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return resp;
	}

	@RequestMapping("/role")
	public ResponseEntity<String> getRole(@RequestHeader("auth-token") String token) {
		ResponseEntity<String> resp = null;
		try {
			String role = utenteService.findUtenteRole(token);
			resp = new ResponseEntity<>(role, HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
}
