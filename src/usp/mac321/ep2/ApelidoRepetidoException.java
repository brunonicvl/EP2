package usp.mac321.ep2;

public class ApelidoRepetidoException extends Exception{
	
	private String apelido;
	
	public ApelidoRepetidoException(String apelido) {
		this.apelido = apelido;
	}
	
	
	public String toString() {
		return "Apelido " + apelido + " está repetido.";
	}
}
