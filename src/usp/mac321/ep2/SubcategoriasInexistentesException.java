package usp.mac321.ep2;

public class SubcategoriasInexistentesException extends Exception {
	private String nomeTipo;
	
	private static final long serialVersionUID = 1L;
	
	public SubcategoriasInexistentesException(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}
	
	public String toString() {
		return "SubcategoriasInexistentesException[" + nomeTipo + "]\n" + super.toString();
	}

}
