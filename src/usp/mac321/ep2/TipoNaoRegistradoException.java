package usp.mac321.ep2;

public class TipoNaoRegistradoException extends Exception {
	String nomeDoTipo;

	private static final long serialVersionUID = 1L;
	
	public TipoNaoRegistradoException(String nomeDoTipo) {
		this.nomeDoTipo = nomeDoTipo;
	}
	
	public String toString() {
		return "TipoNaoRegistradoException[" + nomeDoTipo + "]\n" + super.toString();
	}
}
