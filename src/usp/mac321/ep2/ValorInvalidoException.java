package usp.mac321.ep2;

public class ValorInvalidoException extends Exception {
	String val;

	private static final long serialVersionUID = 3L;
	
	public ValorInvalidoException(String val) {
		this.val = val;
	}
	
	public String toString() {
		return "ValorInvalidoException[" + val + "]\n" + super.toString();
	}
}
