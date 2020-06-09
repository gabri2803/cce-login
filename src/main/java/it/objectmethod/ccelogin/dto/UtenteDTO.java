package it.objectmethod.ccelogin.dto;

public class UtenteDTO {

	private int idUser;

	private String name;

	private String role;

	private String password;

	private String email;

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UtenteDTO [idUser=" + idUser + ", name=" + name + ", role=" + role + ", password=" + password
				+ ", email=" + email + "]";
	}
}
