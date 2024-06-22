package usp.mac321.ep2;

public class UsuarioNaoExistenteException extends Exception {
	String nomeUser;

	private static final long serialVersionUID = 2L;
	
	public UsuarioNaoExistenteException(String nomeUser) {
		this.nomeUser = nomeUser;
	}
	
	public String toString() {
		return "UsuarioNaoExistenteException[" + nomeUser + "]\n" + super.toString();
	}
}
