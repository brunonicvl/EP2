package usp.mac321.ep2;

public class ValorNegativoException extends Exception {
	int val;

	private static final long serialVersionUID = 2L;
	
	public ValorNegativoException(int val) {
		this.val = val;
	}
	
	public ValorNegativoException(int val) {
		this.val = val;
	}
	
	public String toString() {
		return "ValorNegativoException[" + val + "]\n" + super.toString();
	}
}
