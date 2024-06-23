package usp.mac321.ep2;

public class ConfusaoReceitaDespesaException extends Exception {
	String val;

	private static final long serialVersionUID = 3L;
	
	public ConfusaoReceitaDespesaException(String val) {
		this.val = val;
	}
	
	public String toString() {
		return "ConfusaoReceitaDespesaException[" + val + "]\n" + super.toString();
	}
}
