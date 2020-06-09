package it.objectmethod.ccelogin.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.ccelogin.controllers.beans.LoggedUsers;
import it.objectmethod.ccelogin.dto.UtenteDTO;

@Component
public class AuthenticationFilter implements Filter {

	@Autowired
	private LoggedUsers loggedUsers;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("ENTRO NEL FILTRO!");
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		String url = httpReq.getRequestURI();
		System.out.println(url);

		if (url.endsWith("login")) {
			System.out.println("APPROVED");
			chain.doFilter(request, response);
		} else {
			String token = httpReq.getHeader("auth-token");
			if (token != null) {
				Long tokenNum = Long.parseLong(token);
				if (loggedUsers.getLoggerUserMap().containsKey(tokenNum)) {
					System.out.println("TOKEN VALIDO APPROVED");
				}
				UtenteDTO utente = loggedUsers.getLoggerUserMap().get(tokenNum);
				if (utente.getRole().equalsIgnoreCase("admin")) {
					System.out.println("AUTORIZZATO");
					chain.doFilter(request, response);
				} else if (url.endsWith("role") && utente.getRole().equalsIgnoreCase("agent")) {
					System.out.println("Agente Autorizzato");
					chain.doFilter(request, response);
				} else {
					System.out.println("TOKEN NON VALIDO");
					httpResp.setStatus(HttpServletResponse.SC_FORBIDDEN);
				}
			} else {
				System.out.println("TOKEN NON PRESENTE");
				httpResp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
		}
	}
}
