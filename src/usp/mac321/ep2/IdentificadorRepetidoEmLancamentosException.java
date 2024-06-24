package usp.mac321.ep2;

public class IdentificadorRepetidoEmLancamentosException extends Exception {
	long val;

	private static final long serialVersionUID = 5L;
	
	public IdentificadorRepetidoEmLancamentosException(long val) {
		this.val = val;
	}
	
	public String toString() {
		return "IdentificadorRepetidoEmLancamentosException[" + val + "]\n" + super.toString();
	}
}
