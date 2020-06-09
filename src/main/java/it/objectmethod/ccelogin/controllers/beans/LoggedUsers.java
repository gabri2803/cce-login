package it.objectmethod.ccelogin.controllers.beans;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import it.objectmethod.ccelogin.dto.UtenteDTO;

@Component
public class LoggedUsers {

	private Map<Long, UtenteDTO> loggerUserMap;

	public Map<Long, UtenteDTO> getLoggerUserMap() {
		if (this.loggerUserMap == null) {
			this.loggerUserMap = new HashMap<Long, UtenteDTO>();
		}
		System.out.println(loggerUserMap);
		return loggerUserMap;
	}
}
